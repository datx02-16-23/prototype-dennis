/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.VisualClassPath;
import com.dennisjonsson.annotation.Visualize;
import com.dennisjonsson.markup.AbstractType;

/**
 *
 * @author dennis
 */

public class BubbleSortVisual{
public static com.dennisjonsson.log.Logger logger = 
new com.dennisjonsson.log.Logger(
new String [] {"ARRAY","int[]","intArray"});
       
       static int[] intArray = new int[]{5,90,35,45,150,3};
 
        public static void main(String[] args) {
               
                //create an int array we want to sort using bubble sort algorithm
                
               
                //print array before sorting using bubble sort algorithm
                System.out.println("Array Before Bubble Sort");
                for(int i=0; i < intArray.length; i++){
                        System.out.print(eval("72b3d167-1f5c-438d-b25f-c47f1357656c", intArray[read("intArray",
"72b3d167-1f5c-438d-b25f-c47f1357656c",
0,i)],0) + " ");
                }
               
                //sort an array using bubble sort algorithm
                bubbleSort(intArray);
               
                System.out.println("");
               
                //print array after sorting using bubble sort algorithm
                System.out.println("Array After Bubble Sort");
                for(int i=0; i < intArray.length; i++){
                        System.out.print(eval("f8d7d357-ea39-4ae7-84ff-30e861074713", intArray[read("intArray",
"f8d7d357-ea39-4ae7-84ff-30e861074713",
0,i)],0) + " ");
                }
 
        }
 
        private static void bubbleSort(int[] intArray) {
               
                /*
                 * In bubble sort, we basically traverse the array from first
                 * to array_length - 1 position and compare the element with the next one.
                 * Element is swapped with the next element if the next element is greater.
                 *
                 * Bubble sort steps are as follows.
                 *
                 * 1. Compare array[0] & array[1]
                 * 2. If array[0] > array [1] swap it.
                 * 3. Compare array[1] & array[2]
                 * 4. If array[1] > array[2] swap it.
                 * ...
                 * 5. Compare array[n-1] & array[n]
                 * 6. if [n-1] > array[n] then swap it.
                 *
                 * After this step we will have largest element at the last index.
                 *
                 * Repeat the same steps for array[1] to array[n-1]
                 *  
                 */
               
                int n = intArray.length;
                int temp = 0;
               
                for(int i=0; i < n; i++){
                        for(int j=1; j < (n-i); j++){
                               
                                if(eval("e1ef2845-3495-46da-b39e-db9c23e17bdc", intArray[read("intArray",
"e1ef2845-3495-46da-b39e-db9c23e17bdc",
0,j-1)],0) > eval("4c3f1a26-95fd-4323-a337-02d92761fc03", intArray[read("intArray",
"4c3f1a26-95fd-4323-a337-02d92761fc03",
0,j)],0)){
                                        //swap the elements!
                                        temp = eval("e1a5ad82-b543-49e4-a166-6d591ccc322a", intArray[read("intArray",
"e1a5ad82-b543-49e4-a166-6d591ccc322a",
0,j-1)],logger.endStatement());
                                        eval("27d595f0-03fa-41f2-a78d-485a775c438d", intArray[read("intArray",
"27d595f0-03fa-41f2-a78d-485a775c438d",
0,j-1)] = 
write("intArray", "27d595f0-03fa-41f2-a78d-485a775c438d",eval("27d595f0-03fa-41f2-a78d-485a775c438d", intArray[read("intArray",
"27d595f0-03fa-41f2-a78d-485a775c438d",
0,j)],0)),logger.endStatement());
                                        eval("63f52cfb-14f1-4adf-a808-7b74126aa933", intArray[read("intArray",
"63f52cfb-14f1-4adf-a808-7b74126aa933",
0,j)] = 
write("intArray", "63f52cfb-14f1-4adf-a808-7b74126aa933",temp),logger.endStatement());
                                }
                               
                        }
                }
       
        }
public static int read(String name,String statementId, int dimension, int index){ 
logger.loggRead(name, statementId ,index ,dimension);
return index; 
}
public static int write(String name, String statementId, int value){
logger.logWrite(name, statementId, value+"");
return value;
}
public static int eval(String statementId, int value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static String eval(String statementId, String value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static boolean eval(String statementId, boolean value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static char eval(String statementId, char value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static double eval(String statementId, double value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static float eval(String statementId, float value, int statement){
logger.logEval(statementId, value+"");
return value;
}

public static int[] eval(String statementId, int[] value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static int[][] eval(String statementId, int[][] value, int statement){
logger.logEval(statementId, value+"");
return value;
}
}
