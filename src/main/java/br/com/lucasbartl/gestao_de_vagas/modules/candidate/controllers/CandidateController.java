package br.com.lucasbartl.gestao_de_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasbartl.gestao_de_vagas.exceptions.UserFoundException;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateEntity;
import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")

public class CandidateController {

    //Instanciando nosso repositorio de candidatos e passando responsabilidade para 
    // o spring
    @Autowired
    private CandidateRepository candidateRepository;


    @PostMapping("/")
    //*@Valid vai pegar as regra de validaçoes que criamos dentro do CandidateEntity e só 
    //*recebera os valores que seguem a regra 
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidate){
        
        this.candidateRepository
        .findByUserNameOrEmail(candidate.getUserName(), candidate.getEmail())
        .ifPresent((user) -> {
           throw new UserFoundException(); 
        });
          
        //*Realiza a criaçao dentro do banco de dados
        return  this.candidateRepository.save(candidate);

    }



}
