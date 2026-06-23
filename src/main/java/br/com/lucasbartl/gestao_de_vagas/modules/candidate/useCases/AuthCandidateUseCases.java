package br.com.lucasbartl.gestao_de_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateRepository;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCases {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) {

        // *Validando se o usuário existe, e retorna erro se não
        var candidate = this.candidateRepository
                .findByUserName(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Incorrect information");
                });
        // * Validando se senhas são iguais
        var passwordMatch = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!passwordMatch) {
            throw new BadCredentialsException("Senha inválida");
        }

        // ? Após validarmos existencia do usuário estamos criando um token de
        // ? autenticaçao para o mesmo

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create()
                .withIssuer("Celta_vagas") // Quem emitiu o token
                .withSubject(candidate.getId().toString()) // Dono token
                .withClaim("roles", Arrays.asList("candidate")) // Tipo de user
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2))) // Tempo
                .sign(algorithm);// Assinatura final


        //?  Devolvendo o token criado e organizado
        
        var AuthCandidateResponse = AuthCandidateResponseDTO
                .builder()
                .access_token(token)
                .build();

        return AuthCandidateResponse;
    }

}
