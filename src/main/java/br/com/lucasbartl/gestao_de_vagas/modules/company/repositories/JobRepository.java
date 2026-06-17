package br.com.lucasbartl.gestao_de_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateEntity;
import br.com.lucasbartl.gestao_de_vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID>{

         
    
}
