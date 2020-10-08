package com.sist.model;
import javax.servlet.http.HttpServletRequest;
/*
 *    Ŭ������ ������ �ٸ��� ���ǹ�
 *    ��� Ŭ���� ������ ���� ==> interface
 *    
 *    class A
 *    class B
 *    
 *    interface I
 *    class A implements I
 *    class B implements I
 *    
 *    I i=new A()
 *    I i=new B()
 *    
 *    Ŭ���� �������� ��Ƽ� �ѹ��� ���� => �������̽� (�������� �⺻����)
 *    
 *    
 */
public interface Model {
   public String execute(HttpServletRequest request);
}





