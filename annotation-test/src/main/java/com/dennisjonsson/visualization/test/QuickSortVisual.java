package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.SourcePath;
import com.dennisjonsson.annotation.VisualizeArg;
import com.dennisjonsson.markup.AbstractType;
import java.util.Arrays;


public class QuickSortVisual{
public static com.dennisjonsson.log.ast.ASTLogger logger = 
com.dennisjonsson.log.ast.ASTLogger.instance(new com.dennisjonsson.log.ast.SourceHeader("QuickSortVisual",new String [] { "package com.dennisjonsson.visualization.test;","","import com.dennisjonsson.annotation.Print;","import com.dennisjonsson.annotation.SourcePath;","import com.dennisjonsson.annotation.VisualizeArg;","import com.dennisjonsson.markup.AbstractType;","import java.util.Arrays;","","@SourcePath(path = 'C:/Users/dennis/Documents/NetBeansProjects/' + 'annotation-test/src/main/' + 'java/com/dennisjonsson/visualization/test/')","public class QuickSort {","","    public static void sort(int[] array) {","        quickSort(array, 0, array.length - 1);","    }","","    @VisualizeArg(args = { AbstractType.ARRAY })","    public static void quickSort(int[] arr, int low, int high) {","        if (arr == null || arr.length == 0)","            return;","        if (low >= high)","            return;","        int middle = low + (high - low) / 2;","        int pivot = arr[middle];","        int i = low, j = high;","        while (i <= j) {","            while (arr[i] < pivot) {","                i++;","            }","            while (arr[j] > pivot) {","                j--;","            }","            if (i <= j) {","                int temp = arr[i];","                arr[i] = arr[j];","                arr[j] = temp;","                i++;","                j--;","            }","        }","        if (low < j)","            quickSort(arr, low, j);","        if (high > i)","            quickSort(arr, i, high);","    }","}"},null,new com.dennisjonsson.markup.DataStructure [] {  com.dennisjonsson.markup.DataStructureFactory.getDataStructure("array","int[]","arr")},com.dennisjonsson.log.DefaultInterpreter.instance()));

    public static void sort(int[] array) {
        quickSort(eval("arr", write(null, array, 3, 1), 3, new int[] { 16, 16 }), 0, array.length - 1);
    }

    
    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        int pivot = eval("pivot", write("arr", arr[read("arr", 0, middle)], 0, 1), 0, new int[] { 26, 26 });
        int i = low, j = high;
        while (i <= j) {
            while (eval(null, arr[read("arr", 0, i)], 2, new int[] { 29, 29 }) < pivot) {
                i++;
            }
            while (eval(null, arr[read("arr", 0, j)], 2, new int[] { 32, 32 }) > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = eval("temp", write("arr", arr[read("arr", 0, i)], 0, 1), 0, new int[] { 36, 36 });
                eval("arr[i]", arr[read("arr", 0, i)] = write("arr", arr[read("arr", 0, j)], 0, 0), 0, new int[] { 37, 37 });
                eval("arr[j]", arr[read("arr", 0, j)] = write("temp", temp, 1, 0), 0, new int[] { 38, 38 });
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(eval("arr", write("arr", arr, 1, 1), 3, new int[] { 44, 44 }), low, j);
        if (high > i)
            quickSort(eval("arr", write("arr", arr, 1, 1), 3, new int[] { 46, 46 }), i, high);
    }

public static int eval( String targetId, int value, int expressionType, int [] line){
logger.eval("QuickSortVisual", targetId, value, expressionType, line);
return value;
}
public static int write(String name, int value, int sourceType, int targetType ){
logger.write("QuickSortVisual", name, value, sourceType, targetType);
return value;
}
public static int[] eval( String targetId, int[] value, int expressionType, int [] line){
logger.eval("QuickSortVisual", targetId, java.util.Arrays.copyOf(value,value.length), expressionType, line);
return value;
}
public static int[] write(String name, int[] value, int sourceType, int targetType ){
logger.write("QuickSortVisual", name, java.util.Arrays.copyOf(value,value.length), sourceType, targetType);
return value;
}
public static int[][] eval( String targetId, int[][] value, int expressionType, int [] line){
logger.eval("QuickSortVisual", targetId, new com.dennisjonsson.log.ast.LogUtils<int[][]>().deepCopy(value), expressionType, line);
return value;
}
public static int[][] write(String name, int[][] value, int sourceType, int targetType ){
logger.write("QuickSortVisual", name, new com.dennisjonsson.log.ast.LogUtils<int[][]>().deepCopy(value), sourceType, targetType);
return value;
}
public static int read(String name,int dimension, int index ){ 
logger.read("QuickSortVisual", name ,index ,dimension);
return index; 
}
}
