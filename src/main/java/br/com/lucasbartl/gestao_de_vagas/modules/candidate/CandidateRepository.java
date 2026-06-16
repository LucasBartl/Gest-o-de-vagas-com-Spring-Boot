package br.com.lucasbartl.gestao_de_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/*   
   * Interface — define o contrato do repositório
   * Spring cria a implementação automaticamente,com isso não precisa criar a classe 
   * que implementa

                                ----,, ----
    * JPA é uma classe que possuem varios metodos de manipulação ao banco de dados 
    * CandidateEntity → a classe que mapeia a tabela do banco
    * UUID → o tipo do campo @Id dentro de CandidateEntity
*/

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    // Criando validaçoes
    //Utilizando somente o findBy o Spring entende que deve buscar automaticamente 
    // os proximoa campos escritos no caso UsernameOrEmail
    Optional<CandidateEntity> findByUserNameOrEmail(String userName, String Email);

}
