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

/**
 *
 * @author dennis
 */

public class LoopingVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
new com.dennisjonsson.log.ast.ASTLogger(
new com.dennisjonsson.log.ast.SourceHeader(
"LoopingVisual",
"",
new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[]","first"),com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[]","second"),com.dennisjonsson.markup.DataStructureFactory.getDataStructure("ARRAY","int[]","third")},
com.dennisjonsson.log.DefaultInterpreter.instance()));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] arr = new int[20];
        looping(eval("first", write(null, arr, 3, 1), 3), eval("second", write(null, arr, 3, 1), 3), eval("third", write(null, arr, 3, 1), 3));
    }

    
    public static void looping(int[] first, int[] second, int[] third) {
        for (int i = 0; i < first.length; i++) {
            eval("first[i]", first[read("first", "", 0, i)] = write("i", i, 1, 0), 0);
            eval("second[i]", second[read("second", "", 0, i)] = write("i", i, 1, 0), 0);
            eval("third[i]", third[read("third", "", 0, i)] = write("i", i, 1, 0), 0);
        }
        for (int i = 0; i < second.length; i++) {
            eval("second[i]", second[read("second", "", 0, i)] = write("i", i, 1, 0), 0);
        }
        for (int i = 0; i < third.length; i++) {
            eval("third[i]", third[read("third", "", 0, i)] = write("i", i, 1, 0), 0);
        }
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
