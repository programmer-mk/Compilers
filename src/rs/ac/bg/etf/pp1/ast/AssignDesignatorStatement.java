// generated with ast extension for cup
// version 0.8
// 20/0/2020 20:42:10


package rs.ac.bg.etf.pp1.ast;

public class AssignDesignatorStatement extends DesignatorStatement {

    private AssignStmnt AssignStmnt;

    public AssignDesignatorStatement (AssignStmnt AssignStmnt) {
        this.AssignStmnt=AssignStmnt;
        if(AssignStmnt!=null) AssignStmnt.setParent(this);
    }

    public AssignStmnt getAssignStmnt() {
        return AssignStmnt;
    }

    public void setAssignStmnt(AssignStmnt AssignStmnt) {
        this.AssignStmnt=AssignStmnt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AssignStmnt!=null) AssignStmnt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignStmnt!=null) AssignStmnt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignStmnt!=null) AssignStmnt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignDesignatorStatement(\n");

        if(AssignStmnt!=null)
            buffer.append(AssignStmnt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignDesignatorStatement]");
        return buffer.toString();
    }
}
