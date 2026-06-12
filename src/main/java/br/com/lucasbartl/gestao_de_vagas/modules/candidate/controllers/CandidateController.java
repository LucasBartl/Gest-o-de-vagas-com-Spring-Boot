package br.com.lucasbartl.gestao_de_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")

public class CandidateController {

    @PostMapping("/")
    public void create(@RequestBody CandidateEntity candidate){
        System.out.println("Candidato:");
        System.out.println(candidate.getEmail());
        
    }



}
