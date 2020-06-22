// generated with ast extension for cup
// version 0.8
// 22/5/2020 10:10:38


package rs.ac.bg.etf.pp1.ast;

public class MulLeft extends Mulop {

    private MullopLeft MullopLeft;

    public MulLeft (MullopLeft MullopLeft) {
        this.MullopLeft=MullopLeft;
        if(MullopLeft!=null) MullopLeft.setParent(this);
    }

    public MullopLeft getMullopLeft() {
        return MullopLeft;
    }

    public void setMullopLeft(MullopLeft MullopLeft) {
        this.MullopLeft=MullopLeft;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MullopLeft!=null) MullopLeft.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MullopLeft!=null) MullopLeft.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MullopLeft!=null) MullopLeft.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulLeft(\n");

        if(MullopLeft!=null)
            buffer.append(MullopLeft.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulLeft]");
        return buffer.toString();
    }
}
