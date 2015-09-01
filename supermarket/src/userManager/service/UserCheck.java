package userManager.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import userManager.vo.User;

/**
 * 
 * @description ���User���������ԵĺϷ���
 *
 * @author hello world
 *
 */
public class UserCheck {
	/**
	 * ���User����ĺϷ���
	 * @param user
	 * @return
	 */
	public boolean checkUser(User user){
		
		return  checkUserName(user.getUserName()) &&
				checkPassword(user.getUserPassword()) &&
				checkGender(user.getGender()) &&
				checkAge(user.getAge()) &&
				checkTelephone(user.getTelephoneNum()) &&
				checkAddress(user.getAddress()) ;
	}
	/**
	 * У���û����Ƿ�Ϸ�
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName){
		/*
		 * �û�����Ҫ����Ĺ���
		 * 1. ����1~20���ַ���������Ӣ�ģ�����
		 * 2. �û���ֻ�ܰ���Ӣ�ġ����ġ��»��ߡ�����
		 */
		String regex = "[a-zA-Z_\u4E00-\u9FA50-9]{1,20}" ;
		Matcher matcher = Pattern.compile(regex).matcher(userName) ;
		return matcher.matches() ;
	}
	/**
	 * У���û������Ƿ���ȷ
	 * @param password ��У��������ַ���
	 * @return 
	 */
	public boolean checkPassword(String password){
		/*
		 * �û�������Ҫ����Ĺ���
		 * 1. ����6~20����
		 * 2. ����������֡�Ӣ���ַ�
		 */
		String regex = "([0-9]+)|([a-z]+)" ;
		Matcher matcher = Pattern.compile(regex).matcher(password) ;
		return !matcher.matches() ;
	}
	/**
	 * У���Ա�
	 * @param gender
	 * @return
	 */
	public boolean checkGender(String gender ){
		/*
		 * �û��Ա���Ҫ����Ĺ���
		 * 1. �������С�Ů֮���һ��ֵ
		 */
		return gender.equals("��") || gender.equals("Ů") ;
	}
	/**
	 * У������
	 * @param age
	 * @return
	 */
	public boolean checkAge(double age ){
		/*
		 * �û�������Ҫ����Ĺ���
		 * 1. ������Ҫ��10~150֮��
		 */
		return age <= 150 && age >= 10 ; 
	}
	/**
	 * У���û��绰����
	 * @param telephone
	 * @return
	 */
	public boolean checkTelephone(String telephone ){
		/*
		 * �û��绰��Ҫ����Ĺ���
		 * 1. �����ǵ绰�ź��ֻ���
		 * 2. 
		 * TODO: У��绰��������ʽ
		 */
		
		return true ;
	}
	/**
	 * У���ַ�Ϸ���
	 * @param address
	 * @return
	 */
	public boolean checkAddress(String address ){
		/*
		 * ��ʱû���뵽��ַ��Ҫ����Ĺ���
		 */
		return true ;
	}
}
