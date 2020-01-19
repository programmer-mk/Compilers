// generated with ast extension for cup
// version 0.8
// 19/0/2020 16:47:50


package rs.ac.bg.etf.pp1.ast;

public class ActualParameter extends ActualParamList {

    private ActualParam ActualParam;

    public ActualParameter (ActualParam ActualParam) {
        this.ActualParam=ActualParam;
        if(ActualParam!=null) ActualParam.setParent(this);
    }

    public ActualParam getActualParam() {
        return ActualParam;
    }

    public void setActualParam(ActualParam ActualParam) {
        this.ActualParam=ActualParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParam!=null) ActualParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParam!=null) ActualParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParam!=null) ActualParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParameter(\n");

        if(ActualParam!=null)
            buffer.append(ActualParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParameter]");
        return buffer.toString();
    }
}
