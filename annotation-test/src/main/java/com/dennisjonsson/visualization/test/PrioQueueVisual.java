package com.dennisjonsson.visualization.test;



import com.dennisjonsson.annotation.VisualClassPath;
import com.dennisjonsson.annotation.Visualize;
import com.dennisjonsson.markup.AbstractType;
import java.util.*;



public class PrioQueueVisual{
public static com.dennisjonsson.log.Logger logger = 
new com.dennisjonsson.log.Logger(
new String [] {"ARRAY","java.lang.Object[]","array"});
	
	interface Comparator{
		int compare(Object elm1, Object elm2);
	}

	private Comparator comparator;

	// Underlying array for storing all elements in heap
        
	private Object[] array;
	// Starting capacity of the array
	private static final int DEFAULT_CAPACITY = 2;
	// Keeps track of the number of elements in the array.
	private int currentSize;

    
  	public PrioQueueVisual(Comparator comparator){
	      	
		array = new Object[DEFAULT_CAPACITY+1];
		currentSize = 0;
		this.comparator = comparator;
   	 }

	public PrioQueueVisual(Comparator comparator, Object [] Array){
		array = Array;
		currentSize = Array.length;
		this.comparator = comparator;
		buildHeap();
   	 }

	/*
		resets the queue to a previous size
	*/
	public void reset(int size){
		if(eval("4e187db1-6fa4-40a0-8f7b-e856acc5d4a8", array[read("array",
"4e187db1-6fa4-40a0-8f7b-e856acc5d4a8",
0,size-1)],0) != null){
			currentSize = size;
			buildHeap();
		}
	}

	/*
		removes all from a certain index
	*/
	public void decrease(int size){
		int oldSize = currentSize;
		currentSize = currentSize-size;
		for(int i = currentSize; i < oldSize; i++){
			eval("57f1b0da-5467-4619-9fc5-b7fc4b95c1cb", array[read("array",
"57f1b0da-5467-4619-9fc5-b7fc4b95c1cb",
0,i)] = 
write("array", "57f1b0da-5467-4619-9fc5-b7fc4b95c1cb",null),logger.endStatement());
		}

		
	}


	private void buildHeap(){
		for (int k = size()/2; k > 0; k--){
			shiftDown(k);
		}
	}
    
	/**
	* 
	* @return first element in heap
	*/
    
    	public void clear(){
	    	array = new Object[DEFAULT_CAPACITY+1];
	    	currentSize = 0;
   	}
    
    	public Object top(){
		if(currentSize <= 0)
		    throw new RuntimeException("not possible");
		return (Object)eval("f94adae8-3f5f-4693-b2cc-d7051f338629", array[read("array",
"f94adae8-3f5f-4693-b2cc-d7051f338629",
0,0)],logger.endStatement());
   	}
   	 
    	public Object bottom(){
	    	if(size() <= 0)
	    		throw new RuntimeException("not possible, size too small");
	    	return (Object)eval("d9362a88-6685-44ae-bb7a-e089939cd9ef", array[read("array",
"d9362a88-6685-44ae-bb7a-e089939cd9ef",
0,size() - 1)],logger.endStatement());
    	}

	public Object[] toArray(){
		Object [] arr = new Object[currentSize];
		System.arraycopy(array,0,arr,0,currentSize);
		return arr;
	}

	public Object[] getSorted(){

		Object [] arr = new Object [currentSize];
		int index = 0;
		while(size() > 0){
			arr[index] = remove();
			index ++;
		}
		return arr;
		
	}

	/**
	* Inserts an new object into the heap
	 * Time complexity is O(log n), O(n) when it needs to be extended.
	* @param x object to inserted
	*/
    	public void add(Object x){
	
		//assert invariant() : showHeap();
		
		if(currentSize + 1 > array.length )
		    doubleArray();
		eval("12679480-8cd3-4cd8-a420-3a0d6e3e17f6", array[read("array",
"12679480-8cd3-4cd8-a420-3a0d6e3e17f6",
0,currentSize)] = 
write("array", "12679480-8cd3-4cd8-a420-3a0d6e3e17f6",x),logger.endStatement());
		int index = shiftUp(currentSize);
		currentSize++;
		
		//assert invariant() : showHeap();
    	}
    
