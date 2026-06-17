package br.com.lucasbartl.gestao_de_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


//Informando para o Spring que se trata de uma classe de
//  configuração que queremos que ele gerencie 
@Configuration

public class SecurityConfig {
    //Colocamos o Bean para indicar que é um objeto gerenciado pelo String
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }


}
