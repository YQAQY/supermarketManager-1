package billManager.dao;

import javax.sql.rowset.CachedRowSet;

import billManager.vo.Bill;

/**
 * 
 * @description ��������������ݿ���� 
 * 
 * @author caiyao
 *
 */
public interface BillManagerDAO {
	/**
	 * ��ѯ���ж�����Ϣ
	 * @return �ɴ洢�Ľ����
	 */
	public CachedRowSet selectAllBill() throws Exception ;
	/**
	 * ��Ӷ���
	 * @param bill
	 * @throws Exception
	 */
	public void insertBill( Bill bill ) throws Exception ;
	/**
	 * ���Ķ�����Ϣ
	 * @param bill
	 * @throws Exception
	 */
	public void updateBill( Bill bill ) throws Exception ;
	/**
	 * ���ݶ���ID��ѯ����
	 * @param billId
	 * @return
	 * @throws Exception
	 */
	public CachedRowSet findBillById(int billId ) throws Exception ;
	/**
	 * ���ݶ���IDɾ������
	 * @param billId
	 * @throws Exception
	 */
	public void deleteBillById(int billId ) throws Exception ;
	/**
	 * ������Ʒ�����Ƿ񸶿��ѯ������Ϣ
	 * @param goodName ��Ʒ��
	 * @param isPay �Ƿ񸶿�
	 * @return
	 * @throws Exception
	 */
	public CachedRowSet selectBillByNameAndIsPay(String goodName , String isPay ) throws Exception ;
}
