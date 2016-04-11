/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.SourcePath;
import com.dennisjonsson.annotation.Visualize;
import com.dennisjonsson.markup.AbstractType;


public class BubbleSortVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
new com.dennisjonsson.log.ast.ASTLogger(
new com.dennisjonsson.log.ast.SourceHeader(
"BubbleSortVisual",
"",
new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[]","intArray")},
com.dennisjonsson.log.DefaultInterpreter.instance()));

    
    static int intArray[] = eval("intArray", write(null, new int[] { 5, 90, 9, 50, 10, 89, 62, 17, 43, 25, 66, 7, 8, 35, 45, 150, 3 }, 3, 1), 0);

    public static void main(String[] args) {
        //create an int array we want to sort using bubble sort algorithm
        //print array before sorting using bubble sort algorithm
        System.out.println("Array Before Bubble Sort");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(eval(null, intArray[read("intArray", "", 0, i)], 2) + " ");
        }
        //sort an array using bubble sort algorithm
        bubbleSort();
        print();
        System.out.println("");
        //print array after sorting using bubble sort algorithm
        System.out.println("Array After Bubble Sort");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(eval(null, intArray[read("intArray", "", 0, i)], 2) + " ");
        }
    }

    private static void bubbleSort() {
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
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (eval(null, intArray[read("intArray", "", 0, j - 1)], 2) > eval(null, intArray[read("intArray", "", 0, j)], 2)) {
                    //swap the elements!
                    eval("temp", temp = write("intArray", intArray[read("intArray", "", 0, j - 1)], 0, 1), 0);
                    eval("intArray[j - 1]", intArray[read("intArray", "", 0, j - 1)] = write("intArray", intArray[read("intArray", "", 0, j)], 0, 0), 0);
                    eval("intArray[j]", intArray[read("intArray", "", 0, j)] = write("temp", temp, 1, 0), 0);
                }
            }
        }
    }

    
    public static void print() {
        logger.print();
    }
public static int read(String name,String statementId, int dimension, int index){ 
logger.read(name, statementId ,index ,dimension);
return index; 
}
public static int eval(String targetId, int value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
public static int write(String name, int value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static int[] eval(String targetId, int[] value, int expressionType){
logger.eval(targetId, java.util.Arrays.copyOf(value,value.length), expressionType);
return value;
}
public static int[] write(String name, int[] value, int sourceType, int targetType ){
logger.write(name, java.util.Arrays.copyOf(value,value.length), sourceType, targetType);
return value;
}public static int[][] eval(String targetId, int[][] value, int expressionType){
logger.eval(targetId, new com.dennisjonsson.log.ast.LogUtils<int[][]>().deepCopy(value), expressionType);
return value;
}
public static int[][] write(String name, int[][] value, int sourceType, int targetType ){
logger.write(name, new com.dennisjonsson.log.ast.LogUtils<int[][]>().deepCopy(value), sourceType, targetType);
return value;
}public static java.lang.String write(String name, java.lang.String value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static java.lang.String eval(String targetId, java.lang.String value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
public static boolean write(String name, boolean value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static boolean eval(String targetId, boolean value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
public static char write(String name, char value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static char eval(String targetId, char value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
public static double write(String name, double value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static double eval(String targetId, double value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
public static float write(String name, float value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static float eval(String targetId, float value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
public static java.lang.Object write(String name, java.lang.Object value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static java.lang.Object eval(String targetId, java.lang.Object value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
}
