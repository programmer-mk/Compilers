// generated with ast extension for cup
// version 0.8
// 21/5/2020 1:0:40


package rs.ac.bg.etf.pp1.ast;

public class MulRight extends Mulop {

    private MullopRight MullopRight;

    public MulRight (MullopRight MullopRight) {
        this.MullopRight=MullopRight;
        if(MullopRight!=null) MullopRight.setParent(this);
    }

    public MullopRight getMullopRight() {
        return MullopRight;
    }

    public void setMullopRight(MullopRight MullopRight) {
        this.MullopRight=MullopRight;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MullopRight!=null) MullopRight.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MullopRight!=null) MullopRight.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MullopRight!=null) MullopRight.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulRight(\n");

        if(MullopRight!=null)
            buffer.append(MullopRight.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulRight]");
        return buffer.toString();
    }
}
