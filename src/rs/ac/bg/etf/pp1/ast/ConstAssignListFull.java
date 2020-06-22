// generated with ast extension for cup
// version 0.8
// 22/5/2020 21:42:30


package rs.ac.bg.etf.pp1.ast;

public class ConstAssignListFull extends ConstAssignList {

    private ConstAssignList ConstAssignList;
    private ConstValAssign ConstValAssign;

    public ConstAssignListFull (ConstAssignList ConstAssignList, ConstValAssign ConstValAssign) {
        this.ConstAssignList=ConstAssignList;
        if(ConstAssignList!=null) ConstAssignList.setParent(this);
        this.ConstValAssign=ConstValAssign;
        if(ConstValAssign!=null) ConstValAssign.setParent(this);
    }

    public ConstAssignList getConstAssignList() {
        return ConstAssignList;
    }

    public void setConstAssignList(ConstAssignList ConstAssignList) {
        this.ConstAssignList=ConstAssignList;
    }

    public ConstValAssign getConstValAssign() {
        return ConstValAssign;
    }

    public void setConstValAssign(ConstValAssign ConstValAssign) {
        this.ConstValAssign=ConstValAssign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstAssignList!=null) ConstAssignList.accept(visitor);
        if(ConstValAssign!=null) ConstValAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstAssignList!=null) ConstAssignList.traverseTopDown(visitor);
        if(ConstValAssign!=null) ConstValAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstAssignList!=null) ConstAssignList.traverseBottomUp(visitor);
        if(ConstValAssign!=null) ConstValAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstAssignListFull(\n");

        if(ConstAssignList!=null)
            buffer.append(ConstAssignList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstValAssign!=null)
            buffer.append(ConstValAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstAssignListFull]");
        return buffer.toString();
    }
}
