package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeMinimaDePessoasException extends RuntimeException {
    public QuantidadeMinimaDePessoasException(){
        super("Não é possivel realizar uma reserva com menos de 2 pessoas para imóveis do tipo Hotel");
    }
}
