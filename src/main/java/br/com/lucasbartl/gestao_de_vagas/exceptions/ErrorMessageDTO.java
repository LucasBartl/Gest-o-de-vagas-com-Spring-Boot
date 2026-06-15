package br.com.lucasbartl.gestao_de_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //Constutor com argumentos
public class ErrorMessageDTO {

    private String message; 
    private String fied;

}
