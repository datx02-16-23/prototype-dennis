/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation.processor.parser;

import com.dennisjonsson.annotation.log.ast.EvalOperation;
import com.dennisjonsson.annotation.log.ast.WriteOperation;
import com.dennisjonsson.annotation.markup.Argument;
import com.dennisjonsson.annotation.markup.ArrayDataStructure;
import com.dennisjonsson.annotation.markup.DataStructure;
import com.dennisjonsson.annotation.markup.Header;
import com.dennisjonsson.annotation.markup.Method;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.lang.model.element.Element;

/**
 *
 * @author dennis
 */
public class MainParser extends ModifierVisitorAdapter
{
    final ArrayList<DataStructure> dataStructures;
    final ArrayList<DataStructure> uknowns;
    final HashMap<String, Method> methods;
    final String className;
    
    final Element printMethod;
    
    public static final String LOGGER = "logger";
    public static final String PRINT = "print";
    public static final String PRINT_METHOD = LOGGER+"."+PRINT;
    
    
    public static final String SKIP = "skip";
    public static final String IS_ASSIGNMENT = "assignment";
    public static final String IS_DECLARATION = "declaration";
    public static final String IS_BINARY = "binary";
    public static final String CONTINUE = "continue";
    
    private enum Level{
        CLASS,
        METHOD
    }
    
    private Level level = Level.CLASS; 
    
    private String methodScope = null;


    public MainParser(String className, ArrayList<DataStructure> dataStruct, Element printMethod, 
            HashMap<String, Method> methods) {
        this.dataStructures = dataStruct;
        uknowns = new ArrayList<>();
        this.printMethod = printMethod;
        this.methods = methods;
        this.className = className;
        
    }
    
 
    private String concatIdentifier(String id){
        
        if(methodScope != null){
              return className + Header.CONCAT + methodScope + Header.CONCAT + id;
        }
        return className + Header.CONCAT + id;
        
        
        //return id;
        
    }
  
    private boolean arrayMatchesIdentifier(String identifier, String arrayAccess){
        
        if(methodScope != null){
              return ( className + Header.CONCAT + methodScope + Header.CONCAT + arrayAccess)
                      .matches(identifier+"(\\s*(\\[(.*)\\]))*");
        }
        return (className + Header.CONCAT + arrayAccess)
                .matches(identifier+"(\\s*(\\[(.*)\\]))*");
        
        
        //return arrayAccess.matches(identifier+"(\\s*(\\[(.*)\\]))*");
        
    }
    
    private DataStructure isAnnotated(String identifier){
        for(DataStructure dataStructure : dataStructures ){
            if(arrayMatchesIdentifier(dataStructure.getIdentifier(),
                    identifier)){
                return dataStructure;
            }
        }
        return null;
    }
    
     /*
        highest branch
    */

    @Override
    public Node visit(MethodDeclaration n, Object arg) {
        
        if(printMethod == null){
            return super.visit(n, arg); 
        }
        
        level = Level.METHOD;
        String name = n.getName();
        String print = printMethod.toString().replaceAll("\\(.*\\)", "");
        //System.out.println(name + ", "+print);
        if(name.equalsIgnoreCase(print)){
            ArrayList<Expression> args = new ArrayList<>();
            n.getBody().getStmts().add(new ExpressionStmt (new MethodCallExpr(null, PRINT_METHOD, args)));
        }
        return super.visit(n, arg); 
    }

    @Override
    public Node visit(ExpressionStmt n, Object arg) {
        
        Expression expr = n.getExpression();
        if(expr instanceof AssignExpr){
            AssignExpr ax = (AssignExpr)expr;
            if(isTracked(ax)){
                MethodCallExpr call = setEval(ax);
                n.setExpression(call);
            }
           
        }
     
        return super.visit(n, null); 
    }
    
    /* 
                EVAL LEVEL
    */
    
