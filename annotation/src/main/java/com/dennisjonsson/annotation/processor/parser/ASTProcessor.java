/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation.processor.parser;

import com.dennisjonsson.annotation.log.AbstractInterpreter;
import com.dennisjonsson.annotation.log.DefaultInterpreter;
import com.dennisjonsson.annotation.log.ast.ASTLogger;
import com.dennisjonsson.annotation.log.ast.SourceHeader;
import com.dennisjonsson.annotation.markup.Argument;
import com.dennisjonsson.annotation.markup.DataStructure;
import com.dennisjonsson.annotation.markup.Header;
import com.dennisjonsson.annotation.markup.Method;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dennis
 */
public class ASTProcessor extends SourceProcessor {
    
    private MainParser adapter;
    private CompilationUnit unit;
    public HashMap<String, Method> methods;
    public final String fullName;
    public ArrayList<String> includes;
    private static final String SUFFIX = "Visual";
    private String interpreterClass = DefaultInterpreter.class.getName();
    
    ASTProcessor(String className, String fullName) {
        super(className);
        this.fullName = fullName;
        methods = new HashMap<>();
        includes = new ArrayList<>();
    }
    
  
    
    public void setInterpreter(String interpreterClass){
        this.interpreterClass = interpreterClass;
    }
    
    public void setIncludes(ArrayList<String> includes){
        this.includes = includes;
    }
    
    public void addArgument(Argument arg){
        if(methods.get(arg.method.name) == null){
            methods.put(arg.method.name, arg.method);
        }
        methods.get(arg.method.name).addArgument(arg);
    }
    
    public void addMethods(HashMap<String, Method> methods){
        this.methods = methods;
    }
    
    public void addAllArguments(ArrayList<Argument> args){
        for(Argument arg : args){
            addArgument(arg);
        }
    }

    @Override
    public void loadSource() {
        unit = readFile();
    }

   CompilationUnit readFile(){
       
       System.out.println("loading source: "+this.fullPath.toString());
       InputStream stream = this.getInputStream(this.fullPath);
       
       CompilationUnit unit = null;
       
        try {
            unit = JavaParser.parse(stream);
        } catch (ParseException ex) {
            Logger.getLogger(ASTProcessor.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("ASTProcessor: Could not parse file"); 
        }finally{
           try {
               stream.close();
           } catch (IOException ex) {
               Logger.getLogger(ASTProcessor.class.getName()).log(Level.SEVERE, null, ex);
               
           }
        }
        
       return unit;
   }
   
    private void replaceIncludes(TextParser parser){
        for(String incl : includes){
            if(!incl.equalsIgnoreCase(fullName)){
                String classname =incl.replaceAll("(\\w*\\.)", "");
                parser.renameType(classname, classname + SUFFIX);
                System.out.println("include: "+incl+" -> "+(incl + SUFFIX));
            }
        }
    }
    
    private Object [] getSourceLines(String str){
        //Scanner scanner = new Scanner(str);
        ArrayList<String> lines = new ArrayList<>();
        // all empty lines are needed
        final BufferedReader br = new BufferedReader(new StringReader(str));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(ASTProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        while(scanner.hasNext()){
            String n = scanner.next("\\n");
            if(n != null){
                lines.add(n);
            }
            else{
                lines.add(scanner.nextLine());
                return lines.toArray();
            }
            
        }*/
        return lines.toArray();
    }
    
    public String getSourceLinesAsString(String str){
        Object [] lines = getSourceLines(str);
        StringBuilder builder = new StringBuilder();
        builder.append("new String [] { ");
        for(Object line : lines){
            builder.append("\"");
            builder.append(line.toString().replaceAll("\"", "'"));
            builder.append("\",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("}");
        return builder.toString();
    }
    
    public void concatClassName(String className){
        for(DataStructure ds : dataStructures){
            ds.identifier =  className + Header.CONCAT + ds.identifier;
        }
    }

    @Override
    public void processSource(Object arg) {
        
        if(unit == null){
            throw new RuntimeException("ASTPRocessor: CompilationUnit is null");
        }
        
        /*
        if(print == null){
            throw new RuntimeException("ASTPRocessor: No printing method found");
        }*/
       
        String newClass = className + SUFFIX;
        String lines = getSourceLinesAsString(unit.toString());
        
        PreParser pp = new PreParser(methods);
        pp.visit(unit, null);
   
        concatClassName(className);
        adapter = new MainParser(className, dataStructures, getPrintingMethod(), methods);
        adapter.visit(unit, null);

        // textual changes
        TextParser parser = new TextParser(unit.toString());
        parser.renameClass(className, newClass);
        
        String oldClassName = className;
        className = newClass;
        
        parser.removeAnnotations();
        // replace type of included sources
        replaceIncludes(parser);
        parser.insertInterceptorMethods(className, dataStructures);
        parser.insertField("public static "+ASTLogger.class.getName()+" logger = \n"
                +   ASTLogger.class.getName()+".instance("
                +   "new "+SourceHeader.class.getName()+"("
                +   "\""+newClass+"\","
                +   ""+lines+","
                +   getPrintingPath()+","
                +   parser.printDataStructures(dataStructures) +","
                +   "new " + interpreterClass + "()"
                + "));", className);
        source = parser.getSource();
     
        
    }
    
   
   
        
    
}
