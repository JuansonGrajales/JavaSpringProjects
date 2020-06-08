package com.test.hplus.interceptors;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggingInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		String sessionId = null;
		
		if(null!=request.getCookies()) {
			for(Cookie cookie: request.getCookies()) {
				if("JSESSIONID".equals(cookie.getName())) {
					sessionId = cookie.getValue();
				}
			}
		}
		
		System.out.println("Incoming request data log: session: "+sessionId+" at "+new Date()
				+" for "+ request.getRequestURI());
		
		return true;
		
	}
}
