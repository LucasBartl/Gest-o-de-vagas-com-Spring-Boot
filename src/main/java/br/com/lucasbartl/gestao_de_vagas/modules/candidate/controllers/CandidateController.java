package br.com.lucasbartl.gestao_de_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasbartl.gestao_de_vagas.exceptions.UserFoundException;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateEntity;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateRepository;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/candidate")

public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    // *@Valid vai pegar as regra de validaçoes que criamos dentro do CandidateEntity e só
    // *recebera os valores que seguem a regra
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok(result);
        
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
