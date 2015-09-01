package providerManager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import providerManager.service.ProviderService;
/**
 * 
 * @description ���ProviderName�Ƿ��ظ�
 *
 * @author hello world
 *
 */
public class CheckProviderNameServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String ProviderName = req.getParameter("name") ;
		try{
			if(!new ProviderService().checkProviderNameExist(ProviderName)){
				resp.getWriter().print("") ;
			}else{
				resp.setCharacterEncoding("utf-8") ;
				resp.getWriter().print("��Ӧ�����Ѿ����� ");
			}
		}catch(Exception e ){
			e.printStackTrace() ;
			resp.sendRedirect("../error.jsp");
		}
	}
}
