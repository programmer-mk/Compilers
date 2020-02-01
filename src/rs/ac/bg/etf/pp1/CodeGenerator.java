package rs.ac.bg.etf.pp1;

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
		Code.store(assignStatement.getDesignator().obj);
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
		if(addSubExpr.getOperation().getClass() == AddOperation.class ) {
		   Code.put(addop.pop());
		}else {
		   Code.put(mulop.pop());
		}
	 }
	 
	public void visit(FactorParen factorParen) {
		
	}
	 
	public void visit(ReadStmt readStatement) {
		if(readStatement.getDesignator().obj.getType().equals(Tab.intType)) {
			Code.put(Code.read);
		}else {
			Code.put(Code.bread);
		}
		
		Code.store(readStatement.getDesignator().obj);
	}
	
	public void visit(Addop addOperation) {
		// we asume that elements in expr are already on expression stack
		addop.push(Code.add);
	}
	
	public void visit(Subop subOperation) {
		addop.push(Code.sub);
	}
	
	public void visit(Mulop muloOperation) {
		mulop.push(Code.mul);
	}
	
	public void visit(Divop divOperation) {
		mulop.push(Code.div);
	}
	
	public void visit(Modop modOperation) {
		mulop.push(Code.rem);
	}
	
	public void visit(PreArrIdxDummy preArrIdxDummy) {
		Obj arrObj = ((ArrayElemDesignator) preArrIdxDummy.getParent()).getDesignator().obj;
		Code.load(arrObj);	
	}
	
	
	public void visit(ArrayElemDesignator arrayElemDesignator) {
		if(!(arrayElemDesignator.getParent().getClass() == AssignStatement.class)) {
			Code.load(arrayElemDesignator.obj);
		}
	}
		
	public void visit(DesignatorVar designatorVar) {
		if(!(designatorVar.getParent().getClass() == AssignStatement.class
				|| designatorVar.getParent().getParent().getClass() == AssignStatement.class || designatorVar.getParent().getClass() == ArrayElemDesignator.class)) {													
			Code.load(designatorVar.obj);
		}
	}
	
	public void visit(DynamicArr dynamicArray) {
        Code.put(Code.newarray);
        Code.put(dynamicArray.struct.getElemType().equals(Tab.charType) ? 0 : 1); // TODO: what for bool?
    }
	
	public void visit(IncrementStatement incrementStatement) {
		//Code.load(incrementStatement.getDesignator().obj); designator object is already pushed on stack in designatorVar method
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(incrementStatement.getDesignator().obj);
	}
	
	public void visit(DecrementStatement decrementStatement) {
		//Code.load(incrementStatement.getDesignator().obj); designator object is already pushed on stack in designatorVar method
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(decrementStatement.getDesignator().obj);
	}
}
