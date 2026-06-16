package br.com.lucasbartl.gestao_de_vagas.modules.company.repositories;

import java.util.UUID;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucasbartl.gestao_de_vagas.modules.candidate.CandidateEntity;
import br.com.lucasbartl.gestao_de_vagas.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUserNameOrEmail(String userName, String Email);
}
