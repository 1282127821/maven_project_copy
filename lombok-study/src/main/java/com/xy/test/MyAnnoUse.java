package com.xy.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @fileName:MyAnnoUse
 * @author:xy
 * @date:2018/8/22
 * @description:
 */
@Target({ElementType.FIELD,ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnoUse {
    String value();
}
