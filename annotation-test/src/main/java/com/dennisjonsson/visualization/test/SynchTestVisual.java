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

/**
 *
 * @author dennis
 */

public class SynchTestVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
new com.dennisjonsson.log.ast.ASTLogger(
new com.dennisjonsson.log.ast.SourceHeader(
"SynchTestVisual",
"",
new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[][]","a"),com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[]","b"),com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[][]","c")},
com.dennisjonsson.log.DefaultInterpreter.instance()));

    /**
     * @param args the command line arguments
     */
    
    public static int[][] a = eval("a", write(null, new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }, 3, 1), 0);

    
    static int[] b = eval("b", write(null, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 3, 1), 0);

    
    static int[][] c = eval("c", write(null, new int[3][3], 3, 1), 0);

    public static void main(String[] args) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < eval(null, a[read("a", "", 0, i)], 2).length; j++) {
                eval("b[a.length * i + j]", b[read("b", "", 0, a.length * i + j)] = write("a", a[read("a", "", 0, i)][read("a", "", 1, j)], 0, 0), 0);
            }
        }
        eval("a[0]", a[read("a", "", 0, 0)] = write("c", c[read("c", "", 0, 0)], 0, 0), 0);
        /*
        for(int k = 0; k < a.length; k++){
            c[k] = a[k];
        }*/
        /*end visualize*/
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
}public static int[][][] eval(String targetId, int[][][] value, int expressionType){
logger.eval(targetId, new com.dennisjonsson.log.ast.LogUtils<int[][][]>().deepCopy(value), expressionType);
return value;
}
public static int[][][] write(String name, int[][][] value, int sourceType, int targetType ){
logger.write(name, new com.dennisjonsson.log.ast.LogUtils<int[][][]>().deepCopy(value), sourceType, targetType);
return value;
}
public static java.lang.String write(String name, java.lang.String value, int sourceType, int targetType ){
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
