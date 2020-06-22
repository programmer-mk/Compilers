package rs.ac.bg.etf.pp1;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
    int printCallCount = 0;
    int varDeclCount = 0;
    boolean returnFounded = false;
	boolean errorDetected = false;
	private Struct currentType;
	private Obj currentMethod = null;
	private int mainFuncCallCnt = 0;
	private boolean mainFounded;
	
	/* constant name and value */
	private HashMap<String,Integer> constants = new HashMap<>();
	/* arrrays and sizes, in Symbol table are declaration, here are definitions  */
	private HashMap<String,Integer> definedArrays = new HashMap<>();
	
	private Stack<ArrayList<Struct>> currentActParTypesStack = new Stack<>();
	private ArrayList<Obj> currentMethFormPars = null;
	
	/* for code generating */
	int nVars;  
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if(line != 0) {
		  msg.append("on line").append(line);
		}
		
		log.error(msg.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if(line != 0) {
		  msg.append("on line").append(line);
		}
		
		log.error(msg.toString());
	}
	
    private void formParInsert(String formParName, Struct formParType, int formParLine) {
	        if (findByName(formParName, currentMethFormPars).equals(Tab.noObj)) {
	            Obj formParObj = new Obj(Obj.Var, formParName, formParType);
	            currentMethFormPars.add(formParObj);
	        } else {
	            report_error("Error on " + formParLine + "(" + formParName + ") is already declared", null);
	        }
	}
	 
    private Obj findByName(String name, Collection<Obj> collection) {
	        for (Obj item : collection) {
	            if (item.getName().equals(name)) {
	                return item;
	            }
	        }

	        return Tab.noObj;
	}

	
    public void visit(PrintStmt print) {
		printCallCount++;
		if(print.getExpr().struct != Tab.intType && print.getExpr().struct != Tab.charType) {
			report_error("Semantic error on line " + print.getLine()+ "Operand type instruction PRINT must be char or int!", null);
		}
		
			
	}
    
    public void visit(ProgName progName) {
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program) {
    	nVars = Tab.currentScope.getnVars();
    	//chain all local variables
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    
    public void visit(Type type) {
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj) {
    	  report_error("Type is not founded" + type.getTypeName() + " in symboltable!",  null);
    	}else {
    	  if(Obj.Type == typeNode.getKind()) {
    		 type.struct = typeNode.getType();
    		 currentType = type.struct; // type in current line
    	  }else {
    		  report_error("Error: Name"+ type.getTypeName()+ "is not type!", type);
    		  type.struct = Tab.noType;
    	  }
    	}
    }
    
    public void visit(VariableIdentList varIdentList) {
    	report_info("list of variables", null);
    }
    
    public void visit(VariableIdentSingle singleVar) {
    	report_info("Declared variable  "+singleVar.getVarIdent()+ "with type : "+ currentType.getKind(), singleVar);
    	Obj varNode = Tab.insert(Obj.Var, singleVar.getVarIdent(), currentType);
    }
    
    public void visit(ArrayVariableIdent arrayVariable) {
     	report_info("Declared array variable  "+arrayVariable.getArrIdent()+ "with type : "+ currentType.getKind(), arrayVariable);
    	Obj varNode = Tab.insert(Obj.Var, arrayVariable.getArrIdent(), new Struct(Struct.Array, currentType));
    }
    
    public void visit(ConstValAssign constValAssign) {
    	constants.put(constValAssign.getConstIdent(), currentType.getKind()); // remove ?
    	Obj valObj = constValAssign.getConstVal().obj;
        if (valObj.getType().equals(currentType)) {
            if (Tab.currentScope().findSymbol(constValAssign.getConstIdent()) == null) {
                Obj temp = Tab.insert(valObj.getKind(), constValAssign.getConstIdent(), valObj.getType());
                temp.setAdr(valObj.getAdr());

                if (temp.getLevel() == 0) {
                  //  globalConstCnt++;
                }
            } else {
                report_error("Error on " + constValAssign.getLine() + "(" + constValAssign.getConstIdent() + ") is already declared", null);
            }
        } else {
            report_error("Error on " + constValAssign.getLine() + ": incompatible types", null);
        }
    }
    
    public void visit(MethodSignature methodSignature) {
    	currentMethod = Tab.noObj;
    	//symbl with same name in same scope
    	Obj sym = Tab.currentScope().findSymbol(methodSignature.getMethName());
    	if(sym == null) {
    	   currentMethod = Tab.insert(Obj.Meth, methodSignature.getMethName(), methodSignature.getIncludeVoidType().struct);
           Tab.openScope();
           
           if("main".equalsIgnoreCase(currentMethod.getName())) {
        	   mainFounded = true;
        	   
        	   if (!currentMethod.getType().equals(Tab.noType)) {
                   report_error("Error on " + methodSignature.getLine() + ": return value main method must be void", null);
               }
        	   
        	   if(!currentMethFormPars.isEmpty()) {
        		   report_error("Error on " + methodSignature.getLine() + ": main method cannot have formal parameters", null); 
        	   }
           }
           
           int formalParCnt = 1;
           for(Obj methFormPar : currentMethFormPars) {
        	   Obj temp = Tab.insert(methFormPar.getKind(), methFormPar.getName(), methFormPar.getType());
        	   temp.setFpPos(formalParCnt++);
           }
           
           currentMethod.setLevel(currentMethFormPars.size());
    	}else {
    		report_error("Error on " + methodSignature.getLine() + "(" + methodSignature.getMethName() + ") is already declared", null);
    	}
    	
    	methodSignature.obj = currentMethod;
    	currentMethFormPars = null;  // I am not sure if this goes here, check this later
    }
    
    public void visit(MethodDecl methodDecl) {
    	if(!returnFounded && currentMethod.getType() != Tab.noType) {
    		report_error("Semantic error on line "+ methodDecl.getLine()+ ": function "+ currentMethod.getName()+ " doesn't have return command!", null);
    	}
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	
    	returnFounded = false;
    	currentMethod = null;
    }
    
    public void visit(WithoutVoidType withoutVoidType) {
    	withoutVoidType.struct = withoutVoidType.getType().struct;
    }

    public void visit(VoidType voidType) {
     	voidType.struct = Tab.noType;
    }
    
    public void visit(DesignatorVar designator) {
    	Obj varName = Tab.find(designator.getName());
    	if(varName == Tab.noObj) {
    		report_error("Error on line "+ designator.getLine()+" : name "+ designator.getName()+" is not declared", null);	
    	}
    	designator.obj = varName;
    }
    
    public void visit(ArrayElemDesignator arrDesign) {
    	/*
    	Obj varName = Tab.find(arrDesign.getName());
    	if(varName == Tab.noObj) {
    		report_error("Error on line "+ arrDesign.getLine()+" : name "+ arrDesign.getName()+" is not declared", null);	
    	}
    	*/
   
    
    	
    	Obj desigObj = arrDesign.getDesignator().obj;
    	if(desigObj.getType().getKind() != Struct.Array) {
    		report_error("Error on line "+ arrDesign.getLine()+ ": name "+ arrDesign.getDesignator().obj.getName() + " is not an array!", null);
    	}else {
    		arrDesign.obj = new Obj(Obj.Elem, desigObj.getName() + "_elem", desigObj.getType().getElemType());
    	}
    	
    	
    	if(!definedArrays.containsKey(desigObj.getName())) {
    		report_error("Error on line "+ arrDesign.getLine()+ ": name "+ arrDesign.getDesignator().obj.getName() + " is only declared!", null);
    	}
    	
    }
    
    public void visit(FuncCall funcCall) {
    	if ("main".equalsIgnoreCase(currentMethod.getName())) {
            mainFuncCallCnt++;
        }
    	
    	Obj function = funcCall.getDesignator().obj;
    	ArrayList<Struct> currentActParTypes = currentActParTypesStack.pop();
    	
    	if(Obj.Meth == function.getKind()) {
    		funcCall.struct = function.getType();
    	}else {
    		report_error("Error on line "+ funcCall.getLine()+ ": name "+ function.getName() + " is not a function!", null);
    		funcCall.struct = Tab.noType;
    		return;
    	}
    	
    	Obj designatorObj = funcCall.getDesignator().obj;
    	int paramNumber = designatorObj.getLevel();
    	if(paramNumber != currentActParTypes.size()) {
    		report_error("Error on  " + funcCall.getLine() + ": number of function's parameters is incorrect!", null);
    	}else {
    		ArrayList<Obj> methLocalParams = new ArrayList<>(designatorObj.getLocalSymbols());
    		if (designatorObj.equals(currentMethod)) {
                // special case: recursion,local symbols of method are in current scope!
                methLocalParams = new ArrayList<>(Tab.currentScope().values());
            }

            for (int i = 0; i < paramNumber; i++) {
                Obj formPar = methLocalParams.get(i);
                Struct actParType = currentActParTypes.get(i);

               
                if (!actParType.assignableTo(formPar.getType())) {
                    report_error("Error on " + funcCall.getLine() + "(" + designatorObj.getName() + ") call parameters are not correct", null);
                    break;
                }
            }
        }
    }
    
    public void visit(TermFactor term) {
    	term.struct = term.getFactor().struct;
    }
    
    public void visit(DynamicData dynamicData) {
    	dynamicData.struct = dynamicData.getType().struct;
    }
    
    public void visit(DynamicArr dynamicArray) {
    	if (!dynamicArray.getExpr().struct.equals(Tab.intType)) {
            report_error("Error on line " + dynamicArray.getLine() + ": expression type in new[] expr isn't int", null);
        }

    	dynamicArray.struct = new Struct(Struct.Array,dynamicArray.getType().struct);
    }

    public void visit(AddSubExpr addSubExpr) {
    	Struct exprType = addSubExpr.getExpr().struct;
    	Struct termType = addSubExpr.getTerm().struct;
    	if(termType.getKind() == Struct.Array) {
    		termType = termType.getElemType();
    	}
    	
    	if(exprType.equals(termType) && exprType == Tab.intType) {
    		addSubExpr.struct = exprType;
    	}else {
    		report_error("Error on line"+ addSubExpr.getLine()+" : the types are incompatible in expression.", null);
    		addSubExpr.struct = Tab.noType;
    	}
    }
    
    public void visit(FactorParen factorParen) {
    	factorParen.struct = factorParen.getExpr().struct;
    }
    
    public void visit(ReturnExpr returnExpr) {
    	returnFounded = true;
    	Struct currentMethodType = currentMethod.getType();
    	if(!currentMethodType.compatibleWith(returnExpr.getExpr().struct)) {
    		report_error("Error on line"+ returnExpr.getLine()+ ": return type is not same like return type of the function "+ currentMethod.getName(), null);
    	}
    }
    
    public void visit(FactorConstVal factorConst) {
    	factorConst.struct = factorConst.getConstVal().obj.getType();
    }
    
    public void visit(ConstValNum constValNum) {
    	constValNum.obj = new Obj(Obj.Con, "", Tab.intType, constValNum.getN1(), Obj.NO_VALUE);
    }
    
    public void visit(ConstValChar constValChar) {
    	constValChar.obj = new Obj(Obj.Con, "", Tab.charType, constValChar.getC1(), Obj.NO_VALUE);
    }
    
    public void visit(ConstValBool constValBool) {
    	constValBool.obj = new Obj(Obj.Con, "", Tab.intType, Boolean.valueOf(constValBool.getB1()) ? 1 : 0, Obj.NO_VALUE);
    }
    
    public void visit(Var var) {
    	var.struct = var.getDesignator().obj.getType();
    }
    
    public void visit(AssignStatement assignStatement) {
    	Struct first = assignStatement.getExpr().struct;
    	Struct second = assignStatement.getDesignator().obj.getType();
    	if(assignStatement.getDesignator().getClass() == DesignatorVar.class) {
    		//------------------------
    		if(first.getKind() == Struct.Array) {
        		// Type arr[] = new Type[x];
        		Struct delcaredArrayType = Tab.find(assignStatement.getDesignator().obj.getName()).getType(); // array type
        		if(!assignStatement.getExpr().struct.assignableTo(delcaredArrayType)) {
            		report_error("Error on line "+ assignStatement.getLine()+ " : " + "incompatible types in assign statement ", null);
            	}
        		
        		definedArrays.put(assignStatement.getDesignator().obj.getName(), 1);
        	}else {
        		if(!assignStatement.getExpr().struct.assignableTo(assignStatement.getDesignator().obj.getType())) {
            		report_error("Error on line "+ assignStatement.getLine()+ " : " + "incompatible types in assign statement ", null);
            	}
        	}
    	}else {
    		// array element assign expr
    		Obj desigObj = assignStatement.getDesignator().obj;
        	if(!assignStatement.getExpr().struct.assignableTo(desigObj.getType())) {
        		report_error("Error on line "+ assignStatement.getLine()+ " : " + "incompatible types in assign statement ", null);
        	}
    	}
    }
    
    public void visit(TermExpr termExpr) {
    	termExpr.struct = termExpr.getTerm().struct;
    }
    
    public void visit(TermMulop termMulop) {
    	termMulop.struct = termMulop.getTerm().struct;
    }
    
    public void visit(IncrementStatement increment) {
    	String varName = increment.getDesignator().obj.getName();
    	Struct varType = Tab.find(varName).getType();
    	if(!(varType.getKind() == Struct.Int || increment.getDesignator().obj.getKind() == Obj.Elem )) {
    		report_error("Error on line "+ increment.getLine()+ " : " + "variable type isn't INT", null);
    	}
    }
    
    public void visit(DecrementStatement decrement) {
    	String varName = decrement.getDesignator().obj.getName();
    	Struct varType = Tab.find(varName).getType();
    	if(!(varType.getKind() == Struct.Int || decrement.getDesignator().obj.getKind() == Obj.Elem)) {
    		report_error("Error on line "+ decrement.getLine()+ " : " + "variable type isn't INT", null);
    	}
    }
    
   
    public void visit(FormParDeclSingle formParDeclSingle) {
        formParInsert(formParDeclSingle.getI2(), formParDeclSingle.getType().struct, formParDeclSingle.getLine());
    }
    
    public void visit(FormParDeclArray formParDeclArray) {
        formParInsert(formParDeclArray.getI2(), new Struct(Struct.Array, formParDeclArray.getType().struct), formParDeclArray.getLine());
    }
    
    
    public void visit(PreFormParsDummy paramDummy) {
    	currentMethFormPars = new ArrayList<>();
    }
    
    public void visit(PreActParsDummy preActParsDummy) {
        currentActParTypesStack.push(new ArrayList<>());
    }
    
    
    public void visit(ActualPar actParam) {
        currentActParTypesStack.peek().add(actParam.getExpr().struct);
    }
    
    public void visit(ReadStmt readStatement) {
    	String varName = readStatement.getDesignator().obj.getName();
    	Struct varType = Tab.find(varName).getType();
    	if(!(varType.getKind() == Struct.Int || varType.getKind()== Struct.Char || varType.getKind()== Struct.Bool)) {
    		report_error("Error on line "+ readStatement.getLine()+ " : " + "variable type isn't INT or Char or Bool", null);
    	}
    }
    
    public boolean passed() {
    	return !errorDetected;
    }
 }
