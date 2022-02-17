package io.github.joefarias.apireservas.Exception;

import io.github.joefarias.apireservas.domain.FormaPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaNaoAceitaException extends RuntimeException {
    public FormaNaoAceitaException(FormaPagamento formaescolhida, List<FormaPagamento> formaPagamento) {
        super(String.format
                ("O anúncio não aceita %s como forma de pagamento. As formas aceitas são %s.",
                        formaescolhida, formaPagamento.stream().map(Object::toString).collect(Collectors.joining(", "))));
    }
}
