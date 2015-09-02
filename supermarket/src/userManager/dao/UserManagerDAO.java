package userManager.dao;

import javax.sql.rowset.CachedRowSet;

import userManager.vo.User;

/**
 * 
 * @description �û�����������ݿ���� 
 * 
 * @author caiyao
 *
 */
public interface UserManagerDAO {
	/**
	 * ��ѯ�����û�(������Ա)��Ϣ
	 * @return �ɴ洢�Ľ����
	 */
	public CachedRowSet selectAllUser() throws Exception ;
	/**
	 * ����û�
	 * @param user
	 * @throws Exception
	 */
	public void insertUser( User user ) throws Exception ;
	/**
	 * �����û���Ϣ
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception ;
	/**
	 * �����û�ID��ѯ�û�
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public CachedRowSet findUserById(int userId ) throws Exception ;
	/**
	 * �����û�IDɾ���û�
	 * @param userId
	 * @throws Exception
	 */
	public void deleteUserById(int userId ) throws Exception ;
	/**
	 * �����û��������û�
	 * @param userName �û���
	 * @return ��ѯ�����
	 * @throws Exception
	 */
	public CachedRowSet selectUserByName(String userName ) throws Exception ;
	/**
	 * ��ѯ�û���ΪuserName�����û�ID��Ϊid���û�
	 * @param userName �û���
	 * @param id �û�ID
	 * @return 
	 * @throws Exception
	 */
	public CachedRowSet selectUserByNameExceptId(String userName , int id ) throws Exception ;
}
