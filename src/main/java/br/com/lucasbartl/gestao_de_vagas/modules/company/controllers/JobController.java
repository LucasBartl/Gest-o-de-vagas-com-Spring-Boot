package br.com.lucasbartl.gestao_de_vagas.modules.company.controllers;

import br.com.lucasbartl.gestao_de_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasbartl.gestao_de_vagas.modules.company.entities.JobEntity;
import br.com.lucasbartl.gestao_de_vagas.modules.company.useCases.CreateJobUserCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/job")
public class JobController {

    // chamando useCase
    @Autowired
    private CreateJobUserCase createJobUserCase;

    @RequestMapping("/")
    public ResponseEntity create(@Valid @RequestBody JobEntity jobEntity) {
        
        try {
            var result = this.createJobUserCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
