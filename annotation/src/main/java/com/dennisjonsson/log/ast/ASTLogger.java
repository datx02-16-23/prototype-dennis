/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.log.ast;

import com.dennisjonsson.markup.DataStructure;
import com.dennisjonsson.markup.DataStructureFactory;
import com.dennisjonsson.markup.Header;
import com.dennisjonsson.markup.Markup;
import com.dennisjonsson.markup.Operation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author dennis
 */
public class ASTLogger {
    
    public static final String CLASS_NAME = "com.dennisjonsson.log.ast.ASTLogger"; 

    final ArrayList<LogOperation> operations = 
            new ArrayList<>();
    final Markup markup;
    
    final Stack<Operation> callStack;
    final SourceHeader sourceHeader;

    public ASTLogger(SourceHeader sourceHeader) {
        
        this.sourceHeader = sourceHeader;
        String [] data = sourceHeader.dsArgs;
       
        callStack = new Stack<>();
        Header header = new Header();
        
        for(int i = 0; i < data.length-2; i = i+3){
  
           DataStructure dataStructure = DataStructureFactory
                   .getDataStructure(data[i], data[i+1], data[i+2]);
           header.addDataStructure(dataStructure);
        }
        
        markup = new Markup(header, new ArrayList<>());
        
    }
        
    public void read(String id, String stId, int index, int dimension){
        // IndexRead(String identifier, int index, int dimension, String statementId)
        operations.add(new IndexedReadOperation(id, index, dimension));
    }
    
    public void write(String name, Object value, int sourceType, int targetType){
        // (String name, String value, String operation, String statementId)
        operations.add(new WriteOperation(name, value, sourceType, targetType));
    }
    
    public void eval(String targetId, Object value, int expressionType){
        //EvalOperation(String value, String statementId)
        operations.add(new EvalOperation(targetId, value, expressionType));
    }
    
    private void parse(){
       LogParser parser = new LogParser(
               markup.header.annotatedVariables, 
               operations,
               markup);
       parser.parse();
    }
    
    public void print(){
        parse();
        String json = null;
        Gson gson = new GsonBuilder().create();
        //json = gson.toJson(operations);
        Collections.reverse(markup.body);
        json = gson.toJson(markup);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(sourceHeader.printingPath+sourceHeader.className+".json", "UTF-8");
            writer.print(json);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getMessage());
           // java.util.logging.Logger.getLogger(BFStest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // java.util.logging.Logger.getLogger(BFStest.class.getName()).log(Level.SEVERE, null, ex);
        finally{
            if(writer != null)
                writer.close();
        }
    }
    
}
