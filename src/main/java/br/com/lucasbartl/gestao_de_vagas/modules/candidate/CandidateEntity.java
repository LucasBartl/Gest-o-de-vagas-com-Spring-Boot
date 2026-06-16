package br.com.lucasbartl.gestao_de_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/* 
    OBS: Estamos usando uma biblioteca chamada Lombok que nos agiliza ter
    que colocar manualmente os metodos GET/SET : Usamos a marcaçao @Data
*/
@Data

//Transformando nossa entidade em uma tabela BD
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não podem conter espaço")
    private String userName;

    @Email(message = "O campo deve conter um email valido")
    private String email;
    
    @Length(min = 10, max = 50 )
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt; 

}
