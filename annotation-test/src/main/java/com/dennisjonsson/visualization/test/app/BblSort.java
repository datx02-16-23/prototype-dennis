
package com.dennisjonsson.visualization.test.app;

import com.dennisjonsson.annotation.Include;
import com.dennisjonsson.annotation.Print;
import com.dennisjonsson.annotation.SourcePath;
import com.dennisjonsson.visualization.test.BubbleSort;

@Include(classes = {"com.dennisjonsson.visualization.test.BubbleSort"})

@SourcePath(path = "C:/Users/dennis/Documents/NetBeansProjects/" 
        + "annotation-test/src/main/" 
        + "java/com/dennisjonsson/visualization/test/app/")
public class BblSort {
    
    public static void main(String [] args){
        int [] array = new int [] {14,51,21,61,21,14,12,56,58,47,14,12,25,25,26,23,21,24,27,45,48,46,29,58,39,38,37,1,5,6};
        BubbleSort.sort(array);
        print();
    }
    
    @Print(path="")
    public static void print(){}
}
