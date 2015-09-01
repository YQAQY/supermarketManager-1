package authority.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authority.service.AdminLoginService;
/**
 * 
 * @description �������Ա��¼Servlet 
 * 
 * @author caiyao
 *
 */
public class AdminLoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String adminName = req.getParameter("name") ;
		String adminPassword = req.getParameter("password") ;
		try{
			if(new AdminLoginService().checkExist(adminName , adminPassword )){
				req.getSession().setAttribute("admin", adminName) ;
				/*ҳ����תʹ���ض�����ҳ���ض���ʱ�ᾭ���������ж�Ȩ��*/
				// ��ȷ��ת��ʽ��
				resp.sendRedirect("../admin/admin_main.jsp") ;
				// ���ַ�ʽ����תURLΪ�� http://localhost:8080/supermarket/authority/admin/admin_main.jsp
				// ����дб��ʱ��Ĭ���Ǹ��ݵ�ǰservlet��URL����һ��Ϊ��URL
				//resp.sendRedirect("admin/admin_main.jsp") ;
			}else{
				resp.sendRedirect("../admin_login.jsp?result=1") ;
			}
		}catch(Exception e ){
			e.printStackTrace();
			resp.sendRedirect("error.jsp") ;
		}
		
	}
}
