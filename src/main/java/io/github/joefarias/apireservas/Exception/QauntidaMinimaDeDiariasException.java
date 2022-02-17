package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QauntidaMinimaDeDiariasException extends RuntimeException {
    public QauntidaMinimaDeDiariasException(){
        super("Não é possivel realizar uma reserva com menos de 5 diárias para imóveis do tipo Pousada");
    }
}
