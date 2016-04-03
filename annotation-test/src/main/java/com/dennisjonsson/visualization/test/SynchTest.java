/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.Visualize;
import com.dennisjonsson.markup.AbstractType;
import com.dennisjonsson.annotation.SourcePath;

/**
 *
 * @author dennis
 */
@SourcePath(path = "C:/Users/dennis/Documents/NetBeansProjects/" 
        + "annotation-test/src/main/" 
        + "java/com/dennisjonsson/visualization/test/")
public class SynchTest {

    /**
     * @param args the command line arguments
     */
    @Visualize(type = AbstractType.ARRAY)
    public static int [][] a = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
    
    @Visualize(type = AbstractType.ARRAY)
    static int [] b = new int[] {0,0,0,0,0,0,0,0,0};
    
    
    @Visualize(type = AbstractType.ARRAY)
    static int [][] c = new int [3][3];
    
    public static void main(String[] args) {
        
        
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                b[a.length * i + j] = a[i][j];
            }
        }
        
        
        a[0] = c[0];
        
        /*
        for(int k = 0; k < a.length; k++){
            c[k] = a[k];
        }*/
        /*end visualize*/
        print();
    }
    
    @Print(path = "")
    public static void print(){
    } 
    
}
