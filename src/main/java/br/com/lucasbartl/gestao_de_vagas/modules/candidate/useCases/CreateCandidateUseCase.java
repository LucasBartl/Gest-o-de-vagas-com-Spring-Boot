package br.com.lucasbartl.gestao_de_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.lucasbartl.gestao_de_vagas.exceptions.UserFoundException;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateEntity;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

//Informando ao Spring que ele é nossa camada de serviço (Regra de negocio)
@Service

public class CreateCandidateUseCase {

    // Instanciando nosso repositorio de candidatos e passando responsabilidade para
    // o spring
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        //Verifica se o username ou email existem no banco, se existir avisa que já esta em uso
        this.candidateRepository 
                .findByUserNameOrEmail(candidateEntity.getUserName(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        // *Realiza a criaçao dentro do banco de dados
        return this.candidateRepository.save(candidateEntity);
    }

}
