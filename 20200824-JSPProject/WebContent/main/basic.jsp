<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
	JSP : Java Server Page => �������� ����Ǵ� �ڹ�����
	========
	JSP�� ������� 
		1. ������
			= page : JSP���Ͽ� ���� ����
			= tablib : �±׷� �ڹ��� ����
						==> ��� <c:forEach> <c:if>
			�ڹ� + HTML (HTML ��� �ڹٰ� ÷��)
			�ڹ� ���� <% %>
			<html>
				<body>
					<h1>�Խ���</h1>
					<ul>
					<%
						for(BoardVO vo:list)
						{
					%>	
							<li>��ȣ-����-�̸�-�ۼ���-��ȸ��</li>	
					<%	
						}
					%>
					</ul>
				</body>
			</html>
			
			==> �� forEach�� ���� (�̰� �����ؼ� �� ���� ���)
			 
			<html>
				<body>
					<h1>�Խ���</h1>
					<ul>
						<c:forEach ltems="list">
							<li>��ȣ-����-�̸�-�ۼ���-��ȸ��</li>	
						</c:forEach>
					</ul>
				</body>
			</html> 
			
			= include : Ư�� JSP �ȿ� JSP�� ÷��
			
			2. �ڹ� �ڵ� �κ�
				= ��ũ��Ʈ�� <% %> => �Ϲ� �ڹ� �ڵ�
				= ǥ���� <%= %> => out.println() : ȭ�鿡 ���� ���
				= ����� <%! %> => �������� , �޼ҵ带 ���� ��� (���󵵰� ����)
				
			3. ���� ��ü (8�� ����) : �̸� ��ü�� �����س��� �ʿ�� ���� 
								  ����� �����ϰ� ������ش�
			   = request : ������� ��û��
			   = response : ���� => ó�� => ����
			   ========================
			   = session
			   = pageContext
			   = page
			   = config
			   = exception
			   = application
			4. ǥ���� (EL,JSTL)
			5. MVC					
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% 
	String name="ȫ�浿";
	out.println(name);
%>	
</body>
</html>