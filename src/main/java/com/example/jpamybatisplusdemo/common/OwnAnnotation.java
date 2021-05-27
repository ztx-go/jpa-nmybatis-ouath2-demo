package com.example.jpamybatisplusdemo.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OwnAnnotation {

    //    在底层实现上，所有定义的注解都会自动继承java.lang.annotation.Annotation接口。
    String name();

    int age() default 18;

    int[] score();

//    @Inherited注解，是指定某个自定义注解如果写在了父类的声明部分，那么子类的声明部分也能自动拥有该注解
}

