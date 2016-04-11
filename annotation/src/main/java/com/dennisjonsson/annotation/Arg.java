/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation;

import com.dennisjonsson.markup.AbstractType;

/**
 *
 * @author dennis
 */
public class Arg{
  
    public static String build(String name, AbstractType type, int position){
        return name+","+type+","+position;
    }

}
