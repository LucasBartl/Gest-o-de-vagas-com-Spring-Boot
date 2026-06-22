package br.com.lucasbartl.gestao_de_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lucasbartl.gestao_de_vagas.exceptions.UserFoundException;
import br.com.lucasbartl.gestao_de_vagas.modules.company.entities.CompanyEntity;
import br.com.lucasbartl.gestao_de_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;


    //Chamando nosso Beam para criptografar senha salvas 
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository
        .findByUserNameOrEmail(companyEntity.getUserName(), companyEntity.getEmail())
        .ifPresent((user)->{
            throw new UserFoundException();
        });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        
        return this.companyRepository.save(companyEntity);

    }

}
