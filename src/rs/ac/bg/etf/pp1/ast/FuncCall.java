// generated with ast extension for cup
// version 0.8
// 20/0/2020 20:42:10


package rs.ac.bg.etf.pp1.ast;

public class FuncCall extends Factor {

    private Designator Designator;
    private PreActParsDummy PreActParsDummy;
    private ActualPars ActualPars;

    public FuncCall (Designator Designator, PreActParsDummy PreActParsDummy, ActualPars ActualPars) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.PreActParsDummy=PreActParsDummy;
        if(PreActParsDummy!=null) PreActParsDummy.setParent(this);
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public PreActParsDummy getPreActParsDummy() {
        return PreActParsDummy;
    }

    public void setPreActParsDummy(PreActParsDummy PreActParsDummy) {
        this.PreActParsDummy=PreActParsDummy;
    }

    public ActualPars getActualPars() {
        return ActualPars;
    }

    public void setActualPars(ActualPars ActualPars) {
        this.ActualPars=ActualPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(PreActParsDummy!=null) PreActParsDummy.accept(visitor);
        if(ActualPars!=null) ActualPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(PreActParsDummy!=null) PreActParsDummy.traverseTopDown(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(PreActParsDummy!=null) PreActParsDummy.traverseBottomUp(visitor);
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FuncCall(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PreActParsDummy!=null)
            buffer.append(PreActParsDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FuncCall]");
        return buffer.toString();
    }
}
