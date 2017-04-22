package com.dk.umwerktestapp.utils.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by David on 20-Apr-17.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}