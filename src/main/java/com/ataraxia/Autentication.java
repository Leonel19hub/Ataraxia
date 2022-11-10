package com.ataraxia;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class Autentication implements AuthenticationSuccessHandler{

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                Boolean tipoCliente=false, tipoAdmin=false;
		
		
                Collection<?extends GrantedAuthority> autorizaciones = authentication.getAuthorities();
                
                for(GrantedAuthority grantedAuthority:autorizaciones) {
                    if(grantedAuthority.getAuthority().equals("USER")) { //en mayuscula
                        tipoCliente=true;
                        break;
                    }else {
                        if(grantedAuthority.getAuthority().equals("ADMIN")) { //en mayuscula
                            tipoAdmin=true;
                            break;
                        }
                    }
                    
                }
                
                if(tipoCliente) {
                    redirectStrategy.sendRedirect(request, response, "/index");
                }else {
                    if(tipoAdmin) {
                        redirectStrategy.sendRedirect(request, response, "/index");
                    }
                }
        
    }
    
}
