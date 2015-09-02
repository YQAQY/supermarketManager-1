package authority.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @description �˳�ϵͳ
 *
 * @author hello world
 *
 * @modifyTime 2015��9��2��
 */
public class ExitServlet extends HttpServlet {
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			doPost(req, resp) ;
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String admin = (String)req.getSession().getAttribute("admin") ;
		String user = (String)req.getSession().getAttribute("userlogin") ;
		
		if((admin == null || admin.equals("")) && ((user == null || user.equals("")))){
			// ����һ���쳣��½
			System.out.println("�û��쳣��½�� û�е�½��ֱ�ӽ����˹���ҳ��") ;
		}
		// ��session��userlogin��admin���Ե�ֵ����Ϊ""
		req.getSession().setAttribute("admin","") ;
		req.getSession().setAttribute("userlogin","") ;
		resp.sendRedirect("../userLogin.jsp") ;
	}
}
