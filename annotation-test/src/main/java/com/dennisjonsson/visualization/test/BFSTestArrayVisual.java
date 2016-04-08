/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.markup.AbstractType;
import com.dennisjonsson.annotation.Visualize;
import java.util.ArrayList;
import com.dennisjonsson.annotation.SourcePath;


public class BFSTestArrayVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
new com.dennisjonsson.log.ast.ASTLogger(
new com.dennisjonsson.log.ast.SourceHeader("BFSTestArrayVisual","",new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ADJECENCY_MATRIX","int[][]","adjList")}));

    final int size = 20;

    
    int[][] adjList = eval("adjList", write(null, new int[size][size], 3, 1), 0);

    // this is a comment yo
    public void bfs(int start) {
        boolean[] marked = new boolean[size];
        for (int k = 0; k < adjList.length; k++) {
            for (int i = size - 1 - k; i < size - (k / 2); i++) {
                eval("adjList[k][i]", adjList[read("adjList", "", 0, k)][read("adjList", "", 1, i)] = write(null, 1, 3, 0), 0);
            }
            marked[k] = false;
        }
        ArrayList<Integer> left = new ArrayList<Integer>();
        left.add(start);
        int i = 0;
        while (i < left.size()) {
            marked[left.get(i)] = true;
            for (int j = 0; j < eval(null, adjList[read("adjList", "", 0, left.get(i))], 2).length; j++) {
                if (!marked[j] && eval(null, adjList[read("adjList", "", 0, left.get(i))][read("adjList", "", 1, j)], 2) == 1) {
                    left.add(j);
                    marked[j] = true;
                }
            }
            i++;
        }
    }

    public static void main(String[] args) {
        BFSTestArrayVisual bfs = new BFSTestArrayVisual();
        bfs.bfs(0);
        bfs.print();
    /*end visualize*/
    }

    
    public void print() {
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
}public static int[][][] eval(String targetId, int[][][] value, int expressionType){
logger.eval(targetId, new com.dennisjonsson.log.ast.LogUtils<int[][][]>().deepCopy(value), expressionType);
return value;
}
public static int[][][] write(String name, int[][][] value, int sourceType, int targetType ){
logger.write(name, new com.dennisjonsson.log.ast.LogUtils<int[][][]>().deepCopy(value), sourceType, targetType);
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
