package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @description 
 *              ����/providerManager��/billManager����Դ�Ŀ��ƣ������ж�session��userlogin���Ի���admin�����Ƿ���ֵ
 *              �����ж���ͨ�û����߹���Ա�Ƿ��½
 *
 * @author hello world
 *
 */
public class CommonAuthorityControlFilter implements Filter {
	@Override
	public void destroy() {}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)req ;
		if(httpRequest.getSession().getAttribute("admin") == null && httpRequest.getSession().getAttribute("userlogin") == null){
			((HttpServletResponse)resp).sendRedirect("../userLogin.jsp");
		}else{
			chain.doFilter(req, resp) ;
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
}
