package userManager.serlvet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userManager.service.UserService;
/**
 * 
 * @description ����޸ĺ��UserName�Ƿ������û��ظ�
 *
 * @author hello world
 *
 */
public class CheckModifyUserNameServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id")) ;
		String userName = req.getParameter("name") ;
		try{
			if(!new UserService().checkModifyUserNameExist(userName,id)){
				resp.getWriter().print("") ;
			}else{
				resp.setCharacterEncoding("utf-8") ;
				resp.getWriter().print("�û����Ѿ����� ");
			}
		}catch(Exception e ){
			e.printStackTrace() ;
			resp.sendRedirect("../error.jsp");
		}
	}
}
