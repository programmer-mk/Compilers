// generated with ast extension for cup
// version 0.8
// 20/0/2020 20:42:10


package rs.ac.bg.etf.pp1.ast;

public class ArrayVariableIdent extends VarIdent {

    private String arrIdent;

    public ArrayVariableIdent (String arrIdent) {
        this.arrIdent=arrIdent;
    }

    public String getArrIdent() {
        return arrIdent;
    }

    public void setArrIdent(String arrIdent) {
        this.arrIdent=arrIdent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayVariableIdent(\n");

        buffer.append(" "+tab+arrIdent);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayVariableIdent]");
        return buffer.toString();
    }
}
