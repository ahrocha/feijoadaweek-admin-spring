package br.com.feijoadaweek.admin.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

//import br.com.feijoadaweek.jpa.helper.TokenHelper;
import br.com.feijoadaweek.admin.interfaces.*;

public class GoogleSigninService implements SigninInterface  {

	private static final JacksonFactory jacksonFactory = new JacksonFactory();

	private static final NetHttpTransport transport = new NetHttpTransport();

	private String token;

	private String email;

	private Payload payload;

	@Override
	public boolean signin(String idTokenString) throws ServletException, IOException {

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
				.setAudience(Collections.singletonList(System.getenv("feijucaGoogle"))).build();
		GoogleIdToken idToken;
		
		System.out.println("Google Token " + System.getenv("feijucaGoogle"));

		try {
			idToken = verifier.verify(idTokenString);
			if (idToken != null) {
				// payload contem userdata informado pelo Google
				this.payload = idToken.getPayload();
				this.email = payload.getEmail();

			} else {
				verifier.getIssuer();
				System.out.println("Invalid ID token.");
			}

		} catch (GeneralSecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

}
