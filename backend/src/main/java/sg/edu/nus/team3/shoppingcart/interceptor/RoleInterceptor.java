package sg.edu.nus.team3.shoppingcart.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author diony
 */

@Component
public class RoleInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// Looks for role in session attributes. If not "staff", returns 403 Forbidden.
		HttpSession session = request.getSession();

		Integer id = (Integer) session.getAttribute("id");
		String role = (String) session.getAttribute("role");

		if (id == null || !(role.equalsIgnoreCase("staff"))) {
			response.setStatus(403);
			response.getWriter().write("Forbidden: Staff access required.");
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// Looks for email address in session attributes. If null, redirect to login.
		// HttpSession session = request.getSession();

		// String username = (String) session.getAttribute("email");

		// if (username == null) {
		// response.sendRedirect("/login");
		// }
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
