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
import java.util.Arrays;

/**
 *
 * @author dennis
 */

public class QuickSortVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
new com.dennisjonsson.log.ast.ASTLogger(
new com.dennisjonsson.log.ast.SourceHeader("QuickSortVisual","",new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[]","arr")}));

    public static void main(String[] args) {
        int[] x = { 9, 2, 4, 7, 3, 7, 10 };
        System.out.println(Arrays.toString(x));
        int low = 0;
        int high = x.length - 1;
        quickSort(eval("arr", write(null, x, 3, 1), 3), low, high);
        print();
        System.out.println(Arrays.toString(x));
    }

    
    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;
        if (low >= high)
            return;
        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = eval("pivot", write("arr", arr[read("arr", "", 0, middle)], 0, 1), 0);
        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (eval(null, arr[read("arr", "", 0, i)], 2) < pivot) {
                i++;
            }
            while (eval(null, arr[read("arr", "", 0, j)], 2) > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = eval("temp", write("arr", arr[read("arr", "", 0, i)], 0, 1), 0);
                eval("arr[i]", arr[read("arr", "", 0, i)] = write("arr", arr[read("arr", "", 0, j)], 0, 0), 0);
                eval("arr[j]", arr[read("arr", "", 0, j)] = write("temp", temp, 1, 0), 0);
                i++;
                j--;
            }
        }
        // recursively sort two sub parts
        if (low < j)
            quickSort(eval("arr", write("arr", arr, 1, 1), 3), low, j);
        if (high > i)
            quickSort(eval("arr", write("arr", arr, 1, 1), 3), i, high);
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
}public static String write(String name, String value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static String eval(String targetId, String value, int expressionType){
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
public static Object write(String name, Object value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static Object eval(String targetId, Object value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
}
