// generated with ast extension for cup
// version 0.8
// 20/0/2020 23:33:5


package rs.ac.bg.etf.pp1.ast;

public class ConstAssignItem extends ConstAssignList {

    private ConstValAssign ConstValAssign;

    public ConstAssignItem (ConstValAssign ConstValAssign) {
        this.ConstValAssign=ConstValAssign;
        if(ConstValAssign!=null) ConstValAssign.setParent(this);
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
        if(ConstValAssign!=null) ConstValAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstValAssign!=null) ConstValAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstValAssign!=null) ConstValAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstAssignItem(\n");

        if(ConstValAssign!=null)
            buffer.append(ConstValAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstAssignItem]");
        return buffer.toString();
    }
}
