package billManager.service;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import util.Convert;
import billManager.dao.BillManagerDAO;
import billManager.dao.impl.BillManagerDaoImpl;
import billManager.vo.Bill;

/**
 * 
 * @description ����������ع��� 
 * 
 * @author caiyao
 *
 */
public class BillService {
	/**
	 * ��ѯ���ж�����Ϣ
	 * @return ArrayList
	 */
	public ArrayList<Bill> allBill() throws Exception {
		BillManagerDAO BillManagerDao = new BillManagerDaoImpl() ;
		CachedRowSet cachedRowSet = BillManagerDao.selectAllBill() ;
		ArrayList<Bill> Bills = Convert.RStoList(cachedRowSet, Bill.class) ;
		return Bills ;
	}
	/**
	 * ��Ӷ���
	 * @param bill
	 */
	public void addBill(Bill bill ) throws Exception {
		BillManagerDAO BillManagerDao = new BillManagerDaoImpl() ;
		BillManagerDao.insertBill(bill) ;
	}
	/**
	 * ����ID�޸Ķ�����Ϣ
	 * @param bill
	 * @throws Exception
	 */
	public void modifyBill(Bill bill ) throws Exception {
		BillManagerDAO BillManagerDao = new BillManagerDaoImpl() ;
		BillManagerDao.updateBill(bill) ;
	}
	/**
	 * ����BillID��ѯBill��Ϣ
	 * @param id
	 * @return
	 */
	public Bill findBillById(int id ) throws Exception {
		BillManagerDAO BillManagerDao = new BillManagerDaoImpl() ;
		CachedRowSet cachedRowSet = BillManagerDao.findBillById(id) ;
		cachedRowSet.next() ; // ��ָ�붨λ����һ��
		Bill Bills = Convert.RStoObject(cachedRowSet, Bill.class) ;
		return Bills ;
	}
	/**
	 * ���ݶ���IDɾ��������Ϣ
	 * @param id
	 * @throws Exception
	 */
	public void deleteBillById(int id ) throws Exception {
		BillManagerDAO BillManagerDao = new BillManagerDaoImpl() ;
		BillManagerDao.deleteBillById(id) ;
	}
	/**
	 * ������Ʒ�����Ƿ񸶿��ѯ����
	 * @return ArrayList
	 */
	public ArrayList<Bill> findBillByNameAndIsPay(String goodName , String isPay ) throws Exception {
		BillManagerDAO BillManagerDao = new BillManagerDaoImpl() ;
		CachedRowSet cachedRowSet = BillManagerDao.selectBillByNameAndIsPay(goodName , isPay ) ;
		ArrayList<Bill> Bills = Convert.RStoList(cachedRowSet, Bill.class) ;
		// ����Bill��providerName����ֵ
		return Bills ;
	}
}