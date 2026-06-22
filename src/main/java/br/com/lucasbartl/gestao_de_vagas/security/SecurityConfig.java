package br.com.lucasbartl.gestao_de_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
    *Informando para o Spring que se trata de uma classe de 
    *configuração que queremos que o proprio Spring gerencie
 */
@Configuration
public class SecurityConfig {
    // *Colocamos o Bean para indicar que é um objeto gerenciado pelo String
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       //*Desabilitando o SpringSecurity
        http.csrf(csrf -> csrf.disable())
        //*Definindo rotas autenticadas
          .authorizeHttpRequests(auth -> {

            //*Rotas definidas como autenticadas */
            auth.requestMatchers("/candidate/").permitAll()
            .requestMatchers("/company/").permitAll()
            .requestMatchers("/auth/company").permitAll();
            
            //! Não autenticadas
            auth.anyRequest().authenticated();

        })
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
