package com.dennisjonsson.visualization.test.app;

import com.dennisjonsson.annotation.Include;
import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.SourcePath;
import com.dennisjonsson.visualization.test.BubbleSortVisual;



public class BblSortVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
com.dennisjonsson.log.ast.ASTLogger.instance(new com.dennisjonsson.log.ast.SourceHeader("BblSortVisual",new String [] { "package com.dennisjonsson.visualization.test.app;","","import com.dennisjonsson.annotation.Include;","import com.dennisjonsson.annotation.Print;","import com.dennisjonsson.annotation.SourcePath;","import com.dennisjonsson.visualization.test.BubbleSort;","","@Include(classes = { 'com.dennisjonsson.visualization.test.BubbleSort' })","@SourcePath(path = 'C:/Users/dennis/Documents/NetBeansProjects/' + 'annotation-test/src/main/' + 'java/com/dennisjonsson/visualization/test/app/')","public class BblSort {","","    public static void main(String[] args) {","        int[] array = new int[] { 14, 51, 21, 61, 21, 14, 12, 56, 58, 47, 14, 12, 25, 25, 26, 23, 21, 24, 27, 45, 48, 46, 29, 58, 39, 38, 37, 1, 5, 6 };","        BubbleSort.sort(array);","        print();","    }","","    @Print(path = '')","    public static void print() {","    }","}"},"",new com.dennisjonsson.markup.DataStructure [] { },new com.dennisjonsson.visualization.test.MyInterpreter()));

    public static void main(String[] args) {
        int[] array = new int[] { 14, 51, 21, 61, 21, 14, 12, 56, 58, 47, 14, 12, 25, 25, 26, 23, 21, 24, 27, 45, 48, 46, 29, 58, 39, 38, 37, 1, 5, 6 };
        com.dennisjonsson.visualization.test.BubbleSortVisual.sort(array);
        print();
    }

    
    public static void print() {
        logger.print();
    }
public static int read(String name,int dimension, int index ){ 
logger.read("BblSortVisual", name ,index ,dimension);
return index; 
}
}
