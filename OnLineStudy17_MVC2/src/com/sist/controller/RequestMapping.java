package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
  public String value();//String���ڿ��� ���� (ã��) => �����ڰ� �����ϸ� �ȵȴ� 
  // �޼ҵ����� => @RequestMapping("����") => ã�� ���� �ؿ� �ִ� 
  // rm.value()
}