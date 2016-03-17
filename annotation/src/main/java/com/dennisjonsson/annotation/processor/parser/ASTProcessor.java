/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation.processor.parser;

import com.dennisjonsson.markup.DataStructure;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dennis
 */
public class ASTProcessor extends SourceProcessor {
    
    private SourceVisitorAdapter adapter;
    private CompilationUnit unit;
    
    public ASTProcessor(String path, String className, ArrayList<DataStructure> dataStructures) {
        super(path, className, dataStructures);

        
    }
    
    public void parseSource(){
        adapter = new SourceVisitorAdapter(dataStructures);
        
        adapter.visit(unit, null);
        
    }

    @Override
    public void loadSource() {
        unit = readFile();
    }
    
   
   CompilationUnit readFile(){
       
       InputStream stream = this.getInputStream(path, className);
       
       CompilationUnit unit = null;
       
        try {
            unit = JavaParser.parse(stream);
        } catch (ParseException ex) {
            Logger.getLogger(ASTProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           try {
               stream.close();
           } catch (IOException ex) {
               Logger.getLogger(ASTProcessor.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
       
  
       return unit;
   }
        
    
}