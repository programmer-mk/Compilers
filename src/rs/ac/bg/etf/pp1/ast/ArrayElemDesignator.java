// generated with ast extension for cup
// version 0.8
// 20/0/2020 23:33:6


package rs.ac.bg.etf.pp1.ast;

public class ArrayElemDesignator extends Designator {

    private Designator Designator;
    private PreArrIdxDummy PreArrIdxDummy;
    private ArrayIndex ArrayIndex;

    public ArrayElemDesignator (Designator Designator, PreArrIdxDummy PreArrIdxDummy, ArrayIndex ArrayIndex) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.PreArrIdxDummy=PreArrIdxDummy;
        if(PreArrIdxDummy!=null) PreArrIdxDummy.setParent(this);
        this.ArrayIndex=ArrayIndex;
        if(ArrayIndex!=null) ArrayIndex.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public PreArrIdxDummy getPreArrIdxDummy() {
        return PreArrIdxDummy;
    }

    public void setPreArrIdxDummy(PreArrIdxDummy PreArrIdxDummy) {
        this.PreArrIdxDummy=PreArrIdxDummy;
    }

    public ArrayIndex getArrayIndex() {
        return ArrayIndex;
    }

    public void setArrayIndex(ArrayIndex ArrayIndex) {
        this.ArrayIndex=ArrayIndex;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(PreArrIdxDummy!=null) PreArrIdxDummy.accept(visitor);
        if(ArrayIndex!=null) ArrayIndex.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(PreArrIdxDummy!=null) PreArrIdxDummy.traverseTopDown(visitor);
        if(ArrayIndex!=null) ArrayIndex.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(PreArrIdxDummy!=null) PreArrIdxDummy.traverseBottomUp(visitor);
        if(ArrayIndex!=null) ArrayIndex.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayElemDesignator(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PreArrIdxDummy!=null)
            buffer.append(PreArrIdxDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ArrayIndex!=null)
            buffer.append(ArrayIndex.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayElemDesignator]");
        return buffer.toString();
    }
}
