package authority.service;

import authority.dao.LoginDAO;
import authority.dao.impl.LoginDAOImpl;

/**
 * 
 * @description ����Ա��¼�Ĺ��� 
 * 
 * @author caiyao
 *
 */
public class LoginService {
	public boolean checkExist(String name , String password , int role ) throws Exception {
		LoginDAO adminDao = new LoginDAOImpl() ;
		return adminDao.selectUser(name, password , role ) ;
	}
}
