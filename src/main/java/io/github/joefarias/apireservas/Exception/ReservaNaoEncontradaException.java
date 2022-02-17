package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaNaoEncontradaException extends RuntimeException {
    public ReservaNaoEncontradaException(Long idReserva) {
        super(String.format("Nenhum(a) Reserva com Id com o valor '%d' foi encontrado.", idReserva));
    }
}
