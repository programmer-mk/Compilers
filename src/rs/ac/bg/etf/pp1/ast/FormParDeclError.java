// generated with ast extension for cup
// version 0.8
// 19/0/2020 16:47:50


package rs.ac.bg.etf.pp1.ast;

public class FormParDeclError extends FormalParamDecl {

    public FormParDeclError () {
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
        buffer.append("FormParDeclError(\n");

        buffer.append(tab);
        buffer.append(") [FormParDeclError]");
        return buffer.toString();
    }
}