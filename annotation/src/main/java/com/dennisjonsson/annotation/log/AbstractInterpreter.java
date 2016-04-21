/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation.log;

import com.dennisjonsson.annotation.markup.Header;
import com.dennisjonsson.annotation.markup.Markup;
import com.dennisjonsson.annotation.markup.Operation;
import com.dennisjonsson.annotation.markup.Read;
import com.dennisjonsson.annotation.markup.Write;
import java.util.HashMap;

/**
 *
 * @author dennis
 */
public abstract class AbstractInterpreter implements Stream{
    
    protected Markup markup;
  

    @Override
    public void addMarkup(Markup markup) {
        this.markup = markup;
    }

    public abstract void interpret(String className, Operation operation);
    public abstract void print(String json);
   


    
}