	/**
	* removes smallest element in heap
	 * Time complexity is O(log n)
	* @return smallest element in heap
	*/
    	public Object remove(){
		if(currentSize <= 0){
		    throw new RuntimeException("Cannot remove from an empty heap!");
		}
		//assert invariant() : showHeap();	
		// move last element to the top
       	        currentSize --;
		Object firstElm = (Object)eval("096d34f8-597b-4694-86b6-7cfe6ab560f0", array[read("array",
"096d34f8-597b-4694-86b6-7cfe6ab560f0",
0,0)],logger.endStatement());
		Object lastElm = (Object)eval("94ed1398-2a76-47a6-93be-c834b5ab55d9", array[read("array",
"94ed1398-2a76-47a6-93be-c834b5ab55d9",
0,currentSize)],logger.endStatement());
		// tweak
		eval("55594ba3-5c86-489e-8d87-b88ee14836c9", array[read("array",
"55594ba3-5c86-489e-8d87-b88ee14836c9",
0,currentSize)] = 
write("array", "55594ba3-5c86-489e-8d87-b88ee14836c9",firstElm),logger.endStatement());
	
		if(currentSize > 0){
			eval("aa0c9bed-103c-42ce-b582-ec4f48e19b97", array[read("array",
"aa0c9bed-103c-42ce-b582-ec4f48e19b97",
0,0)] = 
write("array", "aa0c9bed-103c-42ce-b582-ec4f48e19b97",lastElm),logger.endStatement());
			int index = shiftDown(0);
		}
		//assert invariant() : showHeap();
		return firstElm;
    	}
    
	/**
	* Internal method for shifting an element down the heap
	 * Time complexity is O(log n)
	* @param index starting index
	* @return final index
	*/
   	 private int shiftDown(int index){
	    	Object x = eval("5c4fc620-61cf-4801-a19e-917bb121af3f", array[read("array",
"5c4fc620-61cf-4801-a19e-917bb121af3f",
0,index)],logger.endStatement());
		int child;
		while(index*2 + 1 <currentSize){
		    child = index*2 + 1;
		    if(child + 1 < currentSize && 
		            comparator.compare(eval("df71f891-eda7-4ec2-ad5a-020869d55569", array[read("array",
"df71f891-eda7-4ec2-ad5a-020869d55569",
0,child)],0),eval("3386c367-2d22-4491-be86-f94d37285d5b", array[read("array",
"3386c367-2d22-4491-be86-f94d37285d5b",
0,child+1)],0))>0){
		        child++;
		    }if(comparator.compare(x,eval("d3b6b933-e3eb-4739-92c0-049f5364eda8", array[read("array",
"d3b6b933-e3eb-4739-92c0-049f5364eda8",
0,child)],0))>0){
		        eval("0fd9ce8f-9067-4b3d-a64f-4031d7ab1e5a", array[read("array",
"0fd9ce8f-9067-4b3d-a64f-4031d7ab1e5a",
0,index)] = 
write("array", "0fd9ce8f-9067-4b3d-a64f-4031d7ab1e5a",(Object)eval("0fd9ce8f-9067-4b3d-a64f-4031d7ab1e5a", array[read("array",
"0fd9ce8f-9067-4b3d-a64f-4031d7ab1e5a",
0,child)],0)),logger.endStatement());
		        index = child;
		    }else
		        break;
		}
		eval("f8affd5d-3af4-463a-8717-1f2e5aa31777", array[read("array",
"f8affd5d-3af4-463a-8717-1f2e5aa31777",
0,index)] = 
write("array", "f8affd5d-3af4-463a-8717-1f2e5aa31777",x),logger.endStatement());
		return index;
  	  }
    
	/**
	* Internal method for shifting an element up the heap
	 * Time complexity is O(log n) (worst case)
	* @param index starting index
	* @return final index where the element ended up
	*/
    	private int shiftUp(int index){
		int parent;
		Object x = eval("e4a8763c-7c2d-417d-a060-028f7a8c127c", array[read("array",
"e4a8763c-7c2d-417d-a060-028f7a8c127c",
0,index)],logger.endStatement());
		while(index>0){
		    if(index%2==0)
		        parent = (index-2)/2;
		    else
		        parent = (index - 1)/2;
		    
		    if(parent>=0 && 
		            comparator.compare(x,eval("46e5bf2c-4ca0-4128-84f3-17047235ac60", array[read("array",
"46e5bf2c-4ca0-4128-84f3-17047235ac60",
0,parent)],0))<0){
		        eval("97c6e6c2-985c-4df4-913a-03d375a4246a", array[read("array",
"97c6e6c2-985c-4df4-913a-03d375a4246a",
0,index)] = 
write("array", "97c6e6c2-985c-4df4-913a-03d375a4246a",(Object)eval("97c6e6c2-985c-4df4-913a-03d375a4246a", array[read("array",
"97c6e6c2-985c-4df4-913a-03d375a4246a",
0,parent)],0)),logger.endStatement());
				
		        index = parent;
		    }else
		        break;
		}
		eval("867f2555-77d8-45ea-90e7-a7ce5a53aaaa", array[read("array",
"867f2555-77d8-45ea-90e7-a7ce5a53aaaa",
0,index)] = 
write("array", "867f2555-77d8-45ea-90e7-a7ce5a53aaaa",x),logger.endStatement());
		return index;
    	}
    
