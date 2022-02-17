package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaNaoPagavelException extends RuntimeException {
    public ReservaNaoPagavelException(){
        super("Não é possível realizar o pagamento para esta reserva, pois ela não está no status PENDENTE.");
    }
}
