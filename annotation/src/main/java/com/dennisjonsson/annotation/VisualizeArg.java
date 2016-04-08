/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dennisjonsson.annotation;

import com.dennisjonsson.markup.AbstractType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author dennis
 */
@Retention(RetentionPolicy.SOURCE)
public @interface VisualizeArg {
    String name();
    AbstractType abstractType();
    int position();
}