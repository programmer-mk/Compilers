// generated with ast extension for cup
// version 0.8
// 22/5/2020 10:10:38


package rs.ac.bg.etf.pp1.ast;

public class AddRightOp extends Assignop {

    private AddopRight AddopRight;

    public AddRightOp (AddopRight AddopRight) {
        this.AddopRight=AddopRight;
        if(AddopRight!=null) AddopRight.setParent(this);
    }

    public AddopRight getAddopRight() {
        return AddopRight;
    }

    public void setAddopRight(AddopRight AddopRight) {
        this.AddopRight=AddopRight;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddopRight!=null) AddopRight.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddopRight!=null) AddopRight.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddopRight!=null) AddopRight.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddRightOp(\n");

        if(AddopRight!=null)
            buffer.append(AddopRight.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddRightOp]");
        return buffer.toString();
    }
}
