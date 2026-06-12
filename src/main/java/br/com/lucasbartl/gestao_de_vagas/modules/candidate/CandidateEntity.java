package br.com.lucasbartl.gestao_de_vagas.modules.candidate;

import java.util.UUID;

import lombok.Data;

/* 
    OBS: Estamos usando uma biblioteca chamada Lombok que nos agiliza ter
    que colocar manualmente os metodos GET/SET : Usamos a marcaçao @Data
*/
@Data
public class CandidateEntity {

    private UUID id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String description;
    private String curriculum;

}
