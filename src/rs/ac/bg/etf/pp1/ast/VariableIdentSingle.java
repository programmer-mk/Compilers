// generated with ast extension for cup
// version 0.8
// 21/5/2020 1:0:39


package rs.ac.bg.etf.pp1.ast;

public class VariableIdentSingle extends VarIdent {

    private String varIdent;

    public VariableIdentSingle (String varIdent) {
        this.varIdent=varIdent;
    }

    public String getVarIdent() {
        return varIdent;
    }

    public void setVarIdent(String varIdent) {
        this.varIdent=varIdent;
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
        buffer.append("VariableIdentSingle(\n");

        buffer.append(" "+tab+varIdent);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariableIdentSingle]");
        return buffer.toString();
    }
}
