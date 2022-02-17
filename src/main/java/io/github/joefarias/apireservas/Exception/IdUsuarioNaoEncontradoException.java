package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdUsuarioNaoEncontradoException extends RuntimeException{
    public IdUsuarioNaoEncontradoException(Long idUsuario) {
        super (String.format("Nenhum(a) Usuario com Id com o valor '%d' foi encontrado.", idUsuario));
    }
}