    @Override
    public Node visit(VariableDeclarator n, Object arg) {
        if(isTracked(n)){
            eval(n);
            
        }
       
        return super.visit(n, IS_DECLARATION); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void eval(VariableDeclarator decl){
        
        Expression init = decl.getInit();
        MethodCallExpr call = null;
        
        if( init instanceof ArrayAccessExpr){
            ArrayAccessExpr aaExpr = (ArrayAccessExpr)init;
            call = setWriteFromArray(aaExpr);
        }
        else if(init instanceof NameExpr){
            NameExpr varable = (NameExpr)init;
            call = setWtriteFromVariable(varable);
        }
        else if(init == null){
            return;
        }
        else{
            call = setWriteFromUndefined(init);
        }
        
        if(call == null){
            return;
        }
        call.getArgs().add(new IntegerLiteralExpr(WriteOperation.VARIABLE+""));

        decl.setInit(setEvalCall(
                decl.getId().toString().replaceAll("\\[\\s*\\]",""),
                call,
                EvalOperation.ASSIGNMENT,
                new int [] {decl.getBeginLine(), decl.getEndLine()}
        ));
    }
    
    private MethodCallExpr setEval(AssignExpr aExpr){
 
        return setEvalCall(
                aExpr.getTarget().toString(), 
                aExpr, 
                EvalOperation.ASSIGNMENT, 
                new int[]{aExpr.getBeginLine(), aExpr.getEndLine()});
    }
    
    /*
       TRACKING
    */
    
    private boolean isTracked(AssignExpr aExpr){
        Expression target = aExpr.getTarget();
        Expression value = aExpr.getValue();
        
        return (isTrackedArray(target) || isTrackedArray(value));
       
        
    }
    
    private boolean isTrackedArray(Expression expr){
        if((expr instanceof ArrayAccessExpr)){ 
            return isTracked((ArrayAccessExpr)expr);
        }
        if(expr instanceof NameExpr){
            return isTracked((NameExpr)expr);
        }
        return false;      
    }
    
    private boolean isTracked(VariableDeclarator n){
        DataStructure data = isAnnotated(n.getId().getName());
        return (data != null) || isTrackedArray(n.getInit());
    }
    
    private boolean isTracked(ArrayAccessExpr aaExpr){
       return isAnnotated(aaExpr.getName().toString()) != null;
    }
    
    private boolean isTracked(NameExpr nExpr){
        return isAnnotated(nExpr.getName()) != null;
    }
    

    /* 
                WRITE LEVEL
    */
    
    @Override
    public Node visit(AssignExpr n, Object arg) {
       
        MethodCallExpr call = null;
        
        Expression value = n.getValue();
        if(value instanceof ArrayAccessExpr){ 
            call = setWriteFromArray((ArrayAccessExpr)value);
        }else if(value instanceof NameExpr){
            call = setWtriteFromVariable((NameExpr)value);
        }
        else if(value instanceof NullLiteralExpr){
            // change 
            call = setWriteFromUndefined(value);
        }
        else{
            call = setWriteFromUndefined(value);
        }
        
        Expression target = n.getTarget();
        
        if(target instanceof ArrayAccessExpr){
            seWriteToArray((ArrayAccessExpr)target, call);
        }else if(target instanceof NameExpr){
            setWriteToVariable((NameExpr)target, call);
        }else{
            call.getArgs().add(new StringLiteralExpr(WriteOperation.UNDEFINED+""));
        }
       
        if( isTrackedArray(value) || isTrackedArray(target)){
           n.setValue(call); 
        }

        return super.visit(n, IS_ASSIGNMENT);
    }

    
    private MethodCallExpr createWrite(ArrayList<Expression> args){
        return new MethodCallExpr(null, WriteOperation.OPERATION,args);
    }
    
    /*
        UNDEFINED WRITE
    */
    
    private MethodCallExpr setWriteFromUndefined(Expression exp){
        
        ArrayList<Expression> args = new ArrayList<>();
        args.add(new NullLiteralExpr());
        args.add(exp);
        args.add(new IntegerLiteralExpr(WriteOperation.UNDEFINED+""));
        return createWrite(args);
    }
    
    /*
        ARRAY WRITE
    */
    
    private MethodCallExpr setWriteFromArray(ArrayAccessExpr aValueExp){

        DataStructure dataStructure = isAnnotated(aValueExp.getName().toString());
        
        if(dataStructure != null){
            ArrayList<Expression> args = new ArrayList<>(); 
            args.add(new StringLiteralExpr(concatIdentifier(dataStructure.getIdentifier())));
            args.add(aValueExp);
            args.add(new IntegerLiteralExpr(WriteOperation.ARRAY+""));
            return createWrite(args);
        }
  
        return setWriteFromUndefined(aValueExp);
        
    }
    
    
    
    private void seWriteToArray(ArrayAccessExpr aTargetExp, MethodCallExpr call){
        
        DataStructure dataStructure = 
                isAnnotated(aTargetExp.getName().toString());
        if(dataStructure!= null){
            call.getArgs().add(new IntegerLiteralExpr(WriteOperation.ARRAY+""));
        }else{
            call.getArgs().add(new IntegerLiteralExpr(WriteOperation.UNDEFINED+""));
        }

    }
    
    /*
        VARIABLE WRITE
    */
    
    private MethodCallExpr setWtriteFromVariable(NameExpr nExpr){
        ArrayList<Expression> args = new ArrayList<>();  
        args.add(new StringLiteralExpr(concatIdentifier(nExpr.getName())));
        args.add(nExpr);
        args.add(new IntegerLiteralExpr(WriteOperation.VARIABLE+""));
        return createWrite(args);
    }
    
    private void setWriteToVariable(NameExpr nExpr, MethodCallExpr call ){
        call.getArgs().add(
            new IntegerLiteralExpr(WriteOperation.VARIABLE+""));
        
    }
    
    /* 
                READ LEVEL
    */
   
    
    @Override
    public Node visit(ArrayAccessExpr n, Object arg) {
        
        
        if(arg != null && arg instanceof String){
            //System.out.println(arg+" : "+n.toString());
            String str = (String)arg;
            
            if(str.equalsIgnoreCase(SKIP)){
                return super.visit(n, CONTINUE);
            }
        }
        // skip all which are outside of assignments and declarations
        if(arg == null){
            return super.visit(n, arg);
        }
        
        DataStructure dataStructure = isAnnotated(n.getName().toString());
        
        if(dataStructure != null){
            ArrayDataStructure ads = (ArrayDataStructure) dataStructure;
            
            String name = n.getName().toString();
            int dimension = TextParser.countHighLeveOccurences(name,"[", "]");

            ArrayList<Expression> args = new ArrayList<>();
            args.add(new StringLiteralExpr(dataStructure.getIdentifier()));
            args.add(new IntegerLiteralExpr(""+dimension));
            args.add(n.getIndex());
            n.setIndex(new MethodCallExpr(null, "read", args));
          
            if(((String)arg).equalsIgnoreCase(IS_BINARY)){
                return super.visit(setEval(n), SKIP);
            }
            return super.visit(n, arg);
        }
        
        return super.visit(n, arg);
    }
    
    private MethodCallExpr setEval(ArrayAccessExpr aaExpr){
        return setEvalCall(
             new NullLiteralExpr(),
             aaExpr,
             EvalOperation.ARRAY_ACCESS,
             new int[] {aaExpr.getBeginLine(), aaExpr.getEndLine()}
        );
    }

    @Override
    public Node visit(MethodCallExpr n, Object arg) {
        handleArguments(n);
        return super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void handleArguments(MethodCallExpr n){
        
        Method method = methods.get(n.getName());
        if(method != null){
          
            for(Argument argItem : method.annotetedArguments){
               MethodCallExpr call = handleArgument(n.getArgs().get(argItem.position),argItem);
                n.getArgs().set(argItem.position, call);
            }
        }
    }
    
    private MethodCallExpr handleArgument(Expression expr, Argument arg){
        
        DataStructure ds = isAnnotated(expr.toString());
        
        Expression source;
        int writeSourceContext;
        int writeTargetContext = WriteOperation.VARIABLE;
        
        if(ds != null){
            source = new StringLiteralExpr(concatIdentifier(ds.getIdentifier()));
            if(expr instanceof ArrayAccessExpr){
                writeSourceContext = WriteOperation.ARRAY;
            }else{
              writeSourceContext = WriteOperation.VARIABLE;  
            }
            
        }else{
            source = new NullLiteralExpr();
            writeSourceContext = WriteOperation.UNDEFINED;
        }
  
        return setEvalCall(
                arg.name,
                setWriteCall(source, expr, writeSourceContext, writeTargetContext),
                EvalOperation.METHOD_CALL,
                new int[]{expr.getBeginLine(), expr.getEndLine()});
    }
    
    public MethodCallExpr setEvalCall(String target, Expression value, 
            int context, int [] line){
        return setEvalCall(
                new StringLiteralExpr(concatIdentifier(target)),
                value,
                context,
                line
        );
    }
    
    public MethodCallExpr setEvalCall(Expression target, Expression value, 
            int context, int [] line){
        ArrayList<Expression> args = new ArrayList<>();
        args.add(target);
        args.add(value);
        args.add(new IntegerLiteralExpr(context+""));
        args.add(new ArrayCreationExpr(
                new PrimitiveType(PrimitiveType.Primitive.Int), 
                line.length - 1, 
                arrayToExpr(line)
        ));
        
        return new MethodCallExpr(null, EvalOperation.OPERATION, args);
    }
    
    
    public MethodCallExpr setWriteCall(Expression source, Expression value, 
            int sourceType, int targetType){
        ArrayList<Expression> args = new ArrayList<>();
        args.add(source);
        args.add(value);
        args.add(new IntegerLiteralExpr(sourceType+""));
        args.add(new IntegerLiteralExpr(targetType+""));
        return new MethodCallExpr(null, WriteOperation.OPERATION, args);
    }
    
    public ArrayInitializerExpr arrayToExpr(int [] array){
        ArrayList<Expression> expressions = new ArrayList<>();
        for(int i : array){
            expressions.add(new IntegerLiteralExpr(i+""));
        }
        return new ArrayInitializerExpr(expressions);
    }
    
    
    
    

    @Override
    public Node visit(BinaryExpr n, Object arg) {
        return super.visit(n, IS_BINARY); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node visit(UnaryExpr n, Object arg) {
        return super.visit(n, null); //To change body of generated methods, choose Tools | Templates.
    }
    
  /*
    @Override
    public Node visit(BlockComment n, Object arg) {
        return null;
    }

    @Override
    public Node visit(LineComment n, Object arg) {
        return null;
    }
    */
    
    
}
