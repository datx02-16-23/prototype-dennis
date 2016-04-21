/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation.markup;

import java.util.ArrayList;

/**
 *
 * @author dennis
 */
public class Method {
    
    public final String name;
    public final String className;
    public final String signature;
    public final ArrayList<Argument> annotetedArguments;
    
    public final String uniqueSignature;

    public Method(String className, String signature) {
        this.name = signature.replaceAll("\\(.*\\)", "").trim();
        
        this.className = className;
        this.signature = signature;
        this.annotetedArguments = new ArrayList<>();
        this.uniqueSignature = className + signature;
    }
    
    public void addArgument(Argument arg){
        arg.method = this;
        annotetedArguments.add(arg);
    }
    
    
}
