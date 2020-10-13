package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
/*
 *   �޸� ���� 
 *   CLASS
 *   SOURCE
 *   ================ �������Ŀ� �޸� ���� 
 *   RUNTIME
 *   ================ �������Ŀ��� �޸� ����
 */
@Target(METHOD)
/*
 *   Annotation 
 *    1. ����� ���� 
 *    2. ã�� 
 *       = TYPE  ====> Ŭ������ ã�� ���
 *       = PARAMETER => �Ű������� ã�� ���
 *       = CONSTRUCTOR => ������ ã�� ���
 *       = FILED  => ������� ã�� ���
 *       = METHOD => �޼ҵ� ã�� ��� 
 *    ================================= ��ġ (Annotation)
 *    @Annotation��  ==> TYPE
 *    public class MovieModel
 *    {
 *         @Annotation��  ==> FILED
 *         MovieDAO dao=new MovieDAO();
 *         
 *         @Annotation��  ==> CONSTRUCTOR
 *         public MovieModel(@Annotation��  ==> PARAMETER int a)
 *         {
 *         }
 *         @Annotation��  ==> METHOD
 *         public void display()
 *         {
 *         }
 *    }
 *    
 *    @RequestMapping("movie/list.do")  ==> Spring
 *    public String movieListData(HttpServletRequest request)
 */
public @interface RequestMapping {
   public String value(); // ���ڿ��� ���� 
}
