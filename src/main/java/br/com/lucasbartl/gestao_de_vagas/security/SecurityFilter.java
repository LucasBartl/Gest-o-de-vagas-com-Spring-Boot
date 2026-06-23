package br.com.lucasbartl.gestao_de_vagas.security;

import br.com.lucasbartl.gestao_de_vagas.exceptions.ExceptionHandlerController;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Definindo como componentes
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final ExceptionHandlerController exceptionHandlerController;

    SecurityFilter(ExceptionHandlerController exceptionHandlerController) {
        this.exceptionHandlerController = exceptionHandlerController;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, // Recebe da requisição
            HttpServletResponse response, // Resposta a requisição
            FilterChain filterChain) // Filtro para validaçoes
            throws ServletException, IOException {

        // Coletando o header
        String header = request.getHeader("Authorization");

        System.out.println(header);

        filterChain.doFilter(request, response);
        
        throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }

}
