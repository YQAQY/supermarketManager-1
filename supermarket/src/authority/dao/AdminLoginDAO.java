package authority.dao;

import java.sql.Connection;


/**
 * 
 * @description �������Ա��¼������ݿ���� 
 * 
 * @author caiyao
 *
 */
public interface AdminLoginDAO {
	/**
	 * ��ѯAdmin��Ϣ
	 * @param name �û���
	 * @param password ����
	 * @return �Ƿ���ڽ����
	 */
	public boolean selectAdmin(String name , String password ) throws Exception ;
}
