package util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 
 * @description Xml�ļ�����
 *
 * @author hello world
 *
 * @modifyTime 2015��9��1��
 */
public class XmlUtils {
	/**
	 * ��web-inf/config/'fileName'�ж�ȡ��ǩ��Ϊkey��ֵ��
	 * @param key ��ǩ��
	 * @param fileName Xml�ļ���
	 * @return ��ǩֵ
	 */
	public static String getValue(String key , String fileName ){
		try {
			Document doc = getDocumentFromXml(Constrant.SERVER_PATH + "WEB-INF/config/" + fileName ) ;
			String value = doc
					.getElementsByTagName(key).item(0)
					.getFirstChild().getNodeValue() ;
			return value ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "" ;
	}
	/**
	 * 
	 * @param xmlPath
	 *            xml�ļ���λ��
	 * @return ���xml�ļ���Ӧ��Document����
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private static Document getDocumentFromXml(String xmlPath)
			throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(xmlPath);
		return doc;
	}
	private static class Test{
		public static void main(String[] args) {
			System.out.println(XmlUtils.getValue("connection-driver", "config.xml")) ;
		}
	}
}
