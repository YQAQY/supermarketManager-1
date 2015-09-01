package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import util.Constrant;

/**
 * 
 * @description Ӧ������ʱִ�г�ʼ������
 *
 * @author hello world
 *
 * @modifyTime 2015��9��1��
 */
public class ApplicationSetupListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// ���÷�����·��
		String serverPath = sce.getServletContext().getRealPath("/") ;
		Constrant.SERVER_PATH = serverPath ;
	}
}
