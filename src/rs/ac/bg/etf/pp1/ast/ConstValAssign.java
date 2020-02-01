// generated with ast extension for cup
// version 0.8
// 1/1/2020 1:36:55


package rs.ac.bg.etf.pp1.ast;

public class ConstValAssign implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String constIdent;
    private ConstVal ConstVal;

    public ConstValAssign (String constIdent, ConstVal ConstVal) {
        this.constIdent=constIdent;
        this.ConstVal=ConstVal;
        if(ConstVal!=null) ConstVal.setParent(this);
    }

    public String getConstIdent() {
        return constIdent;
    }

    public void setConstIdent(String constIdent) {
        this.constIdent=constIdent;
    }

    public ConstVal getConstVal() {
        return ConstVal;
    }

    public void setConstVal(ConstVal ConstVal) {
        this.ConstVal=ConstVal;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVal!=null) ConstVal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVal!=null) ConstVal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVal!=null) ConstVal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstValAssign(\n");

        buffer.append(" "+tab+constIdent);
        buffer.append("\n");

        if(ConstVal!=null)
            buffer.append(ConstVal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstValAssign]");
        return buffer.toString();
    }
}
