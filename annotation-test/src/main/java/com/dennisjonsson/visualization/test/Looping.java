/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.SourcePath;
import com.dennisjonsson.annotation.VisualizeArg;
import com.dennisjonsson.markup.AbstractType;


/**
 *
 * @author dennis
 */
@SourcePath(path = "C:/Users/dennis/Documents/NetBeansProjects/" 
        + "annotation-test/src/main/" 
        + "java/com/dennisjonsson/visualization/test/")
public class Looping {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int [] arr = new int[20];
        looping(arr,arr,arr);
    }
    
    
    @VisualizeArg(args={AbstractType.ARRAY,AbstractType.ARRAY,AbstractType.ARRAY})
    public static void looping(int [] first, int [] second, int [] third){
        
        for(int i = 0; i < first.length; i++){
            first[i] = i;
            second[i] = i;
            third[i] = i;
        }
        
        for(int i = 0; i < second.length; i++){
            second[i] = i;
        }
        
        for(int i = 0; i < third.length; i++){
            third[i] = i;
        }
        
        print();
    }
    
    @Print(path="")
    public static void print(){}
    
}
