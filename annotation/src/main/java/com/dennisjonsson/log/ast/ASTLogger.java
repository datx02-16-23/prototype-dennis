/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.log.ast;

import com.dennisjonsson.log.AbstractInterpreter;
import com.dennisjonsson.markup.DataStructure;
import com.dennisjonsson.markup.Header;
import com.dennisjonsson.markup.Markup;
import com.dennisjonsson.markup.Operation;
import com.dennisjonsson.markup.Source;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dennis
 */
public class ASTLogger {
    
    public static final String CLASS_NAME = "com.dennisjonsson.log.ast.ASTLogger"; 

    /*
    final ArrayList<LogOperation> operations = 
            new ArrayList<>();*/
    final HashMap<String,LogParser> streamParsers;
    final Markup markup;
    final SourceHeader sourceHeader;
    
    // interpreter
    final AbstractInterpreter interpreter;
   // final LogParser streamParser;
    
    int printCount = 0;
    final int PARTION_SIZE = 100000; 
    
    private static ASTLogger logger;
    
    public static ASTLogger instance(SourceHeader sourceHeader){
        if(logger == null){
            logger = new ASTLogger(sourceHeader);
        }else{
            logger.appendHeader(sourceHeader);
        }
        return logger;
    }
 
    private ASTLogger(SourceHeader sourceHeader) {
        
        streamParsers = new HashMap<>();
        Header header = new Header(new HashMap<String, Source>());
        markup = new Markup(header, new ArrayList<>());
        this.sourceHeader = sourceHeader;
        this.interpreter = sourceHeader.interpreter;
        this.interpreter.addMarkup(sourceHeader.className, markup);

        appendHeader(sourceHeader);
    }
    
    private void appendHeader(SourceHeader sourceHeader){
        
        
         markup.header.sources.put(
                 sourceHeader.className, 
                 new Source(sourceHeader.source)
         );
        
        for( DataStructure ds : sourceHeader.dataStructures){
           System.out.println("adding: "+ds.identifier);
           markup.header.addDataStructure(sourceHeader.className ,ds);
        }
        
        this.streamParsers.put(
                sourceHeader.className,
                new LogParser(
                    sourceHeader.className,
                    new ArrayList<LogOperation>(),
                    markup
                ));
    }
    
    public synchronized void read(String className, String id, int index, int dimension){
        // IndexRead(String identifier, int index, int dimension, String statementId)
        streamParsers.get(className).operations.add(new IndexedReadOperation(id, index, dimension));
    }
    
    public synchronized void write(String className, String name, Object value, int sourceType, int targetType){
        // (String name, String value, String operation, String statementId)
        streamParsers.get(className).operations.add(new WriteOperation(name, value, sourceType, targetType));
    }
    
    public synchronized void eval(String className, String targetId, Object value, int expressionType, int [] line){
        //EvalOperation(String value, String statementId)
        EvalOperation eval = new EvalOperation(targetId, value, expressionType, line);
        streamParsers.get(className).operations.add(eval);
        streamOperation(className, eval);
        
    }
    
    private void streamOperation(String className, EvalOperation op){
        
        streamParsers.get(className).visit(op, streamParsers.get(className).operations.size()-2);
        //this.interpreter.interpret(sourceHeader.className, 
          //      this.streamParser.composer.markup.body.size()-1);
    }
    

    public void print(){
        //parse();
        System.out.println("operations: "+markup.body.size());
        String json = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //Collections.reverse(markup.body);
        json = gson.toJson(markup);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(sourceHeader.printingPath+sourceHeader.className+".json", "UTF-8");
            writer.print(json);
            //Desktop.getDesktop().browse(new URI("http://blog-dennisjonsson.rhcloud.com/visualizer/visualizer.html"));
        } 
        catch (FileNotFoundException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getMessage());
        }/*
        catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        } 
        catch (URISyntaxException ex) {
            Logger.getLogger(ASTLogger.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        finally{
            if(writer != null)
                writer.close();
        }
    }
    
}
