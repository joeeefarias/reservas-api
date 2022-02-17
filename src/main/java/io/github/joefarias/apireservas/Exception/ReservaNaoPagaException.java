package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaNaoPagaException extends RuntimeException {
    public ReservaNaoPagaException(){
    super("Não é possível realizar o estorno para esta reserva, pois ela não está no status PAGO.");
    }
}
