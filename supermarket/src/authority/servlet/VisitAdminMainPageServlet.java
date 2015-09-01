package authority.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @description ֱ�ӷ���Admin_main.jspҳ�� 
 * 
 * @author caiyao
 *
 */
public class VisitAdminMainPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 1. �ж�session��Admin�ֶ��Ƿ���ֵ
		 * 2. �����ֵ����ת��admin_main.jspҳ��
		 * 3. ���û��ֵ����ת��admin_login.jspҳ��
		 */
		if(req.getSession().getAttribute("admin") != null ){
			req.getRequestDispatcher("admin/admin_main.jsp") ;
		}else{
			resp.sendRedirect("admin_login.jsp") ;
		}
	}
}
