package providreManager.dao;

import javax.sql.rowset.CachedRowSet;

import providerManager.vo.Provider;

/**
 * 
 * @description ��Ӧ�̹���������ݿ���� 
 * 
 * @author caiyao
 *
 */
public interface ProviderManagerDAO {
	/**
	 * ��ѯ���й�Ӧ����Ϣ
	 * @return �ɴ洢�Ľ����
	 */
	public CachedRowSet selectAllProvider() throws Exception ;
	/**
	 * ��ӹ�Ӧ��
	 * @param provider
	 * @throws Exception
	 */
	public void insertProvider( Provider provider ) throws Exception ;
	/**
	 * ���Ĺ�Ӧ����Ϣ
	 * @param provider
	 * @throws Exception
	 */
	public void updateProvider( Provider provider ) throws Exception ;
	/**
	 * ���ݹ�Ӧ��ID��ѯ�û�
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public CachedRowSet findProviderById(double providerId ) throws Exception ;
	/**
	 * ���ݹ�Ӧ��IDɾ����Ӧ��
	 * @param providerId
	 * @throws Exception
	 */
	public void deleteProviderById(double providerId ) throws Exception ;
	/**
	 * ���ݹ�Ӧ���������û�
	 * @param providerName ��Ӧ����
	 * @return ��ѯ�����
	 * @throws Exception
	 */
	public CachedRowSet selectProviderByName(String providerName ) throws Exception ;
	/**
	 * ���ݹ�Ӧ�����ƺ�����ģ����ѯ��Ӧ��
	 * @param name ��Ӧ������
	 * @param desc ����
	 * @return
	 */
	public CachedRowSet selectAProviderByNameOrDesc(String name , String desc ) throws Exception  ;
}
