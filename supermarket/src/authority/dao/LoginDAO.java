package authority.dao;

import java.sql.Connection;


/**
 * 
 * @description �������Ա��¼������ݿ���� 
 * 
 * @author caiyao
 *
 */
public interface LoginDAO {
	/**
	 * ��ѯAdmin��Ϣ
	 * @param name �û���
	 * @param password ����
	 * @return �Ƿ���ڽ����
	 */
	public boolean selectUser(String name , String password , int role ) throws Exception ;
}
