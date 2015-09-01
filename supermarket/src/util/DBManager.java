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
			Class.forName(XmlUtils.getValue("connection-driver","config.xml")) ;
			String url = XmlUtils.getValue("connection-url","config.xml") ;
			/*
			 * TODO: ��xml�ļ��ж�ȡ
			 */
			Connection conn = DriverManager.getConnection(url, XmlUtils.getValue("connection-user","config.xml"), XmlUtils.getValue("connection-password","config.xml")) ;
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
