package br.com.feijoadaweek.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import br.com.feijoadaweek.admin.service.GoogleSigninService;

public class TransactionFilter implements Filter  {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filtro sendo executado");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
		
		Cookie[] cookies = req.getCookies();
		String idTokenString = null;
		
		for(Cookie cookie : cookies) {
			System.out.println("COOKIE " + cookie.getName() + "  " + cookie.getValue());
			
			if ("token".equalsIgnoreCase(cookie.getName())) {
				// @TODO validate
				idTokenString = cookie.getValue();
			}
			System.out.println("idTokenString " + idTokenString);
		}
		
		if (idTokenString != null) {
			
			GoogleSigninService googleSigninService = new GoogleSigninService();
			googleSigninService.signin(idTokenString);
			
			System.out.println(googleSigninService.getEmail());
			
			System.out.println("logado");
			Cookie tokenCookie = new Cookie("token",idTokenString);
			tokenCookie.setMaxAge(60*60);
			res.addCookie(tokenCookie);
			chain.doFilter(request, res);
		} else {
			res.sendRedirect("/login");
		}
	}
}
