/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.Visualize;
import com.dennisjonsson.markup.AbstractType;
import com.dennisjonsson.annotation.SourcePath;


public class TestParseVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
new com.dennisjonsson.log.ast.ASTLogger(
new com.dennisjonsson.log.ast.SourceHeader("TestParseVisual","",new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[]","b"),com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[][]","c"),com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[][][]","d")}));

    /**
     * @param args the command line arguments
     */
    
    public static int[] b = eval("b", write(null, new int[10], 3, 1), 0);

    
    public static int[][] c = eval("c", write(null, new int[10][10], 3, 1), 0);

    
    public static int[][][] d = eval("d", write(null, new int[10][10][10], 3, 1), 0);

    public static int[] un = new int[10];

    public static void main(String[] args) {
        if (eval(null, b[read("b", "", 0, 0)], 2) == 1) /*&& a[0][1] < 2*/
        {
        }
        eval("b[0]", b[read("b", "", 0, 0)] = write("b", b[read("b", "", 0, 1)], 0, 0), 0);
        if (eval(null, b[read("b", "", 0, 0)], 2) == 1) /*&& a[0][1] < 2*/
        {
        }
        eval("c[1][2]", c[read("c", "", 0, 1)][read("c", "", 1, 2)] = write("b", b[read("b", "", 0, 0)], 0, 0), 0);
        int j = 0;
        int k = eval("k", write("b", b[read("b", "", 0, 0)], 0, 1), 0);
        j = k;
        k = 1;
        eval("c[1][2]", c[read("c", "", 0, 1)][read("c", "", 1, 2)] = write(null, 1, 3, 0), 0);
        b[0]++;
        eval("c[1][0]", c[read("c", "", 0, 1)][read("c", "", 1, 0)] = write("d", d[read("d", "", 0, 1)][read("d", "", 1, 2)][read("d", "", 2, 3)], 0, 0), 0);
        for (int i = 0; i < eval(null, c[read("c", "", 0, 0)], 2).length; i++) {
        }
        //b[1] = k;
        print();
    /*end visualize*/
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
}
public static int[][][] eval(String targetId, int[][][] value, int expressionType){
logger.eval(targetId, new com.dennisjonsson.log.ast.LogUtils<int[][][]>().deepCopy(value), expressionType);
return value;
}
public static int[][][] write(String name, int[][][] value, int sourceType, int targetType ){
logger.write(name, new com.dennisjonsson.log.ast.LogUtils<int[][][]>().deepCopy(value), sourceType, targetType);
return value;
}
public static int[][][][] eval(String targetId, int[][][][] value, int expressionType){
logger.eval(targetId, new com.dennisjonsson.log.ast.LogUtils<int[][][][]>().deepCopy(value), expressionType);
return value;
}
public static int[][][][] write(String name, int[][][][] value, int sourceType, int targetType ){
logger.write(name, new com.dennisjonsson.log.ast.LogUtils<int[][][][]>().deepCopy(value), sourceType, targetType);
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
