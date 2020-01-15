// generated with ast extension for cup
// version 0.8
// 15/0/2020 4:26:5


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Factor Factor);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ConstAssignList ConstAssignList);
    public void visit(GlobalDeclList GlobalDeclList);
    public void visit(ConstDecl ConstDecl);
    public void visit(ActualParamList ActualParamList);
    public void visit(Expr Expr);
    public void visit(FormalParamList FormalParamList);
    public void visit(VarIdent VarIdent);
    public void visit(FormPars FormPars);
    public void visit(VarDeclList VarDeclList);
    public void visit(Operation Operation);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Statement Statement);
    public void visit(AssignStmnt AssignStmnt);
    public void visit(ConstVal ConstVal);
    public void visit(VarIdentList VarIdentList);
    public void visit(StatementList StatementList);
    public void visit(GlobalDecl GlobalDecl);
    public void visit(ActualPars ActualPars);
    public void visit(Modop Modop);
    public void visit(Divop Divop);
    public void visit(Mulop Mulop);
    public void visit(Subop Subop);
    public void visit(Addop Addop);
    public void visit(MulOperation MulOperation);
    public void visit(AddOperation AddOperation);
    public void visit(Designator Designator);
    public void visit(AssignStatement AssignStatement);
    public void visit(DecrementStatement DecrementStatement);
    public void visit(IncrementStatement IncrementStatement);
    public void visit(AssignDesignatorStatement AssignDesignatorStatement);
    public void visit(ActualParam ActualParam);
    public void visit(ActualParams ActualParams);
    public void visit(NoActuals NoActuals);
    public void visit(Actuals Actuals);
    public void visit(FuncCall FuncCall);
    public void visit(Var Var);
    public void visit(Const Const);
    public void visit(Term Term);
    public void visit(TermExpr TermExpr);
    public void visit(AddSubExpr AddSubExpr);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(PrintStmt PrintStmt);
    public void visit(ErrorStmt ErrorStmt);
    public void visit(Assignment Assignment);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(Type Type);
    public void visit(ArrayVariableIdent ArrayVariableIdent);
    public void visit(VariableIdentSingle VariableIdentSingle);
    public void visit(OneVariableIdent OneVariableIdent);
    public void visit(VariableIdentList VariableIdentList);
    public void visit(VarDecl VarDecl);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(ConstValBool ConstValBool);
    public void visit(ConstValChar ConstValChar);
    public void visit(ConstValNum ConstValNum);
    public void visit(ConstValAssign ConstValAssign);
    public void visit(ConstAssignItem ConstAssignItem);
    public void visit(ConstAssignListFull ConstAssignListFull);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(GlobalVariableDecl GlobalVariableDecl);
    public void visit(GlobalConstantDecl GlobalConstantDecl);
    public void visit(GlobalDeclListNone GlobalDeclListNone);
    public void visit(GlobalDeclListFull GlobalDeclListFull);
    public void visit(Program Program);

}
