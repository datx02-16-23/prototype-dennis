/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.visualization.test;


import com.dennisjonsson.markup.AbstractType;
import com.dennisjonsson.annotation.VisualClassPath;
import com.dennisjonsson.annotation.Visualize;
import java.util.ArrayList;
/**
 *
 * @author dennis
 */



  // CHANGE THE PATH BEFORE UNCOMMENTING!!!!!



public class BFSTestArrayVisual{
public static com.dennisjonsson.log.Logger logger = 
new com.dennisjonsson.log.Logger(
new String [] {"ADJECENCY_MATRIX","int[][]","adjList"});
    
    final int size = 10; 
    
    int [][] adjList = new int[size][size];
    
    
    public void bfs( int start){
       
        boolean[] marked = new boolean[size];

        for(int k = 0; k < adjList.length; k++){
            for(int i = size-1-k; i < size-(k/2); i++){
                eval("6fb13af0-f783-4bf1-9acb-45752b386c11", adjList[read("adjList",
"6fb13af0-f783-4bf1-9acb-45752b386c11",
0,k)][read("adjList",
"6fb13af0-f783-4bf1-9acb-45752b386c11",
1,i)] = 
write("adjList", "6fb13af0-f783-4bf1-9acb-45752b386c11",1),logger.endStatement());
            }
            
            marked[k] = false;
        }
        
        ArrayList<Integer> left = new ArrayList<Integer>();
        left.add(start);
        int i = 0;
        
        while(i < left.size()){
            
                marked[left.get(i)] = true; 
                for(int j = 0; j < eval("52cb5bc7-d243-44a5-aa2f-eabb4cf5d9b1", adjList[read("adjList",
"52cb5bc7-d243-44a5-aa2f-eabb4cf5d9b1",
0,left.get(i))],0).length; j++){
                    if( !marked[j] && eval("a8020a43-f335-4a5e-bf1e-5e064ce3b70d", adjList[read("adjList",
"a8020a43-f335-4a5e-bf1e-5e064ce3b70d",
0,left.get(i))][read("adjList",
"a8020a43-f335-4a5e-bf1e-5e064ce3b70d",
1,j)],0) == 1){
                        left.add(j);
                        marked[j] = true;
                    }
                }
            
            i++;
        }

    }
    
    public static void main(String [] args){
        BFSTestArrayVisual bfs = new BFSTestArrayVisual();
        bfs.bfs(0);
        
logger.printLog();

    }
public static int read(String name,String statementId, int dimension, int index){ 
logger.loggRead(name, statementId ,index ,dimension);
return index; 
}
public static int write(String name, String statementId, int value){
logger.logWrite(name, statementId, value+"");
return value;
}
public static int eval(String statementId, int value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static String eval(String statementId, String value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static boolean eval(String statementId, boolean value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static char eval(String statementId, char value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static double eval(String statementId, double value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static float eval(String statementId, float value, int statement){
logger.logEval(statementId, value+"");
return value;
}

public static int[] eval(String statementId, int[] value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static int[][] eval(String statementId, int[][] value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static int[][][] eval(String statementId, int[][][] value, int statement){
logger.logEval(statementId, value+"");
return value;
}
}
