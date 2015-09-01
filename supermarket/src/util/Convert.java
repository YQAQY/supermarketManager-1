package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

/**
 * 
 * ʹ�������Ҫ��
 * 	1. ���ݿ���������������֮�����»��߷ָ�
 * 	2. ���ݿ���Ӧ��ʵ������������ʹ���շ�����
 * 	3. �������ʶ�СЩ
 * @author caiyao 
 *
 * @function ����ת��
 * 
 * @version 2.1
 * 
 * @modifyTime : 2015-9-1
 */
public class Convert {
	/**
	 * ��CachedRowSetImpl�еĶ�����¼�洢��List��
	 * @param cachedRS CachedRowSetImpl�����
	 * @param c List��Ԫ������
	 * @return ArrayList����
	 * @throws SQLException  cachedRS.next()���µ��쳣
	 * @throws RuntimeException 
	 * @throws InvocationTargetException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T> ArrayList<T> RStoList(CachedRowSet cachedRS , Class<T> c)
			throws SQLException, InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, InvocationTargetException, 
			RuntimeException{
		if(!cachedRS.next()){
			return null ;
		}
		cachedRS.previous() ;// ��������ִ����next������������Ҫ��ָ���ƶ�����һ��
		ArrayList<T> store = new ArrayList<T>() ;
		while(cachedRS.next()){
			T object = RStoObject(cachedRS , c ) ;
			store.add(object) ;
		}
		return store ;
	}
	
	/**
	 * ��ResultSet���͵Ľ����ת����Object���͵Ķ���
	 *  ע�� 
	 * 1. �÷���ֻ��ת��ֻ��һ�������CachedRowSet�� 
	 * 2.rs������еõ���ֵxxx��������Ҫ����Ӧ��setXxx����
	 * 
	 * @param rs
	 *            �����
	 * @param c
	 *            Class���͵�ֵ������ͨ��Class.forName(����)��ȡ��Ҫȷ���������޲εĹ��췽����
	 *            ��������InstantiationException�쳣
	 * @return ת��֮��Ķ���
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws RuntimeException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 *             ע�� 
	 * 			   1. �÷���ֻ��ת��ֻ��һ�������CachedRowSet�� 
	 * 			   2.rs������еõ���ֵxxx��������Ҫ����Ӧ��setXxx����
	 *             3. �ڵ����������������ʱ�򣨲���ͨ��RStoList�ļ�ӵ��ã���
	 *             ��Ҫ�ڴ���CachedRowSet�������֮ǰ����rs��next()��������������ָ����Ч�Ĵ���
	 *             4. ʹ�ø÷�����������е�����ת����������ʱ��Ҫ����������get/set����Ҫ�����ݿ���
	 *             �������Ӧ���������ݿ�����Ϊuser_name,��ô����ķ�����Ӧ����setUserName/getUserName
	 */
	public static <T> T RStoObject(CachedRowSet rs , Class<T> c)
			throws InstantiationException, IllegalAccessException, 
			SQLException, NoSuchMethodException, RuntimeException, 
			ClassNotFoundException, InvocationTargetException{
		 // Ҫȷ���������޲εĹ��췽��
		T instance = c.newInstance() ;
		// ��ȡԪ���ݣ��Ա�õ��е���Ϣ
		ResultSetMetaData metaData = rs.getMetaData() ;
		// ��ȡ����
		int columnNum = metaData.getColumnCount() ;
		
		for(int i = 1 ; i <= columnNum ; i ++ ){
			String columnName = getColumnName(metaData.getColumnName(i)) ;
			Class<?> columnClassType = SQLTypeToClass(metaData.getColumnType(i)) ;
			columnClassType = getColumnClassType(columnName , columnClassType , c ) ;
			String columnTypeName = columnTypeToGetter(metaData.getColumnTypeName(i)) ;
			// �����ȡ�����set����
			Method objectMethod = c.getMethod(
							"set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1), 
							columnClassType
							) ;
			// �����ȡCachedRowSetImpl��get����
			Method RSGetter = CachedRowSetImpl.class.getMethod(
							"get"+columnTypeName.substring(0,1).toUpperCase()+columnTypeName.substring(1), 
							 int.class) ;
			// ִ��RS��get������ȡ����ֵ
			Object value = RSGetter.invoke(rs,i) ;
			// ִ��Object��set����Ϊ����ֵ
			objectMethod.invoke(instance, value) ;
		}
		// ���ض���
		return instance ;
	}
	
