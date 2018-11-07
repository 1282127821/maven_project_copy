package com.xy.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
public @interface My {
  String myName() default "my";

  @Target({ElementType.FIELD, ElementType.PARAMETER})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Hi {
    String value() default "hi";
  }

  MyAnnoUse[] anno() default {};
}

