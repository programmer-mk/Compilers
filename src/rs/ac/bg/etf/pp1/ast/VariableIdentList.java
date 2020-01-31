// generated with ast extension for cup
// version 0.8
// 31/0/2020 22:21:38


package rs.ac.bg.etf.pp1.ast;

public class VariableIdentList extends VarIdentList {

    private VarIdentList VarIdentList;
    private VarIdent VarIdent;

    public VariableIdentList (VarIdentList VarIdentList, VarIdent VarIdent) {
        this.VarIdentList=VarIdentList;
        if(VarIdentList!=null) VarIdentList.setParent(this);
        this.VarIdent=VarIdent;
        if(VarIdent!=null) VarIdent.setParent(this);
    }

    public VarIdentList getVarIdentList() {
        return VarIdentList;
    }

    public void setVarIdentList(VarIdentList VarIdentList) {
        this.VarIdentList=VarIdentList;
    }

    public VarIdent getVarIdent() {
        return VarIdent;
    }

    public void setVarIdent(VarIdent VarIdent) {
        this.VarIdent=VarIdent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarIdentList!=null) VarIdentList.accept(visitor);
        if(VarIdent!=null) VarIdent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarIdentList!=null) VarIdentList.traverseTopDown(visitor);
        if(VarIdent!=null) VarIdent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarIdentList!=null) VarIdentList.traverseBottomUp(visitor);
        if(VarIdent!=null) VarIdent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VariableIdentList(\n");

        if(VarIdentList!=null)
            buffer.append(VarIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarIdent!=null)
            buffer.append(VarIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariableIdentList]");
        return buffer.toString();
    }
}
