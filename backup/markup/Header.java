/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.markup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author dennis
 */
public class Header {
    
    public final int version = 2;
    public final HashMap<String, Source> sources;
   /// public final HashMap<String, DataStructure> annotatedVariables;

    public Header(HashMap<String, Source> sources) {
        this.sources = sources;
    }
    
    public void addDataStructure(String className, DataStructure dataStructure){
        this.sources.get(className).addDataStructure(dataStructure);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
}
