package providerManager.service;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import providerManager.dao.impl.ProviderManagerDaoImpl;
import providerManager.vo.Provider;
import providreManager.dao.ProviderManagerDAO;
import util.Convert;

/**
 * 
 * @description ��Ӧ�̹�����ع��� 
 * 
 * @author caiyao
 *
 */
public class ProviderService {
	/**
	 * ��ѯ���й�Ӧ����Ϣ
	 * @return ArrayList
	 */
	public ArrayList<Provider> allProvider() throws Exception {
		ProviderManagerDAO ProviderManagerDao = new ProviderManagerDaoImpl() ;
		CachedRowSet cachedRowSet = ProviderManagerDao.selectAllProvider() ;
		ArrayList<Provider> Providers = Convert.RStoList(cachedRowSet, Provider.class) ;
		return Providers ;
	}
	/**
	 * ��ӹ�Ӧ��
	 * @param provider
	 */
	public void addProvider(Provider provider ) throws Exception {
		ProviderManagerDAO ProviderManagerDao = new ProviderManagerDaoImpl() ;
		ProviderManagerDao.insertProvider(provider) ;
	}
	/**
	 * ����ID�޸Ĺ�Ӧ����Ϣ
	 * @param provider
	 * @throws Exception
	 */
	public void modifyProvider(Provider provider ) throws Exception {
		ProviderManagerDAO ProviderManagerDao = new ProviderManagerDaoImpl() ;
		ProviderManagerDao.updateProvider(provider) ;
	}
	/**
	 * ����ProviderID��ѯProvider��Ϣ
	 * @param id
	 * @return
	 */
	public Provider findProviderById(double id ) throws Exception {
		ProviderManagerDAO ProviderManagerDao = new ProviderManagerDaoImpl() ;
		CachedRowSet cachedRowSet = ProviderManagerDao.findProviderById(id) ;
		if(!cachedRowSet.next()){
			return null ;
		}
		Provider Providers = Convert.RStoObject(cachedRowSet, Provider.class) ;
		return Providers ;
	}
	/**
	 * ���ݹ�Ӧ��IDɾ����Ӧ����Ϣ
	 * @param id
	 * @throws Exception
	 */
	public void deleteProviderById(double id ) throws Exception {
		ProviderManagerDAO ProviderManagerDao = new ProviderManagerDaoImpl() ;
		ProviderManagerDao.deleteProviderById(id) ;
	}
	/**
	 * ��鹩Ӧ�̱����Ƿ��Ѿ����ڸ����Ĺ�Ӧ����
	 * @param providerName
	 * @return
	 */
	public boolean checkProviderNameExist(String providerName ) throws Exception {
		ProviderManagerDAO ProviderManagerDao = new ProviderManagerDaoImpl() ;
		CachedRowSet cachedRowSet = ProviderManagerDao.selectProviderByName(providerName) ;
		return cachedRowSet.next() ;
	}
	/**
	 * ���ݹ�Ӧ�����ƺ�������ѯ��Ӧ����Ϣ
	 * @return ArrayList
	 */
	public ArrayList<Provider> findProviderByNameOrDesc(String providerName , String providerDesc ) throws Exception {
		ProviderManagerDAO ProviderManagerDao = new ProviderManagerDaoImpl() ;
		CachedRowSet cachedRowSet = ProviderManagerDao.selectAProviderByNameOrDesc(providerName , providerDesc) ;
		ArrayList<Provider> Providers = Convert.RStoList(cachedRowSet, Provider.class) ;
		return Providers ;
	}
	
	/*static class Test{
		public static void main(String[] args) {
			ProviderService demo = new ProviderService() ;
			try {
				System.out.println(demo.findProviderById(3)) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/
}