package br.com.lucasbartl.gestao_de_vagas.exceptions;

public class UserFoundException extends RuntimeException {
    
    public UserFoundException(){

        super("usuário já existe");

    }




}
