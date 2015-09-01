package providerManager.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import providerManager.vo.Provider;

/**
 * 
 * @description ���Provider���������ԵĺϷ���
 *
 * @author hello world
 *
 */
public class ProviderCheck {
	/**
	 * ���Provider����ĺϷ���
	 * @param provider
	 * @return
	 */
	public boolean checkProvider(Provider provider){
		
		return checkProviderName(provider.getProviderName()) &&
				checkProviderDesc(provider.getProviderDesc()) &&
				checkProviderLinkman(provider.getLinkman()) &&
				checkProviderTel(provider.getTelephone()) &&
				checkProviderFax(provider.getFax()) &&
				checkProviderAddress(provider.getAddress());
	}
	/**
	 * У�鴫��źϷ���
	 * @param fax
	 * @return
	 */
	public boolean checkProviderFax(String fax ){
		/*
		 * ^(0[0-9]{2,3}\\-)? ����
		 * ([2-9][0-9]{6,7})+ ������
		 * (\\-[0-9]{1,4})?$ �ֻ���
		 */
		String regex = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$" ;
		return Pattern.compile(regex).matcher(fax).matches() ;
	}
	/**
	 * У�鹩Ӧ����ϵ�����Ϸ���
	 * @param linkman
	 * @return
	 */
	public boolean checkProviderLinkman(String linkman) {
		/*
		 * ��Ӧ����ϵ������Ҫ����Ĺ���
		 * 1. ����1~50���ַ���������Ӣ�ģ�����
		 * 2. ��Ӧ����ϵ����ֻ�ܰ���Ӣ�ġ����ġ��»��ߡ�����
		 */
		String regex = "[a-zA-Z_\u4E00-\u9FA50-9]{1,20}" ;
		Matcher matcher = Pattern.compile(regex).matcher(linkman) ;
		return matcher.matches() ;
	}
	/**
	 * У�鹩Ӧ�������Ϸ���
	 * @param desc
	 * @return
	 */
	public boolean checkProviderDesc(String desc) {
		/*
		 * ��ʱ������ɸѡ
		 * TODO : У��
		 */
		return true ;
	}
	/**
	 * У�鹩Ӧ�����Ƿ�Ϸ�
	 * @param providerName
	 * @return
	 */
	public boolean checkProviderName(String providerName){
		/*
		 * ��Ӧ������Ҫ����Ĺ���
		 * 1. ����1~20���ַ���������Ӣ�ģ�����
		 * 2. �û���ֻ�ܰ���Ӣ�ġ����ġ��»��ߡ�����
		 */
		String regex = "[a-zA-Z_\u4E00-\u9FA50-9]{1,20}" ;
		Matcher matcher = Pattern.compile(regex).matcher(providerName) ;
		return matcher.matches() ;
	}

	/**
	 * У�鹩Ӧ�̵绰����
	 * @param telephone
	 * @return
	 */
	public boolean checkProviderTel(String telephone ){
		/*
		 * �û��绰��Ҫ����Ĺ���
		 * 1. �����ǵ绰�ź��ֻ���
		 */
		return Pattern.compile("1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}").matcher(telephone).matches() ;
	}
	/**
	 * У���ַ�Ϸ���
	 * @param address
	 * @return
	 */
	public boolean checkProviderAddress(String address ){
		/*
		 * ��ʱû���뵽��ַ��Ҫ����Ĺ���
		 */
		return true ;
	}
	private static class Test {
		public static void main(String[] args) {
			ProviderCheck demo = new ProviderCheck() ;
			System.out.println(demo.checkProviderFax("4215030")) ;
		}
	}
}
