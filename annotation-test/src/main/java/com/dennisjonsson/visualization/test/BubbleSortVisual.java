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
                        System.out.print(eval("e7e45ca3-9424-4847-a1f2-75ef21da1e67", intArray[read("intArray",
"e7e45ca3-9424-4847-a1f2-75ef21da1e67",
0,i)],0) + " ");
                }
               
                //sort an array using bubble sort algorithm
                bubbleSort(intArray);
               
                System.out.println("");
               
                //print array after sorting using bubble sort algorithm
                System.out.println("Array After Bubble Sort");
                for(int i=0; i < intArray.length; i++){
                        System.out.print(eval("53f6a71d-9e86-47d1-9dee-75dae946d3c6", intArray[read("intArray",
"53f6a71d-9e86-47d1-9dee-75dae946d3c6",
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
                               
                                if(eval("5fd225c3-9475-4f45-8b8f-ae99a5d0fd92", intArray[read("intArray",
"5fd225c3-9475-4f45-8b8f-ae99a5d0fd92",
0,j-1)],0) > eval("8cb5c413-3fdf-4feb-ab89-805d685c0d23", intArray[read("intArray",
"8cb5c413-3fdf-4feb-ab89-805d685c0d23",
0,j)],0)){
                                        //swap the elements!
                                        temp = eval("64b1da45-ed1d-4a4d-834e-56ef2754d4c6", intArray[read("intArray",
"64b1da45-ed1d-4a4d-834e-56ef2754d4c6",
0,j-1)],logger.endStatement());
                                        eval("3f5347fe-fb4f-4c8e-8f18-28fba4f62b7d", intArray[read("intArray",
"3f5347fe-fb4f-4c8e-8f18-28fba4f62b7d",
0,j-1)] = 
write("intArray", "3f5347fe-fb4f-4c8e-8f18-28fba4f62b7d",eval("3f5347fe-fb4f-4c8e-8f18-28fba4f62b7d", intArray[read("intArray",
"3f5347fe-fb4f-4c8e-8f18-28fba4f62b7d",
0,j)],0)),logger.endStatement());
                                        eval("38c7a509-5e03-45ed-bc00-4aee597ed557", intArray[read("intArray",
"38c7a509-5e03-45ed-bc00-4aee597ed557",
0,j)] = 
write("intArray", "38c7a509-5e03-45ed-bc00-4aee597ed557",temp),logger.endStatement());
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
