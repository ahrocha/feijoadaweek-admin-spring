package br.com.feijoadaweek.admin.interfaces;

import java.io.IOException;

import javax.servlet.ServletException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SigninInterface {

    public boolean signin(String idTokenString) throws ServletException, IOException;
    
    public String getEmail();

}
