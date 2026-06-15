package br.com.lucasbartl.gestao_de_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Classe controladora para tratamento de exceçoes
@ControllerAdvice

public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message) {
        this.messageSource = message;

    }

    // Define que quando deve ser usado
    @ExceptionHandler(MethodArgumentNotValidException.class)
    
    public  ResponseEntity<List<ErrorMessageDTO>> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        // Array de Erros DTO -> coleta informaçoes pedidas em ErrorMessageDTO
        List<ErrorMessageDTO> dto = new ArrayList<>();

        // Realizando o tratamento das mensagens de erro e coloca na o erro na linguagem
        // correta
        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

            // Coletando informacoes dos campos necessarios
            ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());

            // Adicionando a lista
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

}
