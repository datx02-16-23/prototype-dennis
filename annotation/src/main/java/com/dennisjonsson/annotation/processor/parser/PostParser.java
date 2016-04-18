/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation.processor.parser;

import com.dennisjonsson.log.ast.LogUtils;
import com.dennisjonsson.markup.DataStructure;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author dennis
 */
public class PostParser extends ModifierVisitorAdapter {
    
    public final String className;
    public final ArrayList<DataStructure> dataStructures;
    
    ArrayList<String> types;
        private ArrayList<String> primitives = 
                new ArrayList<>();
        private ArrayList<String> looseTypes = 
                new ArrayList<>(Arrays.asList("int", "java.lang.String", "boolean", "char", "double", "float", "java.lang.Object"));
        
    ArrayList<Statement> classBody;

    public PostParser(String className, ArrayList<DataStructure> dataStructures) {
        this.className = className;
        this.dataStructures = dataStructures;
    }

    @Override
    public Node visit(ClassOrInterfaceDeclaration n, Object arg) {
        
        
        return super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node visit(ClassExpr n, Object arg) {
        
        return super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public Node visit(BlockStmt n, Object arg) {
        if (n.getParentNode() instanceof ClassOrInterfaceDeclaration){
            ClassOrInterfaceDeclaration decl = 
                    (ClassOrInterfaceDeclaration)n.getParentNode();
            if(decl.getName().equalsIgnoreCase(className)){
                classBody = (ArrayList<Statement>)n.getStmts();
                insertMethods();
            }
        }
        return super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    private void insertMethods(){
        for(DataStructure ds : dataStructures){
            insertMethodsFor(ds);
        }
    }

    private void insertMethodsFor(DataStructure dStruct){
        
        String originalType = dStruct.getType();
            String cleanType = fixClassTypes(originalType);
            String primitiveType = cleanType;
            // check type already exists as method
            for(String type : types){
                    if(type.equalsIgnoreCase(primitiveType)){
                            return;
                    }
            }
            
            String result = null;

            if(dStruct.getType().contains("[")){
                primitiveType = primitiveType.replaceAll("(\\[|\\])", "");
                getArrayEvalsAndWrites(
                        countDimension(dStruct.getType()),
                        primitiveType
                    );
            }else{
                getWriteMethod(primitiveType,0);
                getEval(primitiveType,0);
            }
            
            types.add(primitiveType);
            types.add(cleanType);
            types.add(originalType);
    }
    
     public String fixClassTypes(String type){
            if(!looseTypes.contains(type.replaceAll("(\\[|\\])", "")) || type.contains(".")){
                int i = type.lastIndexOf(".") + 1;
                String object = type.substring(i,i+1).toUpperCase()+type.substring(i+1,type.length());
                return type.substring(0,i)+object;
            }
            return type;
            
        }
    
    public boolean isPrimitive(String primitive){
            for(String str : primitives){
               if(str.equalsIgnoreCase(primitive)){
                   return true;
               }
            }
            return false;
        }
    
    public String getReadMethod(){
        

            return "public static int read("
                    + "String name,"
                    + "int dimension, "
                    + "int index){ "
                    + "\nlogger.read(\""+className+"\", name ,index ,dimension);\n"

                    + "return index; \n}\n";
	}
	
	public String getWriteMethod(String primitiveType, int dimension){
            return "public static "+primitiveType+" write(String name, "+primitiveType+" value, int sourceType, int targetType ){\n"
                       
                        + "logger.write(\""+className+"\", name, "+getValue(dimension,primitiveType,"value")+", sourceType, targetType);\n"
                        + "return value;\n"
                        + "}\n";
	}
        
        public String getEval(String primitiveType, int dimension){
            return "public static "+primitiveType+" eval( String targetId, "+primitiveType+" value, int expressionType){"
                    + "\n"
                    + "logger.eval(\""+className+"\", targetId, "+getValue(dimension,primitiveType,"value")+", expressionType);\n"
                    + "return value;\n"
                    + "}\n";
        }
        
        public String getValue(int dimension, String type, String name){
            String value = name;
            if(dimension > 1){
                 value = "new "+LogUtils.CLASS_NAME+"<"+type+">()."+LogUtils.COPY+"("+name+")";
            }
            if(dimension == 1){
                value = "java.util.Arrays.copyOf("+name+","+name+".length)";
            }
            return value;
        }
        
        
        
          public int countDimension(String type){
            int i = 0; 
            int j = 0;
            
            while(i != -1){
                i = type.indexOf("[", i+1);
                j++;
            }
            return j;
        }
          
        public String getArrayEvalsAndWrites(int dimensions, String primitiveType){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i <= dimensions; i++){
                if(!isPrimitive(primitiveType)){
                    builder.append(getEval(primitiveType, i));
                    builder.append(getWriteMethod(primitiveType, i));
                    primitives.add(primitiveType);
                    types.add(primitiveType);
                }
                primitiveType = primitiveType + "[]";
            }
             
            return builder.toString();
        }
    
    
    
}
