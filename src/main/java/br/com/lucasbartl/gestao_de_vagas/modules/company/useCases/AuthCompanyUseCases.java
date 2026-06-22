package br.com.lucasbartl.gestao_de_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lucasbartl.gestao_de_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.lucasbartl.gestao_de_vagas.modules.company.repositories.CompanyRepository;

//Definindo camada de serviço 
@Service

public class AuthCompanyUseCases {

    @Autowired 
    private CompanyRepository companyRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;


    public void execute(AuthCompanyDTO authCompanyDTO){

        //Verificando se a company existe, caso não encontre retorna avisando...
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
        .orElseThrow(()->{
            throw new UsernameNotFoundException("Company not found");
        });

        //Verificando se senha bate com banco
        var passwordsMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword())
        if (!passwordsMatch) {
            throw new AuthenticationException("Password invalid");
        }
        
    }



}
