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
import com.github.javaparser.ast.Node;

/**
 *
 * @author dennis
 */

public class Test1Visual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
new com.dennisjonsson.log.ast.ASTLogger(
new String [] {"ARRAY","int[]","a","ARRAY","int[]","b"},"");

    /**
     * @param args the command line arguments
     */
    
    public static int[] a = eval("a", write(null, new int[10], 3, 1), 0);

    
    public static int[] b = eval("b", write(null, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 1), 0);

    public static int c[] = new int[2];

    public static void main(String[] args) {
        // TODO code application logic here
        int d = eval("d", write("a", a[read("a", "", 0, 1)], 0, 1), 0);
        eval("a", a = write(null, new int[] { 1, 2, 3, 4, 5, 6 }, 3, 1), 0);
        eval("a", a = write("b", b, 1, 1), 0);
        eval("a[1]", a[read("a", "", 0, 1)] = write("b", b[read("b", "", 0, 1)], 0, 0), 0);
        eval("d", d = write("a", a[read("a", "", 0, 1)], 0, 1), 0);
        eval("a[1]", a[read("a", "", 0, 1)] = write(null, 1, 3, 0), 0);
        eval("a[2]", a[read("a", "", 0, 2)] = write("d", d, 1, 0), 0);
        d = c[1];
        c[1] = d;
        c[1] = c[0];
        d = d;
        // Node d = c[1];
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
logger.eval(targetId, value, expressionType);
return value;
}
public static int[] write(String name, int[] value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
return value;
}public static int[][] eval(String targetId, int[][] value, int expressionType){
logger.eval(targetId, value, expressionType);
return value;
}
public static int[][] write(String name, int[][] value, int sourceType, int targetType ){
logger.write(name, value, sourceType, targetType);
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
