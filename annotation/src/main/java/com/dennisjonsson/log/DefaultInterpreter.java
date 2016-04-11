/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.log;

import com.dennisjonsson.markup.Operation;
import com.dennisjonsson.markup.Read;
import com.dennisjonsson.markup.Write;

/**
 *
 * @author dennis
 */
/*
        Interpreter classes should have the following properties:
            extend: com.dennisjonsson.log.AbstractInterpreter
            method: public static AbstractInterpreter instance();
*/
public class DefaultInterpreter extends AbstractInterpreter {
    
    public static AbstractInterpreter interperter;
    
    public static AbstractInterpreter instance(){
        if(interperter == null){
            interperter = new DefaultInterpreter();
        }
        return interperter;
    }

    private DefaultInterpreter() {
    }

    @Override
    public void interpret(String className, int position) {
        Operation op = this.classes.get(className).body.get(position);
        
        if(op.operation.equalsIgnoreCase(Write.OPERATION)){
            Write write = (Write)op;
            // do your work here
            System.out.println(Write.OPERATION + " to "+write.getTarget().getId());
        }
        else if(op.operation.equalsIgnoreCase(Read.OPERATION)){
            Read read = (Read)op;
            System.out.println(Read.OPERATION + " from "+read.getSource().getId());
            // do your work here
        }
        
    }
    
}
