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
            case "ADJECENCY_MATRIX" :
                return createAdjecencyMatrix(type, identifier);
            case "ARRAY" :
                return createArray(type, identifier); 
            case "UNKNOWN" :
                return createPrimitve(type, identifier);
                
        }
        throw new RuntimeException("unknown abstract type: "
                + abstractType.toString());
        
    }
    
    public static DataStructure createPrimitve(String type, String identifier){
        return new PrimitiveDataStructure(AbstractType.UNKNOWN.toString(),
            type, identifier);
    }
    
    private static DataStructure createAdjecencyMatrix(String type, String identifier){
       
        ArrayDataStructure dataStructure = new ArrayDataStructure(
                AbstractType.ADJECENCY_MATRIX.toString(), type, identifier);
        return dataStructure;
    }
    
    private static DataStructure createArray(String type, String identifier){
       
        ArrayDataStructure dataStructure = new ArrayDataStructure(
                AbstractType.ARRAY.toString(), type, identifier);
        return dataStructure;
    }
    
    
}
