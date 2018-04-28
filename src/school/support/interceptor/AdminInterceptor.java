package school.support.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Ghost
 *
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object object = request.getSession().getAttribute("sessionUser");
		String uri = request.getRequestURI();
		if (null == object && !uri.startsWith("/login") && !uri.startsWith("/logout")) {
			response.sendRedirect("/login.do");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
