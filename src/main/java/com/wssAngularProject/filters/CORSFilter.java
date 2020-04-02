package com.wssAngularProject.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Servlet Filter implementation class CORSFilter
 */
@WebFilter("/*")
@Component
@Order(1)
public class CORSFilter implements Filter {
	
	FilterConfig filterConfig;

    /**
     * Default constructor. 
     */
    public CORSFilter() {
        super();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, x-auth-token, Content-Type, access-control-allow-credentials, X-Total-Count, Authorization, Build-Target");
        response.setHeader("Access-Control-Expose-Headers", "X-Total-Count, Authorization");
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest)req).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {            
            chain.doFilter(req, response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}

}