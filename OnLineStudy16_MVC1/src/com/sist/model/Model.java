package com.sist.model;

import javax.servlet.http.HttpServletRequest;

/*
 *   ���� �ٸ� �������� Ŭ������ ��� �Ѱ��� �̸�(�������̽�)���� ����(����)
 *   ��� 
 *    extends , implements 
 *    
 *    class A   ==> A �������� 
 *    class B   ==> B �������� 
 *    interface I
 *    class C implements I  ==> �������� (I,C)
 *    
 *    interface ���� 
 *    class �� implements ���� (��,����)
 *    
 *    ���� ani=new ��()
 *    
 *    ===> �������̽��� �����ϴ� �޼ҵ忡 ���� => �������̽��� �޼ҵ带 �����ϸ� => �����ϰ� �ִ� ��� Ŭ������ �����߻�
 *    ===> POJO (�������̽� ����(X),���(X)) ==> ���� (������̼�) => Spring
 */
public interface Model {
    // ���(��û) ó���ϴ� �޼ҵ� => Model�� ��� Ŭ������ ��û�� ó���ϱ� ���� �޼ҵ� 
	public String handlerRequest(HttpServletRequest request);// Call By References
	// �ּ�(�޸�) ==> ���� �ּҿ� ä���ִ� ��� (Ŭ����,�迭=>�޸� �ּҸ� �̿��ϴ� ���)
	/*
	 *   �Ű����� ���� ��� : Call by Value : �ٸ� �޸𸮿� ���� ����(�Ϲ� ������)
	 *                  Call by Reference : ���� �޸� �ּ� ������ �Ѿ�� 
	 *                  
	 *   MVC ���� ����
	 *      ���������� ��û�� �޴� ���(JSP/Servlet) 
	 *           request     (request�� �޾Ƽ�)    request   �����ͺ��̽� ���� 
	 *   �����   ===========  ��Ʈ�ѷ�   ============ ModelŬ����  ========== �����ͺ��̽�(DAO) 
	 *            ������� �޴´�       request�� ���� ��Ƽ�  (setAttribute())
	 *                                                     ������� �ִ� request => �޾Ƽ� ���
	 *   ========= Model Ŭ���� ============== ��Ʈ�ѷ�  ============= JSP�� ���� =========> ����� 
	 *   
	 *   ����ڰ� ������ request�� �̿��ؼ� ==> ������� ��� �ش�  ==> (request�� ��ü ���� �ϴ�)
	 *                                                   ======================
	 *                                                    Call By Reference
	 *                                                    
	 *   1) ��Ʈ�ѷ� : ���� (�𵨰� �並 ������ �ִ� ���� ����)
	 *   2) �� : ��û ó�� ==> Ȯ��(����) �Ϲ� �ڹ�
	 *   3) �� : ��û ó���� ������� ��� ==> JSP (EL,JSTL)
	 *   
	 *   <%
         // ����ڰ� ������ �Խù��� �޴´�
         // detail.jsp?no=10
            String no=request.getParameter("no");
         // DAO ����
           BoardDAO dao=new BoardDAO();
           BoardVO vo=dao.boardDetailData(Integer.parseInt(no));  ==> �ڹٷ� ���� (��)
		%>
		===================== ��
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="table.css">
		</head>
		<body>
		   <center>
		     <h1>���뺸��</h1>
		     <table class="table_content" width=700>
		       <tr>
		         <th width=20%>��ȣ</th>
		         <td width=20% align="center"><%=vo.getNo() %></td>
		         <th width=20%>�ۼ���</th>
		         <td width=20% align="center"><%=vo.getRegdate().toString() %></td>
		       </tr>
		       <tr>
		         <th width=20%>�̸�</th>
		         <td width=20% align="center"><%=vo.getName() %></td>
		         <th width=20%>��ȸ��</th>
		         <td width=20% align="center"><%=vo.getHit() %></td>
		       </tr>
		       <tr>
		         <th width=20%>����</th>
		         <td colspan="3" align="left"><%=vo.getSubject() %></td>
		       </tr>
		       <tr>
		         <td colspan="4" height="200" valign="top"><pre><%=vo.getContent() %></pre></td>
		       </tr>
		       <tr>
		         <td colspan="4" align="right">
		           <a href="update.jsp?no=<%=vo.getNo()%>">����</a>&nbsp;
		           <a href="#">����</a>&nbsp;
		           <a href="list.jsp">���</a>
		         </td>
		       </tr>
		     </table>
		   </center>
		</body>
		</html>
		=================== View 
		
		��Ʈ�ѷ� ==> �����ؾߵǴ� �𵨰� �並 �˰� �ִ� (518~520page)
		  1) ��û �޴´� 
		     String cmd=request.getRequestURI() 
		       => (list).do
		  2) ���� ã�´�   ==> ListModel
		  3) �𵨿��� �Ѱ��� ������� request,session�� ��Ƽ�
		       request.setAttribute()
		  4) JSP�� ã�� => request, session�� �Ѱ��ش� 
		       forward(request,responae)
		       
		       list.do ==> ListModel    ====> list.jsp
		       detail.do ==> DetailModel ===> detail.jsp  ==> Map�� �̿��ؼ� (��û:Ű,�ش� �ڹ�����)
		       
		       ***** Controller�� �����ϱ� ���ؼ� Controller�� �ҽ����� ==> ��ü ����Ʈ�� ������ �� ���� 
		       *
		       *  ������ �̿��Ѵ� (.properties,.xml)
		       *                          ===== Spring,struts
		       
	 *          
	 */
}
