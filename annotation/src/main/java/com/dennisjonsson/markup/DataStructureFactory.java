/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.markup;

/**
 *
 * @author dennis
 */
public class DataStructureFactory {
    
    public static final String CLASS_NAME = "com.dennisjonsson.markup.DataStructureFactory";
    public static final String METHOD =  "getDataStructure";
    
    public static DataStructure getDataStructure(
            String abstractType, String type, String identifier){
        type = type.toLowerCase();
        
        
        switch(abstractType){
            case "ADJACENCY_MATRIX" :
                return createArray(type, identifier, AbstractType.ADJACENCY_MATRIX);
            case "ARRAY" :
                return createArray(type, identifier, AbstractType.ARRAY); 
            case "BINARY_TREE" :
                return createArray(type, identifier, AbstractType.BINARY_TREE);
            default :
                return createPrimitve(type, identifier, abstractType);
                
        }
  
    }
    
    public static DataStructure createPrimitve(String type, String identifier, String abstractType){
        return new PrimitiveDataStructure(abstractType,type, identifier);
    }
    
    private static DataStructure createArray(String type, String identifier, String absType){
       
        return new ArrayDataStructure(absType.toString(), type, identifier);
    }
    
}
