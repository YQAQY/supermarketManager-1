package billManager.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billManager.service.BillCheck;
import billManager.service.BillService;
import billManager.vo.Bill;
import exception.FormDataException;
/**
 * 
 * @description ����˵� 
 * 
 * @author caiyao
 *
 */
public class AddBillServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{
			int providerId = Integer.parseInt(req.getParameter("providerId")) ;
			double price = Double.parseDouble(req.getParameter("price")) ;
			String priceUnit = req.getParameter("goodsUnit") ;
			int goodsNum = Integer.parseInt(req.getParameter("goodsNum")) ;
			String isPay =req.getParameter("isPay") ;
			String goodsName = req.getParameter("goodsName") ;
			String goodsDesc = req.getParameter("goodsDesc") ;
			Timestamp BillTime = Timestamp.valueOf(req.getParameter("billTime")) ;
			Bill Bill = new Bill(providerId , price , priceUnit , goodsNum , isPay , goodsName ,goodsDesc,BillTime ) ;
			if( !new BillCheck().checkBill(Bill) ){
				 throw new FormDataException("����˵�����Ϣ��д����") ;
			}
			new BillService().addBill(Bill) ;
			resp.sendRedirect("listBill") ;
		}catch(NumberFormatException e){
			e.printStackTrace() ;
			resp.sendRedirect("../billManager/addBill?result=1") ;
		}catch(FormDataException e ){
			e.printStackTrace() ;
			resp.sendRedirect("../billManager/addBill?result=1") ;
		}catch(Exception e ){
			e.printStackTrace() ; 
			resp.sendRedirect("../error.jsp");
		}
 	}
}
