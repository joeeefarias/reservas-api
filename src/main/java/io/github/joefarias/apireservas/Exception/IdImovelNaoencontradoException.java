package io.github.joefarias.apireservas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdImovelNaoencontradoException extends RuntimeException{
    public IdImovelNaoencontradoException(Long idImovel){
        super(String.format("Nenhum(a) Imovel com Id com o valor '%d' foi encontrado.", idImovel));
    }
}
