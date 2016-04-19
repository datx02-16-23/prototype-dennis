/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test.app;

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
com.dennisjonsson.log.ast.ASTLogger.instance(new com.dennisjonsson.log.ast.SourceHeader("Test1Visual",new String [] { "/*"," * To change this license header, choose License Headers in Project Properties."," * To change this template file, choose Tools | Templates"," * and open the template in the editor."," */","package com.dennisjonsson.visualization.test.app;","","import com.dennisjonsson.annotation.Print;","import com.dennisjonsson.annotation.SourcePath;","import com.dennisjonsson.annotation.Visualize;","import com.dennisjonsson.markup.AbstractType;","import com.github.javaparser.ast.Node;","","/**"," *"," * @author dennis"," */","@SourcePath(path = 'C:/Users/dennis/Documents/NetBeansProjects/' + 'annotation-test/src/main/' + 'java/com/dennisjonsson/visualization/test/app/')","public class Test1 {","","    /**","     * @param args the command line arguments","     */","    @Visualize(abstractType = AbstractType.ARRAY)","    public static int[] a = new int[10];","","    @Visualize(abstractType = AbstractType.ARRAY)","    public static int[] b = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };","","    public static int c[] = new int[2];","","    @Visualize(abstractType = AbstractType.ADJACENCY_MATRIX)","    public static int[][] e = new int[10][10];","","    public static void main(String[] args) {","        // TODO code application logic here","        int d = a[1];","        a = new int[] { 1, 2, 3, 4, 5, 6 };","        a = b;","        a[1] = b[1];","        d = a[1];","        a[1] = 1;","        a[2] = d;","        d = c[1];","        c[1] = d;","        c[1] = c[0];","        d = d;","        a[0] = b[0];","        a[0] = 1;","        a[0] = b[0] + a[1];","        e[a[0]][b[0]] = e[c[1]][b[2]];","        // Node d = c[1];","        print();","    }","","    @Print(path = '')","    public static void print() {","    }","}"},"",new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("array","int[]","a"),com.dennisjonsson.markup.DataStructureFactory.getDataStructure("array","int[]","b"),com.dennisjonsson.markup.DataStructureFactory.getDataStructure("adjacencymatrix","int[][]","e")},new com.dennisjonsson.visualization.test.MyInterpreter()));

    /**
     * @param args the command line arguments
     */
    
    public static int[] a = eval("a", write(null, new int[10], 3, 1), 0, new int[] { 28, 28 });

    
    public static int[] b = eval("b", write(null, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 1), 0, new int[] { 31, 31 });

    public static int c[] = new int[2];

    
    public static int[][] e = eval("e", write(null, new int[10][10], 3, 1), 0, new int[] { 36, 36 });

    public static void main(String[] args) {
        // TODO code application logic here
        int d = eval("d", write("a", a[read("a", 0, 1)], 0, 1), 0, new int[] { 41, 41 });
        eval("a", a = write(null, new int[] { 1, 2, 3, 4, 5, 6 }, 3, 1), 0, new int[] { 43, 43 });
        eval("a", a = write("b", b, 1, 1), 0, new int[] { 45, 45 });
        eval("a[1]", a[read("a", 0, 1)] = write("b", b[read("b", 0, 1)], 0, 0), 0, new int[] { 46, 46 });
        eval("d", d = write("a", a[read("a", 0, 1)], 0, 1), 0, new int[] { 47, 47 });
        eval("a[1]", a[read("a", 0, 1)] = write(null, 1, 3, 0), 0, new int[] { 48, 48 });
        eval("a[2]", a[read("a", 0, 2)] = write("d", d, 1, 0), 0, new int[] { 49, 49 });
        d = c[1];
        c[1] = d;
        c[1] = c[0];
        d = d;
        eval("a[0]", a[read("a", 0, 0)] = write("b", b[read("b", 0, 0)], 0, 0), 0, new int[] { 56, 56 });
        eval("a[0]", a[read("a", 0, 0)] = write(null, 1, 3, 0), 0, new int[] { 57, 57 });
        eval("a[0]", a[read("a", 0, 0)] = write(null, eval(null, b[read("b", 0, 0)], 2, new int[] { 58, 58 }) + eval(null, a[read("a", 0, 1)], 2, new int[] { 58, 58 }), 3, 0), 0, new int[] { 58, 58 });
        eval("e[a[0]][b[0]]", e[read("e", 0, a[read("a", 0, 0)])][read("e", 1, b[read("b", 0, 0)])] = write("e", e[read("e", 0, c[1])][read("e", 1, b[read("b", 0, 2)])], 0, 0), 0, new int[] { 60, 60 });
        // Node d = c[1];
        print();
    }

    
    public static void print() {
        logger.print();
    }

public static int eval( String targetId, int value, int expressionType, int [] line){
logger.eval("Test1Visual", targetId, value, expressionType, line);
return value;
}
public static int write(String name, int value, int sourceType, int targetType ){
logger.write("Test1Visual", name, value, sourceType, targetType);
return value;
}
public static int[] eval( String targetId, int[] value, int expressionType, int [] line){
logger.eval("Test1Visual", targetId, java.util.Arrays.copyOf(value,value.length), expressionType, line);
return value;
}
public static int[] write(String name, int[] value, int sourceType, int targetType ){
logger.write("Test1Visual", name, java.util.Arrays.copyOf(value,value.length), sourceType, targetType);
return value;
}
public static int[][] eval( String targetId, int[][] value, int expressionType, int [] line){
logger.eval("Test1Visual", targetId, new com.dennisjonsson.log.ast.LogUtils<int[][]>().deepCopy(value), expressionType, line);
return value;
}
public static int[][] write(String name, int[][] value, int sourceType, int targetType ){
logger.write("Test1Visual", name, new com.dennisjonsson.log.ast.LogUtils<int[][]>().deepCopy(value), sourceType, targetType);
return value;
}
public static int read(String name,int dimension, int index ){ 
logger.read("Test1Visual", name ,index ,dimension);
return index; 
}
}
