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
import java.util.Arrays;


public class HeapSortVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
new com.dennisjonsson.log.ast.ASTLogger(
new com.dennisjonsson.log.ast.SourceHeader(
"HeapSortVisual",
"",
new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("BINARY_TREE","int[]","a")},
com.dennisjonsson.log.DefaultInterpreter.instance()));

    
    private static int[] a;

    private static int n;

    private static int left;

    private static int right;

    private static int largest;

    public static void buildheap(int[] a) {
        n = a.length - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(a, i);
        }
    }

    public static void maxheap(int[] a, int i) {
        left = 2 * i;
        right = 2 * i + 1;
        if (left <= n && eval(null, a[read("a", "", 0, left)], 2) > eval(null, a[read("a", "", 0, i)], 2)) {
            largest = left;
        } else {
            largest = i;
        }
        if (right <= n && eval(null, a[read("a", "", 0, right)], 2) > eval(null, a[read("a", "", 0, largest)], 2)) {
            largest = right;
        }
        if (largest != i) {
            exchange(i, largest);
            maxheap(a, largest);
        }
    }

    public static void exchange(int i, int j) {
        int t = eval("t", write("a", a[read("a", "", 0, i)], 0, 1), 0);
        eval("a[i]", a[read("a", "", 0, i)] = write("a", a[read("a", "", 0, j)], 0, 0), 0);
        eval("a[j]", a[read("a", "", 0, j)] = write("t", t, 1, 0), 0);
    }

    public static void sort(int[] myarray) {
        eval("a", a = write("myarray", myarray, 1, 1), 0);
        buildheap(a);
        for (int i = n; i > 0; i--) {
            exchange(0, i);
            n = n - 1;
            maxheap(a, 0);
        }
    }

    public static void main(String[] args) {
        int[] numbers = { 5, 6, 12, 2, 1, 11, 10 };
        System.out.println(Arrays.toString(numbers));
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
        print();
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
