/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.Interpreter;
import com.dennisjonsson.log.AbstractInterpreter;
import com.dennisjonsson.markup.Operation;

/**
 *
 * @author dennis
 */
@Interpreter
public class MyInterpreter extends AbstractInterpreter {

    @Override
    public void interpret(String className, Operation operation) {
       // System.out.println(className+ " " +operation.operation);
    }

    @Override
    public void print(String json) {
       System.out.println("printing!");
    }

    
    
}
