/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.log.ast;

/**
 *
 * @author dennis
 */
public class SourceHeader {
    
    public static final String CLASS_NAME = "com.dennisjonsson.log.ast.SourceHeader";
    
    public String className;
    public String printingPath;
    String [] dsArgs;

    public SourceHeader(String className, String printingPath, String[] dsArgs) {
        this.className = className;
        this.printingPath = printingPath;
        this.dsArgs = dsArgs;
    }
    
    
    
}
