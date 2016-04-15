/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test.app;

import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.SourcePath;
import com.dennisjonsson.annotation.Visualize;
import com.dennisjonsson.markup.AbstractType;
import com.github.javaparser.ast.Node;

/**
 *
 * @author dennis
 */
@SourcePath(path = "C:/Users/dennis/Documents/NetBeansProjects/" 
        + "annotation-test/src/main/" 
        + "java/com/dennisjonsson/visualization/test/app/")
public class Test1 {

    /**
     * @param args the command line arguments
     */
    
    @Visualize(abstractType = AbstractType.ARRAY)
    public static int [] a = new int[10];
    
    @Visualize(abstractType = AbstractType.ARRAY)
    public static int [] b = new int[]{1,2,3,4,5,6,7,8,9};
    
    public static int c [] = new int [2];
    
    @Visualize(abstractType = AbstractType.ADJACENCY_MATRIX)
    public static int [][] e = new int [10][10];
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        int d = a[1];
        
        a = new int []{1,2,3,4,5,6};
        
        a = b; 
        a[1] = b[1];
        d = a[1];
        a[1] = 1;
        a[2] = d;
        
        d = c[1];
        c[1] = d;
        c[1] = c[0];
        d = d;
        
        a[0] = b[0];
        a[0] = 1;
        a[0] = b[0] + a[1];
        
        e[a[0]][b[0]] = e[c[1]][b[2]];
        
       // Node d = c[1];
       print();
    }
    
    @Print(path = "")
    public static void print(){
    
    }
    
}
