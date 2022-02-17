package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PeriodoMenorQueUmDiaException extends RuntimeException {
    public PeriodoMenorQueUmDiaException() {
        super(String.format("Período inválido! O número mínimo de diárias precisa ser maior ou igual à 1."));
    }
}
