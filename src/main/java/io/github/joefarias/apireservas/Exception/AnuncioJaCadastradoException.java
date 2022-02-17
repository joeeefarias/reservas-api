package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnuncioJaCadastradoException extends RuntimeException{
    public AnuncioJaCadastradoException(Long idImovel){
        super(String.format("Já existe um recurso do tipo Anuncio com IdImovel com o valor '%d'.", idImovel));
    }
}
