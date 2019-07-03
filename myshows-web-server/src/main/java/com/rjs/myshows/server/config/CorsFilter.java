package com.rjs.myshows.server.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {
	public CorsFilter() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-AUTH-TOKEN, If-Modified-Since, Content-Disposition");
		response.setHeader("Access-Control-Expose-Headers", "X-AUTH-TOKEN,USER-TOKEN,Content-Disposition");
		filterChain.doFilter(servletRequest, response);
	}
}
