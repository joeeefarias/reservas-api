package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImovelComAnuncioCadastradoException extends RuntimeException {
    public ImovelComAnuncioCadastradoException() {
        super("Não é possível excluir um imóvel que possua um anúncio.");
    }
}
