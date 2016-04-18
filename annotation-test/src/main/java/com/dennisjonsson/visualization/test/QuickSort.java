
package com.dennisjonsson.visualization.test;

import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.SourcePath;
import com.dennisjonsson.annotation.VisualizeArg;
import com.dennisjonsson.markup.AbstractType;
import java.util.Arrays;

@SourcePath(path = "C:/Users/dennis/Documents/NetBeansProjects/" 
        + "annotation-test/src/main/" 
        + "java/com/dennisjonsson/visualization/test/")
public class QuickSort {
    
    public static void sort(int[] array){
        quickSort(array, 0 ,array.length-1);
    }
	
    @VisualizeArg(args = {AbstractType.ARRAY})
    public static void quickSort(int[] arr, int low, int high) {
            if (arr == null || arr.length == 0)
                    return;
            if (low >= high)
                    return;
            int middle = low + (high - low) / 2;
            int pivot = arr[middle];
            int i = low, j = high;
            while (i <= j) {
                    while (arr[i] < pivot) {
                            i++;
                    }
                    while (arr[j] > pivot) {
                            j--;
                    }
                    if (i <= j) {
                            int temp = arr[i];
                            arr[i] = arr[j];
                            arr[j] = temp;
                            i++;
                            j--;
                    }
            }
            if (low < j)
                    quickSort(arr, low, j);
            if (high > i)
                    quickSort(arr, i, high);
    }
}