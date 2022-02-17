package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaNaoPendenteException extends RuntimeException {
    public ReservaNaoPendenteException(){
        super("Não é possível realizar o cancelamento para esta reserva, pois ela não está no status PENDENTE.");
    }
}
