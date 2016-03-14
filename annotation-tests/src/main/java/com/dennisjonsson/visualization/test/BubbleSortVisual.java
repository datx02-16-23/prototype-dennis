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
                        System.out.print(eval("1fc13ba0-12f3-4c86-82d1-51638ec9e6b9", intArray[read("intArray",
"1fc13ba0-12f3-4c86-82d1-51638ec9e6b9",
0,i)],0) + " ");
                }
               
                //sort an array using bubble sort algorithm
                bubbleSort(intArray);
               
                System.out.println("");
               
                //print array after sorting using bubble sort algorithm
                System.out.println("Array After Bubble Sort");
                for(int i=0; i < intArray.length; i++){
                        System.out.print(
                                eval("e4aba81d-13d7-450b-af25-c7f8b3532415", 
                                        intArray[
                                                read("intArray","e4aba81d-13d7-450b-af25-c7f8b3532415",
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
                               
                                if(eval("cba78a50-fa7c-497f-b12b-eed43600d2df", intArray[read("intArray",
"cba78a50-fa7c-497f-b12b-eed43600d2df",
0,j-1)],0) > eval("e2ca9b0f-8b39-43da-aa1f-7db54092f0b6", intArray[read("intArray",
"e2ca9b0f-8b39-43da-aa1f-7db54092f0b6",
0,j)],0)){
                                        //swap the elements!
                                        temp = eval("3b754e43-7fac-48f8-895b-ac416dbe23f8", intArray[read("intArray",
"3b754e43-7fac-48f8-895b-ac416dbe23f8",
0,j-1)],logger.endStatement());
                                        eval("f9506b57-5a3d-42fa-b509-e32caf4396f6", intArray[read("intArray",
"f9506b57-5a3d-42fa-b509-e32caf4396f6",
0,j-1)] = 
write("intArray", "f9506b57-5a3d-42fa-b509-e32caf4396f6",eval("f9506b57-5a3d-42fa-b509-e32caf4396f6", intArray[read("intArray",
"f9506b57-5a3d-42fa-b509-e32caf4396f6",
0,j)],0)),logger.endStatement());
                                        eval("e5c987ea-2a94-432e-9887-b1c81e118c1a", intArray[read("intArray",
"e5c987ea-2a94-432e-9887-b1c81e118c1a",
0,j)] = 
write("intArray", "e5c987ea-2a94-432e-9887-b1c81e118c1a",temp),logger.endStatement());
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
