// generated with ast extension for cup
// version 0.8
// 19/0/2020 16:47:50


package rs.ac.bg.etf.pp1.ast;

public class MulOperation extends Operation {

    private Mulop Mulop;

    public MulOperation (Mulop Mulop) {
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Mulop!=null) Mulop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulOperation(\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulOperation]");
        return buffer.toString();
    }
}
