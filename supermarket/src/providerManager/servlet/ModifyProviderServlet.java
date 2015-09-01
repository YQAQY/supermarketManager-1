package providerManager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import providerManager.service.ProviderService;
import providerManager.vo.Provider;
/**
 * 
 * @description �޸Ĺ�Ӧ����Ϣ
 *
 * @author hello world
 *
 */
public class ModifyProviderServlet extends HttpServlet  {
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
		String desc = req.getParameter("desc") ;
		String linkman = req.getParameter("linkman") ;
		String telephone = req.getParameter("telephone") ;
		String fax = req.getParameter("fax") ;
		String address = req.getParameter("address") ;
		/*int providerId, String providerName, String providerDesc,
		String linkMan, String telephone, String fax, String address*/
		Provider provider = new Provider(id , name , desc , linkman ,  telephone ,fax , address ) ;
		try{
			new ProviderService().modifyProvider(provider) ;
			// �޸ĳɹ�ת��ɹ���ʾҳ��
			req.setAttribute("modifyResultInfo", "�޸ĳɹ�") ;
			// ������ʾ��ת���ҳ��
			req.setAttribute("redirectURI", "../providerManager/listProvider") ;
			// ����ת��ҳ������
			req.setAttribute("redirectPageName", "��Ӧ���б�" );
			// ת�����ɹ���ʾҳ��
			req.getRequestDispatcher("../providerManager/result.jsp").forward(req, resp) ;
		}catch(Exception e ){
			e.printStackTrace() ; 
			// �޸ĳɹ�ת��ɹ���ʾҳ��
			req.setAttribute("modifyResultInfo", "�޸�ʧ��") ;
			// ������ʾ��ת���ҳ��
			req.setAttribute("redirectURI", "../providerManager/listProvider") ;
			// ����ת��ҳ������
			req.setAttribute("redirectPageName", "��Ӧ���б�" );
			// ת�����ɹ���ʾҳ��
			req.getRequestDispatcher("../providerManager/result.jsp").forward(req, resp) ;
		}
	}
}
