package sg.edu.nus.team3.shoppingcart.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @author: Jared Chua
@Component
public class CORSFilter implements Filter {

    public CORSFilter() {
    }

    // Specific CORS options to allow devwork to be done from the same origin
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Allow localhost:5173 (the frontend) to send HTTP headers
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        // Allow HTTP credentials to be set on headers
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // Allow methods
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "authorization, Content-Type, xsrf-token");
        response.setHeader("Access-Control-Expose-Headers", "xsrf-token");
        // Allow OPTIONS header, since CORS doesn't seem to allow it even with the above
        if ("OPTIONS".equals(request.getMethod()))
            response.setStatus(HttpServletResponse.SC_OK);
        else
            chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
