package com.sist.controller;
import java.lang.reflect.Method;
import java.util.*;
class A
{
	@RequestMapping("aaa.do")
	// �ߺ��� �� ���� ==> ������̼� => if���� �߰��ϴ� ���̴� 
	public void aaa()
	{
		System.out.println("A:aaa() Call...");
	}
	@RequestMapping("bbb.do")
	public void bbb()
	{
		System.out.println("A:bbb() Call...");
	}
	@RequestMapping("ccc.do")
	public void ccc()
	{
		System.out.println("A:ccc() Call...");
	}
	@RequestMapping("ddd.do")
	public void ddd()
	{
		System.out.println("A:ddd() Call...");
	}
}
// aaa.do  ==> aaa() , bbb.do ==> bbb() ccc.do ==> ccc()
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        System.out.print("����Ʈ�ּ� �Է�:");// getRequestURI()
        String cmd=scan.next();
        
		/*
		 * A a=new A(); if(cmd.equals("aaa.do")) { a.aaa(); } else
		 * if(cmd.equals("bbb.do")) { a.bbb(); } else if(cmd.equals("ccc.do")) {
		 * a.ccc(); }
		 */
        try
        {
        	// Ŭ������ ��� ������ �о� �´� 
        	Class clsName=Class.forName("com.sist.controller.A");
        	// �޸� �Ҵ� 
        	Object obj=clsName.newInstance();
        	// AŬ������ ������ �ִ� ��� �޼ҵ带 �о� �´� 
        	Method[] methods=clsName.getDeclaredMethods();
        	for(Method m:methods)
        	{
        		// System.out.println(m.getName());// AŬ������ ����Ǿ� �ִ� �޼ҵ��̸��� ������ �´�
        		// Method���� �ִ� Annotation�� �д´� 
        		// ������̼��� ���α׷��Ӱ� ����� ���� ���� ����
        		RequestMapping rm=m.getAnnotation(RequestMapping.class);
        		// ������̼��� �о���� ��� 
        		if(cmd.equals(rm.value()))
        		{
        			m.invoke(obj, null);// �޼ҵ� �̸��� ���� ã�� �� �ִ� 
        			// invoke ==> �ش� �޼ҵ带 ȣ�� ���� 
        			// a.aaa()
        			// �޼ҵ� ȣ�� (obj(AŬ����)�� ������ �ִ� �޼ҵ带 ȣ���Ѵ�)
        			// null ==> �Ű�����
        		}
        	}
        }catch(Exception ex) {}
	}

}