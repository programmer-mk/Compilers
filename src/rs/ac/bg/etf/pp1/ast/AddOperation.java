// generated with ast extension for cup
// version 0.8
// 20/0/2020 23:33:6


package rs.ac.bg.etf.pp1.ast;

public class AddOperation extends Operation {

    private Addop Addop;

    public AddOperation (Addop Addop) {
        this.Addop=Addop;
        if(Addop!=null) Addop.setParent(this);
    }

    public Addop getAddop() {
        return Addop;
    }

    public void setAddop(Addop Addop) {
        this.Addop=Addop;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Addop!=null) Addop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Addop!=null) Addop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Addop!=null) Addop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddOperation(\n");

        if(Addop!=null)
            buffer.append(Addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddOperation]");
        return buffer.toString();
    }
}
