package userManager.serlvet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userManager.service.UserCheck;
import userManager.service.UserService;
import userManager.vo.User;
/**
 * 
 * @description �޸��û���Ϣ
 *
 * @author hello world
 *
 */
public class ModifyUserServlet extends HttpServlet  {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id")) ;
		String name = req.getParameter("name") ;
		String password = req.getParameter("password") ;
		String gender = req.getParameter("gender") ;
		int age = Integer.parseInt(req.getParameter("age")) ;
		String telephone = req.getParameter("telephone") ;
		String address = req.getParameter("address") ;
		System.out.println("id: " + id + " name : " + name + "password�� " + password );
		User user = new User(id , name , password , gender , age , telephone , address , 0) ;
		try{
			if(!new UserCheck().checkUser(user)){
				throw new Exception() ;
			}
			new UserService().modifyUser(user) ;
			// �޸ĳɹ�ת��ɹ���ʾҳ��
			req.setAttribute("modifyResultInfo", "�޸ĳɹ�") ;
			// ������ʾ��ת���ҳ��
			req.setAttribute("redirectURI", "../userManager/listUser") ;
			// ����ת��ҳ������
			req.setAttribute("redirectPageName", "�û��б�" );
			// ת�����ɹ���ʾҳ��
			req.getRequestDispatcher("../userManager/result.jsp").forward(req, resp) ;
		}catch(Exception e ){
			e.printStackTrace() ; 
			req.setAttribute("modifyResultInfo", "�޸�ʧ��") ;
			// ������ʾ��ת���ҳ��
			req.setAttribute("redirectURI", "../userManager/listUser") ;
			// ����ת��ҳ������
			req.setAttribute("redirectPageName", "�û��б�" ) ;
			// ת�����ɹ���ʾҳ��
			req.getRequestDispatcher("../userManager/result.jsp").forward(req, resp) ;
		}
	}
}
