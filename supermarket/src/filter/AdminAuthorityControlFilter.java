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
import javax.servlet.http.HttpSession;
/**
 * 
 * @description ���ط���admin/��userManager/�µ���Դ(����JSPҳ���servlet)�������ж�session��admin�ֶ��Ƿ���ֵ����admin�Ƿ��¼ 
 * 
 * @author caiyao
 *
 *
 */
public class AdminAuthorityControlFilter implements Filter {
	@Override
	public void destroy() {}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filter) throws IOException, ServletException {
		System.out.println("����filter");
		HttpSession session = ((HttpServletRequest)req).getSession() ;
		if(session.getAttribute("admin") != null ){
			filter.doFilter(req, resp) ;
		}else{
			/*admin�û�û�е�¼��ת���¼ҳ��*/
			((HttpServletResponse)resp).sendRedirect("/supermarket/admin_login.jsp") ;
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