	/**
	 * �ж���c�Ƿ����set'columnName'('columnClassType' x)������
	 * ��������ڣ���Ѱ���Ƿ����set'columnName'('cloumnClassType�ĸ���' x)������������ڷ���cloumnClassType�ĸ��ࡣ
	 * ������ڣ��򷵻�cloumnClassTypeԭֵ��
	 * @param columnName ��c��������
	 * @param columnClassType ��c set�����Ĳ�������
	 * @param c ��c
	 * @return set��������������
	 */
	private static Class<?> getColumnClassType(String columnName , Class<?> columnClassType , Class<?> c ){
		/*
		 * д�÷�����ԭ��
		 * int/double ��oracle���ݿ��ж�����Number��ʾ��
		 * ���Դ����ݿ��в�ѯ�����������޷������int����double�� Ҳ�͵����޷���ȷͨ�������ȡ�����set��������Ϊ���������޷�ȷ����
		 * ���磺 Student�������������� int a , double b ;
		 * �������ݿ��о��Ƕ���Number�������ݿ���ȡ��Ҳ����Number��
		 * �����Number��Ӧ��java��int����ôSQLTypeToClass�����������صľ���int.class,
		 * ���۴����ݿ��в�ѯ��������int����double����ˣ������ȡset������ֻ�ܻ�ȡ��intΪ�����ķ����� ���Ծ��޷���ȡ��double
		 * b ��set������ �����ȡ������ȷ���ģ��������Ҳ���int��������ʱת��ȥ��double����������
		 * �÷���Ϊ�����Ҳ���intΪ�����ķ���ʱ���̶�����double���Ͳ����ķ�����
		 * �����Ժ������Ҳ�����������Ϊ�����ķ����̶������丸��Ϊ�����ķ�����
		 */
			try {
				c.getMethod("set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1), columnClassType).getName() ;
			} catch (NoSuchMethodException e) {
				return getSuperColumnClassType(columnClassType) ;
			} catch (SecurityException e) {}
			return columnClassType ;
	}
	/**
	 * ��ȡcolumnClassType�ĸ���
	 * @param columnClassType 
	 * @return
	 */
	private static Class<?> getSuperColumnClassType(Class<?> columnClassType){
		if(columnClassType.equals(int.class)){
			return double.class ;
		}else{
			return columnClassType.getSuperclass() ;
		}
		
	}
	/**
	 * ��ȡ�����ݿ�������Ӧ����������
	 * ���磺
	 * 		���ݿ�����				������
	 * 		user_name/username		userName
	 * @param rawColumnName ���ݿ�����
	 * @return �����ݿ�������Ӧ�Ķ���������
	 */
	public static String getColumnName(String rawColumnName ){
		if(rawColumnName.matches(".*_.*")){
			String[] words = rawColumnName.split("_") ;
			String firstWord = words[0].toLowerCase() ;
			String lastWord = words[1].substring(0, 1).toUpperCase() + words[1].substring(1).toLowerCase() ;
			String attributeName = firstWord + lastWord ;
			return attributeName ;
		}else{
			/*
			 * ������ݿ������������»��ߣ��п���ֻ��һ�����ʣ���ô������ĸ��дȻ��ֱ�ӷ��ؾ��С�
			 * ʹ�����������Ҫ�����ݿ�������������֮�����ʹ���»��߷ָ
			 */
			return rawColumnName.substring(0,1).toUpperCase() + rawColumnName.substring(1).toLowerCase() ;
		}
	}
	public static String columnTypeToGetter(String columnType){
		/**
		 * mysql���ݿ��д洢�ַ���ֻ��varchar������ResultSet�ӿ���û�ж���getVarchar�����������Խ�varcharת����String���ɣ�
		 */
		if(columnType.equals("VARCHAR")){
			return "String" ;
		}
		if(columnType.equals("NVARCHAR2")){
			return "String" ;
		}
		// oracle���ݿ�NUMBER�ֶ����Ͷ�ӦJava��int
		if(columnType.equals("NUMBER")){
			return "int" ;
		}
		if(columnType.toLowerCase().equals("timestamp")){
			return "object" ;
		}
		return columnType.toLowerCase() ;
	}
	/**
	 * ����װ���ԭʼ��������ת����ԭʼ���͵�class
	 * @param columnType ��װ��Ķ�����������"java.lang.Integer" , "java.lang.Float" , "java.lang.Double"
	 * @return ԭʼ����class���� ����int.class float.class double.class
	 * @throws ClassNotFoundException 
	 */
	public static Class<?> getColumnClassType(String columnType) throws ClassNotFoundException{
		/**
		 * ���ݿ��е�int��float��double��ԭʼ�����͵�ͨ��getColumnClassName()������ȡʱ���Ὣԭʼ���ʹ������������ͷ�װ���� 
		 * ���� int --> Integer float --> Float double --> Double
		 * �÷�����Ϊ�˽���ȡ�Ļ������ͷ�װ����ת����ԭʼ���͵�class
		 */
		if(columnType.equals("java.lang.Integer")){
			 return int.class ;
		}
		if(columnType.equals("java.lang.Float")){
			return float.class ;
		}
		if(columnType.equals("java.lang.Double")){
			 return double.class ;
		}
		return Class.forName(columnType) ;
	}
	/**
	 * ��java.sql.Types����ת������Ӧ��Java�ж�Ӧ��Class
	 * @param SQLType java.sql.Types����
	 * @return Class���͵�ʵ������ int.class
	 */
	public static  Class<?> SQLTypeToClass(int SQLType){
		switch(SQLType){
		case java.sql.Types.ARRAY : return String.class ;
		
		case java.sql.Types.BIGINT : return int.class ;
		
		case java.sql.Types.BIT : return byte.class ;
		
		case java.sql.Types.BOOLEAN : return boolean.class ;
		
		case java.sql.Types.CHAR : return char.class ;
		
		case java.sql.Types.DOUBLE : return double.class ;
		
		case java.sql.Types.FLOAT : return float.class ;
		
		case java.sql.Types.INTEGER : return int.class ;
		
		case java.sql.Types.LONGNVARCHAR : return String.class ;
		
		case java.sql.Types.LONGVARCHAR : return String.class ;
		
		case java.sql.Types.NCHAR : return String.class ;
		
		case java.sql.Types.NVARCHAR :return String.class ;
		
		case java.sql.Types.VARCHAR : return String.class ;
		// ͨ��rs.getDate()��ȡ����java.sql.Date�ܹ�ֱ�ӽ��丳ֵ��java.util.Date������Զ�ת��
		case java.sql.Types.DATE : return Date.class ;
		// oracle���ݿ��Number�ֶ����Ͷ�ӦNUMERIC���ͣ�
		//���������ݿ��������͸���������Number��
		//������Ӧ�����޷��жϴ����ݿ���ȡ�������������Ǹ�����.
		//�������ǣ���Ӧ���ж�ʹ�ø�����
		case java.sql.Types.NUMERIC : return int.class ;
		// ����sun�����ṩ��CachedRowSetImpl�����bug����getTimestamp()�ֶ�ʱ�������ת���쳣��
		// ��������У�
		// 1. ���ࡣoracle������oracle.jdbc.rowset.OracleCachedRowSet�ࣨ�����������֣�������ȷִ�С�
		// 2. ��oracle�����汾��10.0���ϣ������û���Թ�����Щ��˵���ԡ�
		// 3. ���ʹ��sun�ṩ������࣬��ȡTimestampʱʹ��getObject()��Ȼ����ת����
		// ��Ϊ���ǵ������ͨ���ԣ����Բ��õ����ֽ��������
		case java.sql.Types.TIMESTAMP : return Object.class ;
		
		default :return String.class ;
		}
	}
	public static Date StringTodate(String dateString , String format ) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		  // ��ʱ���ַ���ת����java.util.Date����  
        Date date = sdf.parse(dateString);  
		return date ;
	}
	public static java.sql.Date utildateTosqldate(Date date){
	    return new java.sql.Date(date.getTime());
	}
}