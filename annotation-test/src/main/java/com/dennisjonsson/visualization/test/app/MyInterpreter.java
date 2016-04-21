/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test.app;

import com.dennisjonsson.annotation.Interpreter;
import com.dennisjonsson.annotation.log.AbstractInterpreter;
import com.dennisjonsson.annotation.markup.Entity;
import com.dennisjonsson.annotation.markup.Operation;
import com.dennisjonsson.annotation.markup.Read;
import com.dennisjonsson.annotation.markup.Write;

/**
 *
 * @author dennis
 */
@Interpreter
public class MyInterpreter extends AbstractInterpreter {

    @Override
    public void interpret(String className, Operation operation) {
        if(operation instanceof Write){
            Write write = (Write)operation;
            Entity entity = write.getTarget();
            // är i våra exempel alltid Integer
            Object value = write.getValue();
            String id = entity.getId();
        }else if(operation instanceof Read){
            Read read = (Read)operation;
            
        }
       // System.out.println(className+ " " +operation.operation);
    }

    @Override
    public void print(String json) {
       System.out.println("printing!");
    }

    
    
}
