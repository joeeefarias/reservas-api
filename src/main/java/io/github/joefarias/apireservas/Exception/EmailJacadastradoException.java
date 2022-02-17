package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailJacadastradoException extends RuntimeException {
    public EmailJacadastradoException(String emailInformado) {
        super(String.format("Já existe um recurso do tipo Usuario com E-Mail com o valor '%s'.", emailInformado));
    }
}
