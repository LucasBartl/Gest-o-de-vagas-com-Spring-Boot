package br.com.lucasbartl.gestao_de_vagas.modules.company.useCases;

import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.lucasbartl.gestao_de_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.lucasbartl.gestao_de_vagas.modules.company.repositories.CompanyRepository;

//Definindo camada de serviço 
@Service

public class AuthCompanyUseCases {

    // Pegando informacao do secret, localizada dentro do application.properties
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) {

        // Verificando se a company existe, caso não encontre retorna avisando...
        var company = this.companyRepository.findByUserName(authCompanyDTO.getUserName())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Incorrect information");
                });

        // Verificando se senha bate com banco
        var passwordsMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if (!passwordsMatch) {
            throw new BadCredentialsException("Error");
        }
        // Se for igual, gera o token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("CeltaVagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);

        return token;
    }

}