	/**
	* Internal method for extending the array when it is full
	 * Time complexity is O(n)
	*/
    	private void doubleArray(){
	    	Object[] newArray = new Object[array.length * 2];
		for( int i = 0; i < array.length; i++ )
		    newArray[i] = eval("0b126b26-e552-4184-b294-c7bedee3fb3c", array[read("array",
"0b126b26-e552-4184-b294-c7bedee3fb3c",
0,i)],logger.endStatement());
		array = newArray;
    	}
    
    	public int size(){
      	  return currentSize;
    	}

	@Override
	public String toString() {
		String str = "";
		for(int i =0; i < currentSize; i++){
		    str += eval("5963c42e-fec0-408c-8e40-eb52013f4800", array[read("array",
"5963c42e-fec0-408c-8e40-eb52013f4800",
0,i)],0).toString();
		    if(i<currentSize - 1)
		        str += ", ";
		}
		
		return str;
    	}
	
	/*
		INVARIANT
		looks through the heap recursively before and after add, 
		remove and update makes any changes the heap
	*/
    
	/**
	* Internal method for checking the heap order invariant
	* Looks through the heap starting from the root
	*/
	private boolean invariant(){
		return lookThroughHeap(0);
	}
	
	/**
	* Internal method used by the invariant.
	* Recursively looks through the heap, starting from a given index
	* @param index index to start from
	* @return true if the heap order is kept below the given node index
	*/
	public boolean lookThroughHeap(int index){
		int child = index * 2 + 1;
		if(child < currentSize){
			if(!checkChildren(index,child))
				return false;
			else{
				child ++;
				if(child < currentSize)
					return checkChildren(index,child);
				else
					return true;
			}
		}else
			return true;
		
	}
	
	/**
	* Internal method used by the invariant
	* Compares a given child against its parent.
	* If the heap order is kept, it continues checking the heap order below the child.
	* @param parent index of parent
	* @param child index of child
	* @return true if the heap order is kept below the parent
	*/
	private boolean checkChildren(int parent, int child){
		if(comparator.compare(eval("8d526943-dc93-4da3-9644-3257c34c30a6", array[read("array",
"8d526943-dc93-4da3-9644-3257c34c30a6",
0,parent)],0),eval("8ff5e246-3809-4337-9b5c-18288345981a", array[read("array",
"8ff5e246-3809-4337-9b5c-18288345981a",
0,child)],0)) <= 0)
			return lookThroughHeap(child);
		else
			return false;
	}

	/**
	* Internal method used by the invariant.
	* prints out the heap.
	*/
	private String showHeap(){
			// TODO: return description of heap contents.
			return toString();
	}
        
        public static void main(String [] args){
            Integer [] integers = new Integer[20];
            for(int i = 0; i < integers.length; i++){
                integers[i] = (int)(Math.random()* 100);
            }
            PrioQueueVisual p = new PrioQueueVisual(new Comparator(){
                @Override
                public int compare(Object elm1, Object elm2) {
                    Integer i1 = (Integer)elm1;
                    Integer i2 = (Integer)elm2;
                    if(i1 < i2){
                        return -1;
                    }
                    if(i1 > i2){
                        return 1;
                    }
                    return 0;
                }
            
            },
            integers);
            
            
logger.printLog();

        }
        
        

public static int read(String name,String statementId, int dimension, int index){ 
logger.loggRead(name, statementId ,index ,dimension);
return index; 
}
public static java.lang.Object write(String name, String statementId, java.lang.Object value){
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

public static java.lang.Object eval(String statementId, java.lang.Object value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static java.lang.Object[] eval(String statementId, java.lang.Object[] value, int statement){
logger.logEval(statementId, value+"");
return value;
}
public static java.lang.Object[][] eval(String statementId, java.lang.Object[][] value, int statement){
logger.logEval(statementId, value+"");
return value;
}
}
