/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.markup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dennis
 */
public class Source {
    
    public final ArrayList<String> lines;
    public final HashMap<String, DataStructure> annotatedVariables;

    public Source(ArrayList<String> lines, HashMap<String, DataStructure> annotatedVariables) {
        this.lines = lines;
        this.annotatedVariables = annotatedVariables;
    }
    
    public void addDataStructure(DataStructure dataStructure){
        this.annotatedVariables.put(dataStructure.getIdentifier(), 
                dataStructure);
    }

}
