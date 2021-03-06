package br.com.feijoadaweek.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.feijoadaweek.admin.interfaces.SigninInterface;

@Controller
public class LoginController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/login")
	public String show(Model model) {
		
		System.out.println("login show html");
		
		return "login";
	}

	@PostMapping("/login")
	public String login(Model model) throws ServletException {
		
		System.out.println("login post controler");
		
		String idTokenString = request.getParameter("idtoken");

		// @TODO providenciar múltiplos login (@yahoo, @hotmail, etc...)
		String authProvider = "GoogleSigninService";
        String nomeDaClasse = "br.com.feijoadaweek.admin.service." + authProvider;
        
        String email = null;
        try {
			Class<?> classe = Class.forName(nomeDaClasse);
			SigninInterface acao = (SigninInterface) classe.newInstance(); 
			acao.signin(idTokenString);

			email = acao.getEmail();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException | IOException e) {
			throw new ServletException(e);
		}

        // @TODO validar admin no banco de dados
        // @TODO tirar isso do controller
        // @TODO criar tabela de quem pode acessar o banco

        if (!email.equals(System.getenv("FEIJUCA_ADMIN_EMAIL"))) {
        	System.out.println("E-mail não autorizado.");
        	return "login";
        }
        
		Cookie tokenCookie = new Cookie("token",idTokenString);
		tokenCookie.setMaxAge(60*60);
		response.addCookie(tokenCookie);
		
		HttpSession sessao = request.getSession();
	    sessao.setAttribute("usuarioLogado", true);
		
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		
		System.out.println("logout controller");
		// @TODO kill session, delete cookies, etc...
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession sessao = req.getSession();
		sessao.invalidate();
		
		return "login";
	}
}



