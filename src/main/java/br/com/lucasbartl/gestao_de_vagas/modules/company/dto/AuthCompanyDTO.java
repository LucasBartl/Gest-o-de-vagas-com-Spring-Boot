package br.com.lucasbartl.gestao_de_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // <- cria um construtor com os argumentos password e username

public class AuthCompanyDTO  {
    
    private String password;
    private String userName; 


}
