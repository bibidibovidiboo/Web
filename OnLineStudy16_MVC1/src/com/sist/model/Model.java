package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {
	// ��� (��û) ó���ϴ� �޼ҵ� => Model�� ��� Ŭ������ ��û�� ó���ϱ� ���� �޼ҵ�
	public String handlerRequest(HttpServletRequest request); // Call By References
	// �ּ� (�޸�) ==> ���� �ּҿ� ä���ִ� ��� (Ŭ����,�迭=> �޸� �ּҸ� �̿��ϴ� ���)
	/*
	 * 	�Ű����� ���� ��� : Call by Value : �ٸ� �޸𸮿� ���� ���� (�Ϲ� ������)
	 *  			  Call by Reference : ���� �޸� �ּ� ������ �Ѿ��
	 */
}
