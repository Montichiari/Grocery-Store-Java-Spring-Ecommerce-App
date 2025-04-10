package sg.edu.nus.team3.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sg.edu.nus.team3.shoppingcart.interceptor.LoginInterceptor;
import sg.edu.nus.team3.shoppingcart.interceptor.RoleInterceptor;

@Component
public class WebAppConfig implements WebMvcConfigurer {
	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Autowired
	RoleInterceptor roleInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Current interceptor implementation redirects to login from cart and admin urls except Login itself, if not logged in.
		registry.addInterceptor(loginInterceptor).addPathPatterns("/cart/**", "/admin/**").excludePathPatterns("/login");
		registry.addInterceptor(roleInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/login");
	}
	
	
}