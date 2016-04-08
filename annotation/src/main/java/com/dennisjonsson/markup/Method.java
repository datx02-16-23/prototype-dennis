/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.markup;

import java.util.ArrayList;

/**
 *
 * @author dennis
 */
public class Method {
    
    public final String name;
    public final String className;
    public final String [] arguments;
    public final String signature;
    public final ArrayList<Argument> annotetedArguments;
    
    public final String uniqueSignature;

    public Method(String className, String name, String signature) {
        this.name = name;
        this.className = className;
        this.signature = signature;
        this.arguments = signature.replaceAll("(.*\\()|\\)", "").split(",");
        this.annotetedArguments = new ArrayList<>();
        this.uniqueSignature = className + signature;
    }
    
    public void addArgument(Argument arg){
        arg.method = this;
        annotetedArguments.add(arg);
    }
    
    
    
}
