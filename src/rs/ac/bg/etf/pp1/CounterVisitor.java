package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

	protected int count;
	
	public int getCount() {
		return count;
	}
	
	/* class for counting method's formal params number */
	public static class FormParamCounter extends CounterVisitor {
		
		public void visit(FormalParamDecl formParamDecl) {
			count++;
		}
	}
	
	/* class for counting local param number */
	public static class LocalParamCounter extends CounterVisitor {
		
		public void visit(VarDecl varDecl) {
			count++;
		}
	}
}
