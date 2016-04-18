/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test.app.ds;

import com.dennisjonsson.annotation.SourcePath;
import com.dennisjonsson.annotation.Visualize;


public class NodeVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
com.dennisjonsson.log.ast.ASTLogger.instance(new com.dennisjonsson.log.ast.SourceHeader("NodeVisual",new String [] { "/*"," * To change this license header, choose License Headers in Project Properties."," * To change this template file, choose Tools | Templates"," * and open the template in the editor."," */","package com.dennisjonsson.visualization.test.app.ds;","","import com.dennisjonsson.annotation.SourcePath;","import com.dennisjonsson.annotation.Visualize;","","@SourcePath(path = 'C:/Users/dennis/Documents/NetBeansProjects/' + 'annotation-test/src/main/' + 'java/com/dennisjonsson/visualization/test/app/ds/')","public class Node {","","    public int level;","","    public int arity;","","    public int value;","","    @Visualize(abstractType = 'array')","    public int[] children;","","    public  Node(int level, int arity, int value) {","        this.level = level;","        this.arity = arity;","        this.value = value;","        children = new int[arity];","    }","","    public void init() {","        for (int i = 0; i < children.length; i++) {","            children[i] = (int) Math.floor(Math.random() * children.length);","        }","    }","}"},null,new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("array","int[]","children")},com.dennisjonsson.log.DefaultInterpreter.instance()));

    public int level;

    public int arity;

    public int value;

    
    public int[] children;

    public  NodeVisual(int level, int arity, int value) {
        this.level = level;
        this.arity = arity;
        this.value = value;
        eval("children", children = write(null, new int[arity], 3, 1), 0, new int[] { 32, 32 });
    }

    public void init() {
        for (int i = 0; i < children.length; i++) {
            eval("children[i]", children[read("children", 0, i)] = write(null, (int) Math.floor(Math.random() * children.length), 3, 0), 0, new int[] { 39, 39 });
        }
    }

public static int eval( String targetId, int value, int expressionType, int [] line){
logger.eval("NodeVisual", targetId, value, expressionType, line);
return value;
}
public static int write(String name, int value, int sourceType, int targetType ){
logger.write("NodeVisual", name, value, sourceType, targetType);
return value;
}
public static int[] eval( String targetId, int[] value, int expressionType, int [] line){
logger.eval("NodeVisual", targetId, java.util.Arrays.copyOf(value,value.length), expressionType, line);
return value;
}
public static int[] write(String name, int[] value, int sourceType, int targetType ){
logger.write("NodeVisual", name, java.util.Arrays.copyOf(value,value.length), sourceType, targetType);
return value;
}
public static int[][] eval( String targetId, int[][] value, int expressionType, int [] line){
logger.eval("NodeVisual", targetId, new com.dennisjonsson.log.ast.LogUtils<int[][]>().deepCopy(value), expressionType, line);
return value;
}
public static int[][] write(String name, int[][] value, int sourceType, int targetType ){
logger.write("NodeVisual", name, new com.dennisjonsson.log.ast.LogUtils<int[][]>().deepCopy(value), sourceType, targetType);
return value;
}
public static int read(String name,int dimension, int index ){ 
logger.read("NodeVisual", name ,index ,dimension);
return index; 
}
}
