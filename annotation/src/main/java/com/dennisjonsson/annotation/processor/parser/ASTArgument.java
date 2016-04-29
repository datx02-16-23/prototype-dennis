/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation.processor.parser;

import java.util.Stack;

/**
 *
 * @author dennis
 */
public class ASTArgument {
    public int topNode;
    Stack<Integer> stack;
    
    public ASTArgument(){
        stack = new Stack<>();
    }
    
    public void set(int exp){
        topNode = -1;
        stack.clear();
        push(exp);
    }
    
    public void push(int exp){
        if(stack.empty()){
            topNode = exp;
            stack.push(exp);
        }
        else if(stack.peek() < 10 || exp >= stack.peek()){
            stack.push(exp);
        }
        
    }
    
    public void pop(){
        stack.pop();
        if(stack.empty()){
            topNode = 0;
        }
    }
    
    public int peek(){
        if(stack.empty()){
            return -1;
        }
        return stack.peek();
    }
    
    public void clear(){
        topNode = -1;
        stack.clear();
    }

}
