package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SolicitanteInvalidoExcpetion extends RuntimeException {
    public SolicitanteInvalidoExcpetion(){
        super("O solicitante de uma reserva não pode ser o próprio anunciante.");
    }
}
