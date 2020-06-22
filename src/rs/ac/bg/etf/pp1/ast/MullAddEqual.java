// generated with ast extension for cup
// version 0.8
// 22/5/2020 21:42:31


package rs.ac.bg.etf.pp1.ast;

public class MullAddEqual extends MullopRight {

    public MullAddEqual () {
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
        buffer.append("MullAddEqual(\n");

        buffer.append(tab);
        buffer.append(") [MullAddEqual]");
        return buffer.toString();
    }
}
