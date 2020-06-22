package rs.ac.bg.etf.pp1;

import java.util.HashMap;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.LocalParamCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	
	private Stack<Integer> addop = new Stack<>();
	private Stack<Integer> mulop = new Stack<>();
	private Stack<Integer> equal = new Stack<>();
	private Stack<Obj> nestedVariables = new Stack<>();
	
	Obj currentObject = null;
	Obj previousObject = null;
	
	HashMap<String, Integer> counts = new HashMap<>();
	ArrayElemDesignator currElemDesignator = null;
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void visit(PrintStmt printStmt) {
		if(printStmt.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	

    public void visit(FactorConstVal factorConstVal) {
        Code.load(factorConstVal.getConstVal().obj);
    }
	
	/*
	public void visit(ArrayIndex arrayIndex) {
		Obj con = Tab.insert(Obj.Con, "$", Tab.intType);
		con.setLevel(0); // global const, global scope
		con.setAdr(arrayIndex.getExpr());
		
		// Push const on expr stack
		Code.load(con);
	}
	*/
	
	public void visit(MethodSignature methodSignature) {
		if("main".equalsIgnoreCase(methodSignature.getMethName())) {
			mainPc = Code.pc;
		}
		methodSignature.obj.setAdr(Code.pc);
		
		//Collect arguments and local variables
		LocalParamCounter varCnt = new LocalParamCounter();
		methodSignature.traverseBottomUp(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodSignature.traverseBottomUp(fpCnt);
		
		// generate entry 
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethodDecl methodDecl) {
		// these are final instructions in method
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(AssignStatement assignStatement) {
		if(!equal.isEmpty()) {
			if(!addop.isEmpty()) {
				 Code.put(addop.pop());
			}else {
			   // mulop is not empty
				if(!mulop.isEmpty())  Code.put(mulop.pop());
			}
		   
		    if(assignStatement.getDesignator().getClass() != ArrayElemDesignator.class) {
		    	Code.put(Code.dup);
		    }else {
		    	Code.put(Code.dup_x2);
		    }  
		}
		
		Code.store(assignStatement.getDesignator().obj);
		
		if(!equal.isEmpty()) {
			Code.put(Code.pop);
		}
		
		while(!equal.isEmpty()) {
			equal.pop();
		}
		
		while(!nestedVariables.isEmpty()) {
			nestedVariables.pop();
		}
		
		/*
		if(currElemDesignator != null)
			helper(currElemDesignator);
		currElemDesignator = null;
		*/
	}
	
	
	public void visit(AddopLeft addOpLeft) {
		Code.put(addop.pop());
	}
	
	public void visit(MullopLeft mulOpLeft) {
		Code.put(mulop.pop());
	}
	
	public void visit(MullRightOp mulRightOp) {
		equal.add(1);
		AssignStatement assignStatement = (AssignStatement) mulRightOp.getParent();
		if(assignStatement != null) {
			if(assignStatement.getDesignator().getClass() != ArrayElemDesignator.class) {
				Code.load(assignStatement.getDesignator().obj);
			}else {
				Code.put(Code.dup2);
				Code.load(assignStatement.getDesignator().obj);
			}
		}
	}
	
	
	public void visit(AddRightOp addOpRight) {
		equal.add(1);
		AssignStatement assignStatement = (AssignStatement) addOpRight.getParent();
		if(assignStatement != null) {
			if(assignStatement.getDesignator().getClass() != ArrayElemDesignator.class) {
				Code.load(assignStatement.getDesignator().obj);
			}else {
				Code.put(Code.dup2);
				Code.load(assignStatement.getDesignator().obj);
			}
		}
	}
	
	public void visit(FuncCall funcCall) {
		Obj functionObj = funcCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc; // pc releative address
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	public void visit(ProcCall procCall) {
		Obj functionObj = procCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc; // pc releative address
		Code.put(Code.call);
		Code.put2(offset);
		if(procCall.getDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop); // leaves empty expression stack
		}
	}
	
	public void visit(ReturnExpr returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnNoExpr returnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	 public void visit(AddSubExpr addSubExpr) {
		 if(addSubExpr.getParent().getClass() == AddSubExpr.class) {
			//old version i can assume?
			Code.put(addop.pop());
			 
			 
		 } else {
			 
			//previos addSubExpr
			if(!nestedVariables.isEmpty() && !equal.isEmpty()) {
				 
				AssignStatement assignStatement = (AssignStatement) addSubExpr.getParent();
				if(assignStatement != null && assignStatement.getDesignator().getClass() == ArrayElemDesignator.class) {
					//skip
				}else {
					 Code.put(addop.pop());
					 Code.put(Code.dup);
					 Code.store(nestedVariables.pop());	
				}
			}else {
				// old version
				Code.put(addop.pop());
			}
			 
		   if(!equal.isEmpty()) {
			    if(!addop.isEmpty()) {
			    	Code.put(addop.pop());
			    }else if(!mulop.isEmpty()) {
			    	Code.put(mulop.pop());
			    }
			    Code.put(Code.dup);
			    if(!nestedVariables.isEmpty()) {
			    	Code.store(nestedVariables.pop());
			    }
			    
			    //Code.put(Code.pop);
		   }
		 }
	 }
	 
	 public void visit(TermMulop term_mulop) {
	     //   Code.put(mulop.pop());
	        
		 if(term_mulop.getParent().getClass() == TermMulop.class) {
				//instructionList.add(1);
			 } else {
				 
				//previos addSubExpr
				if(!nestedVariables.isEmpty() && !equal.isEmpty()) {
					 
					AssignStatement assignStatement = (AssignStatement) term_mulop.getParent().getParent();
					if(assignStatement != null && assignStatement.getDesignator().getClass() == ArrayElemDesignator.class) {
						//skip
					}else {
						 Code.put(mulop.pop());
						 Code.put(Code.dup);
						 Code.store(nestedVariables.pop());	
					}
				}else {
					// old version without *= , /= , %= 
					Code.put(mulop.pop());
				}
				 
			   if(!equal.isEmpty()) {
				    if(!mulop.isEmpty()) {
				    	Code.put(mulop.pop());
				    }else if(!addop.isEmpty()) {
				    	Code.put(addop.pop());
				    }
				    Code.put(Code.dup);
				    if(!nestedVariables.isEmpty()) {
				    	Code.store(nestedVariables.pop());
				    }
				    
				    //Code.put(Code.pop);
			   } 
	        
			 }   
	 }
	 
	public void visit(ReadStmt readStatement) {
		if(readStatement.getDesignator().obj.getType().equals(Tab.intType)) {
			Code.put(Code.read);
		}else {
			Code.put(Code.bread);
		}
		
		Code.store(readStatement.getDesignator().obj);
	}
	
	public void visit(Add addOperation) {
		// we asume that elements in expr are already on expression stack
		addop.push(Code.add);
	}
	
	public void visit(Sub subOperation) {
		addop.push(Code.sub);
	}
	
	public void visit(Muloperation mulOperation) {
		mulop.push(Code.mul);
	}
	
	public void visit(Divoperation divOperation) {
		mulop.push(Code.div);
	}
	
	public void visit(Modoperation modOperation) {
		mulop.push(Code.rem);
	}
	
	public void visit(AddEqual addEqual) {
		addop.push(Code.add);
	}
	
	public void visit(SubEqual subEqual) {
		addop.push(Code.sub);
	}
	
	public void visit(MullAddEqual mulEqual) {
		mulop.push(Code.mul);
	}
	
	public void visit(DivEqual divEqual) {
		mulop.push(Code.div);
	}
	
	public void visit(ModEqual modEqual) {
		mulop.push(Code.rem);
	}
	
	public void visit(AddRight addRight) {
		equal.add(1);
		
		/*  += in expression  */
		//Code.load(currentObject);
	}
	
	public void visit(MulRight mulRight) {
		equal.add(1);
	}
	
	public void visit(PreArrIdxDummy preArrIdxDummy) {
		Obj arrObj = ((ArrayElemDesignator) preArrIdxDummy.getParent()).getDesignator().obj;
		Code.load(arrObj);	
	}
	
	public void helper(ArrayElemDesignator arrayElemDesignator) {
		String arrayName = arrayElemDesignator.getDesignator().obj.getName();
		Integer num = counts.get(arrayName);
		if(num == null) {
			counts.put(arrayName, 1);
		}else {
			counts.put(arrayName, num+1);
		}
		
	
		Code.load(arrayElemDesignator.getDesignator().obj);
		Code.loadConst(3);
		Code.loadConst(counts.get(arrayName));
		Code.store(arrayElemDesignator.obj);
	}
	
	
	public void visit(ArrayElemDesignator arrayElemDesignator) {
		if(!(arrayElemDesignator.getParent().getClass() == AssignStatement.class)
				&& arrayElemDesignator.getParent().getClass() != IncrementStatement.class
				&& arrayElemDesignator.getParent().getClass() != DecrementStatement.class) {
			Code.load(arrayElemDesignator.obj);	// bilo ovde
		}
		
		currElemDesignator = arrayElemDesignator;
		/* this is for += purposes */
		nestedVariables.add(arrayElemDesignator.obj);
	}
		
	public void visit(DesignatorVar designatorVar) {
		if(!(designatorVar.getParent().getClass() == AssignStatement.class
				|| designatorVar.getParent().getParent().getClass() == AssignStatement.class || designatorVar.getParent().getClass() == ArrayElemDesignator.class)) {													
			Code.load(designatorVar.obj);
			
			/* for += in expression */
			nestedVariables.add(designatorVar.obj);
		}
	}
	
	public void visit(DynamicArr dynamicArray) {
		/* this is from one modification???
		Code.put(Code.const_1);
		Code.put(Code.add);
		*/
		
		
        Code.put(Code.newarray);
        Code.put(dynamicArray.struct.getElemType().equals(Tab.charType) ? 0 : 1); // TODO: what for bool?
    }
	
	public void visit(IncrementStatement incrementStatement) {
		//Code.load(incrementStatement.getDesignator().obj); designator object is already pushed on stack in designatorVar method
		if(incrementStatement.getDesignator().getClass() == ArrayElemDesignator.class) {
			ArrayElemDesignator arrayElemDesignator = (ArrayElemDesignator) incrementStatement.getDesignator();
			Code.put(Code.dup2);
			Code.load(arrayElemDesignator.obj);
			Code.loadConst(1);
			Code.put(Code.add);
			
			Code.store(arrayElemDesignator.obj);
		} else {
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(incrementStatement.getDesignator().obj);
		}
		
	}
	
	public void visit(DecrementStatement decrementStatement) {
		//Code.load(incrementStatement.getDesignator().obj); designator object is already pushed on stack in designatorVar method
		if(decrementStatement.getDesignator().getClass() == ArrayElemDesignator.class) {
			ArrayElemDesignator arrayElemDesignator = (ArrayElemDesignator) decrementStatement.getDesignator();
			Code.put(Code.dup2);
			Code.load(arrayElemDesignator.obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			
			Code.store(arrayElemDesignator.obj);
		} else {
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(decrementStatement.getDesignator().obj);
		}
		
	}
}
