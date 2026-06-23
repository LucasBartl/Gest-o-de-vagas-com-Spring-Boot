package br.com.lucasbartl.gestao_de_vagas.security;

import br.com.lucasbartl.gestao_de_vagas.Providers.JWTProvider;
import br.com.lucasbartl.gestao_de_vagas.exceptions.ExceptionHandlerController;
import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Definindo como componentes
@Component
public class SecurityFilter extends OncePerRequestFilter {

    // Pedindo para o spring instaciar
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, // Recebe da requisição
            HttpServletResponse response, // Resposta a requisição
            FilterChain filterChain) // Filtro para validaçoes
            throws ServletException, IOException {

        // Setando contexto do usuário como nulo
        SecurityContextHolder.getContext().setAuthentication(null);

        // Coletando o header
        String header = request.getHeader("Authorization");

        System.out.println(header);

        // Validacoes

        if (header != null) {
            // Validando o que temos dento do header
            var subjectToken = this.jwtProvider.validateToken(header);

            // Se vazio
            if (subjectToken.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            request.setAttribute("company_id", subjectToken);
            // inserindo usuário
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null,
                    Collections.emptyList());
            // Injetando auth no springSecurity
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        filterChain.doFilter(request, response);

    }

}
