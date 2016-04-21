/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation.processor.parser;

import com.dennisjonsson.annotation.markup.Argument;
import com.dennisjonsson.annotation.markup.DataStructure;
import com.dennisjonsson.annotation.markup.Method;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dennis
 */
public class PreParser extends ModifierVisitorAdapter{
    
    final HashMap<String, Method> methods;

    public PreParser( HashMap<String, Method> methods) {
        this.methods = methods;
    }
    

    @Override
    public Node visit(MethodDeclaration n, Object arg) {
        

        String [] args = n.getDeclarationAsString(false, false,true)
                .replaceAll("(\\w*\\()", "").split(",");
        
        String signature1 = n.getDeclarationAsString(false, false, false);
        int split = signature1.indexOf(" ");
        signature1 = signature1.substring(split).replaceAll(" ", "").trim();
        
        String name = n.getName();
        
        Method method = methods.get(name);
        
        if(method != null){
            String signature2 = method.signature.replaceAll("(\\w*\\.)","");
            if(signature2.equalsIgnoreCase(signature1)){
                for(int i =0; i < args.length; i++){
                    for(Argument argument : method.annotetedArguments){
                        if(args[i].contains(argument.name)){
                            argument.position = i;
                        }
                    }
                }
            }
            
        }
        
        return super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
