package authority.service;

import authority.dao.AdminLoginDAO;
import authority.dao.impl.AdminLoginDAOImpl;

/**
 * 
 * @description ����Ա��¼�Ĺ��� 
 * 
 * @author caiyao
 *
 */
public class AdminLoginService {
	public boolean checkExist(String name , String password ) throws Exception {
		AdminLoginDAO adminDao = new AdminLoginDAOImpl() ;
		return adminDao.selectAdmin(name, password) ;
	}
}
