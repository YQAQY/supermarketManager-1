package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @description ���ݿ����ӹ��� 
 * 
 * @author caiyao
 *
 */
public class DBManager {
	/**
	 * TODO: ���ݿ����ӳ�
	 * ��ȡ���ݿ�����
	 * @return Connection 
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
			Class.forName("oracle.jdbc.OracleDriver") ;
			String url = "jdbc:oracle:thin:@localhost:1521:caiyao" ;
			/*
			 * TODO: ��xml�ļ��ж�ȡ
			 * 
			 */
			Connection conn = DriverManager.getConnection(url, "supermarketuser", "supermarket_password") ;
			return conn ;
	}
	/**
	 * �ͷ����ݿ�����
	 * @param conn
	 */
	public static void releaseConn(Connection conn ){
		try{
			conn.close(); 
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
}
