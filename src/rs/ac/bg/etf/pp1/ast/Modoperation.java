// generated with ast extension for cup
// version 0.8
// 22/5/2020 10:10:38


package rs.ac.bg.etf.pp1.ast;

public class Modoperation extends MullopLeft {

    public Modoperation () {
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
        buffer.append("Modoperation(\n");

        buffer.append(tab);
        buffer.append(") [Modoperation]");
        return buffer.toString();
    }
}
