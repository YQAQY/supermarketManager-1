package billManager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billManager.service.BillService;
import exception.FormDataException;
/**
 * 
 * @description ������Ʒ���ƺ��Ƿ񸶿��ѯ�˵�
 *
 * @author hello world
 *
 */
public class ListBillByNameAndIsPayServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try{
			String goodName = req.getParameter("name") ;
			String isPay = req.getParameter("isPay") ;
			if(!isPay.matches("(true)|(false)")){
				throw new FormDataException("�˵����ϲ�ѯ��isPay�ֶ�ֵ��Ч") ;
			}
			req.setAttribute("billlist", new BillService().findBillByNameAndIsPay(goodName , isPay )) ;
			req.getRequestDispatcher("../billManager/billList.jsp").forward(req, resp) ;
		}catch(FormDataException e ){
			e.printStackTrace() ; 
			resp.sendRedirect("../billManager/listBill") ;
		}catch(Exception e ){
			resp.sendRedirect("../error.jsp") ;
		}
	}
}
