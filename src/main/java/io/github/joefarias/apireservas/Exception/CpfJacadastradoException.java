package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfJacadastradoException extends RuntimeException {
    public CpfJacadastradoException(String cpf) {
        super(String.format("Já existe um recurso do tipo Usuario com CPF com o valor '%s'.", cpf));
    }
}
