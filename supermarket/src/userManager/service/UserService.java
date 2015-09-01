package userManager.service;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import userManager.dao.UserManagerDAO;
import userManager.dao.impl.UserManagerDaoImpl;
import userManager.vo.User;
import util.Convert;

/**
 * 
 * @description �û�������ع��� 
 * 
 * @author caiyao
 *
 */
public class UserService {
	/**
	 * ��ѯ�����û���������Ա���⣩��Ϣ
	 * @return ArrayList
	 */
	public ArrayList<User> allUser() throws Exception {
		UserManagerDAO userManagerDao = new UserManagerDaoImpl() ;
		CachedRowSet cachedRowSet = userManagerDao.selectAllUser() ;
		ArrayList<User> users = Convert.RStoList(cachedRowSet, User.class) ;
		return users ;
	}
	/**
	 * ����û�(��ͨ�û�)
	 * @param user
	 */
	public void addUser(User user ) throws Exception {
		UserManagerDAO userManagerDao = new UserManagerDaoImpl() ;
		userManagerDao.insertUser(user) ;
	}
	/**
	 * ����ID�޸��û���Ϣ
	 * @param user
	 * @throws Exception
	 */
	public void modifyUser(User user ) throws Exception {
		UserManagerDAO userManagerDao = new UserManagerDaoImpl() ;
		userManagerDao.updateUser(user) ;
	}
	/**
	 * ����userID��ѯUser��Ϣ
	 * @param id
	 * @return
	 */
	public User findUserById(int id ) throws Exception {
		UserManagerDAO userManagerDao = new UserManagerDaoImpl() ;
		CachedRowSet cachedRowSet = userManagerDao.findUserById(id) ;
		cachedRowSet.next() ; // ��ָ�붨λ����һ��
		User users = Convert.RStoObject(cachedRowSet, User.class) ;
		return users ;
	}
	/**
	 * �����û�IDɾ���û���Ϣ
	 * @param id
	 * @throws Exception
	 */
	public void deleteUserById(int id ) throws Exception {
		UserManagerDAO userManagerDao = new UserManagerDaoImpl() ;
		userManagerDao.deleteUserById(id) ;
	}
	/**
	 * ����û������Ƿ��Ѿ����ڸ������û���
	 * @param userName
	 * @return
	 */
	public boolean checkUserNameExist(String userName ) throws Exception {
		UserManagerDAO userManagerDao = new UserManagerDaoImpl() ;
		CachedRowSet cachedRowSet = userManagerDao.selectUserByName(userName) ;
		return cachedRowSet.next() ;
	}
	static class Test{
		public static void main(String[] args) {
			UserService demo = new UserService() ;
			try{
				for(User user : demo.allUser()){
					System.out.println(user);
				}
			}catch(Exception e ){
				e.printStackTrace();
			}
		}
	}
}