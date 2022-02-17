package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaAtivaNoMesmoPeriodoException extends RuntimeException {
    public ReservaAtivaNoMesmoPeriodoException(){
        super("Este anuncio já esta reservado para o período informado.");
    }
}