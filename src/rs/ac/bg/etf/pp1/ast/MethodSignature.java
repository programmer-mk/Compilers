// generated with ast extension for cup
// version 0.8
// 31/0/2020 22:21:38


package rs.ac.bg.etf.pp1.ast;

public class MethodSignature implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private IncludeVoidType IncludeVoidType;
    private String methName;
    private PreFormParsDummy PreFormParsDummy;
    private FormPars FormPars;

    public MethodSignature (IncludeVoidType IncludeVoidType, String methName, PreFormParsDummy PreFormParsDummy, FormPars FormPars) {
        this.IncludeVoidType=IncludeVoidType;
        if(IncludeVoidType!=null) IncludeVoidType.setParent(this);
        this.methName=methName;
        this.PreFormParsDummy=PreFormParsDummy;
        if(PreFormParsDummy!=null) PreFormParsDummy.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
    }

    public IncludeVoidType getIncludeVoidType() {
        return IncludeVoidType;
    }

    public void setIncludeVoidType(IncludeVoidType IncludeVoidType) {
        this.IncludeVoidType=IncludeVoidType;
    }

    public String getMethName() {
        return methName;
    }

    public void setMethName(String methName) {
        this.methName=methName;
    }

    public PreFormParsDummy getPreFormParsDummy() {
        return PreFormParsDummy;
    }

    public void setPreFormParsDummy(PreFormParsDummy PreFormParsDummy) {
        this.PreFormParsDummy=PreFormParsDummy;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IncludeVoidType!=null) IncludeVoidType.accept(visitor);
        if(PreFormParsDummy!=null) PreFormParsDummy.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IncludeVoidType!=null) IncludeVoidType.traverseTopDown(visitor);
        if(PreFormParsDummy!=null) PreFormParsDummy.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IncludeVoidType!=null) IncludeVoidType.traverseBottomUp(visitor);
        if(PreFormParsDummy!=null) PreFormParsDummy.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodSignature(\n");

        if(IncludeVoidType!=null)
            buffer.append(IncludeVoidType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methName);
        buffer.append("\n");

        if(PreFormParsDummy!=null)
            buffer.append(PreFormParsDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodSignature]");
        return buffer.toString();
    }
}
