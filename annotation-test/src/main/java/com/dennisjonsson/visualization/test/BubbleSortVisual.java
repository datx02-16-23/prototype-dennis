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
                        System.out.print(eval("a1c47a4a-780b-4086-aa5b-bfe6d9c754ae", intArray[read("intArray",
"a1c47a4a-780b-4086-aa5b-bfe6d9c754ae",
0,i)],0) + " ");
                }
               
                //sort an array using bubble sort algorithm
                bubbleSort(intArray);
               
                System.out.println("");
               
                //print array after sorting using bubble sort algorithm
                System.out.println("Array After Bubble Sort");
                for(int i=0; i < intArray.length; i++){
                        System.out.print(eval("c646f944-04a9-480b-926c-b201acddb65f", intArray[read("intArray",
"c646f944-04a9-480b-926c-b201acddb65f",
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
                               
                                if(eval("c63c5ce7-3e8f-4266-93c7-c0681614b9ff", intArray[read("intArray",
"c63c5ce7-3e8f-4266-93c7-c0681614b9ff",
0,j-1)],0) > eval("f32d8834-7387-42b4-a98c-db27bfc698f7", intArray[read("intArray",
"f32d8834-7387-42b4-a98c-db27bfc698f7",
0,j)],0)){
                                        //swap the elements!
                                        temp = eval("53deebf1-8651-40f2-9504-df5764f348bf", intArray[read("intArray",
"53deebf1-8651-40f2-9504-df5764f348bf",
0,j-1)],logger.endStatement());
                                        eval("d7f2456f-79a1-4286-9b88-013654fa80f8", intArray[read("intArray",
"d7f2456f-79a1-4286-9b88-013654fa80f8",
0,j-1)] = 
write("intArray", "d7f2456f-79a1-4286-9b88-013654fa80f8",eval("d7f2456f-79a1-4286-9b88-013654fa80f8", intArray[read("intArray",
"d7f2456f-79a1-4286-9b88-013654fa80f8",
0,j)],0)),logger.endStatement());
                                        eval("02944424-68df-4e09-bb36-7fbe3a05331a", intArray[read("intArray",
"02944424-68df-4e09-bb36-7fbe3a05331a",
0,j)] = 
write("intArray", "02944424-68df-4e09-bb36-7fbe3a05331a",temp),logger.endStatement());
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
