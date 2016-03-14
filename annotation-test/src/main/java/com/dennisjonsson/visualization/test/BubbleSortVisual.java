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
                        System.out.print(eval("fc0cdd46-db2f-40ed-9381-684c2e89e2e2", intArray[read("intArray",
"fc0cdd46-db2f-40ed-9381-684c2e89e2e2",
0,i)],0) + " ");
                }
               
                //sort an array using bubble sort algorithm
                bubbleSort(intArray);
               
                System.out.println("");
               
                //print array after sorting using bubble sort algorithm
                System.out.println("Array After Bubble Sort");
                for(int i=0; i < intArray.length; i++){
                        System.out.print(eval("03319fae-46b0-412f-8018-6b58f36675a5", intArray[read("intArray",
"03319fae-46b0-412f-8018-6b58f36675a5",
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
                               
                                if(eval("6a629815-5622-41af-9bb0-f3b5e787b079", intArray[read("intArray",
"6a629815-5622-41af-9bb0-f3b5e787b079",
0,j-1)],0) > eval("86ba9bc8-53d4-4721-9754-ef183d9711d8", intArray[read("intArray",
"86ba9bc8-53d4-4721-9754-ef183d9711d8",
0,j)],0)){
                                        //swap the elements!
                                        temp = eval("ef321018-0e04-4430-bf2d-26dc0dd6c7fd", intArray[read("intArray",
"ef321018-0e04-4430-bf2d-26dc0dd6c7fd",
0,j-1)],logger.endStatement());
                                        eval("d6a622ad-14a5-4720-8be5-3e430f88b8e3", intArray[read("intArray",
"d6a622ad-14a5-4720-8be5-3e430f88b8e3",
0,j-1)] = 
write("intArray", "d6a622ad-14a5-4720-8be5-3e430f88b8e3",eval("d6a622ad-14a5-4720-8be5-3e430f88b8e3", intArray[read("intArray",
"d6a622ad-14a5-4720-8be5-3e430f88b8e3",
0,j)],0)),logger.endStatement());
                                        eval("5c2ee7ef-5145-4000-8b12-efc22f59d643", intArray[read("intArray",
"5c2ee7ef-5145-4000-8b12-efc22f59d643",
0,j)] = 
write("intArray", "5c2ee7ef-5145-4000-8b12-efc22f59d643",temp),logger.endStatement());
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
