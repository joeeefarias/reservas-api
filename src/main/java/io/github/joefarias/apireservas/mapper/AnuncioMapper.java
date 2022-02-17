package io.github.joefarias.apireservas.mapper;

import io.github.joefarias.apireservas.domain.Anuncio;
import io.github.joefarias.apireservas.service.request.CadastrarAnuncioRequest;
import io.github.joefarias.apireservas.service.response.AnuncioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnuncioMapper {

    AnuncioMapper INSTANCE = Mappers.getMapper(AnuncioMapper.class);


    @Mapping(target = "imovel", ignore = true)
    @Mapping(target = "anunciante", ignore = true)
    Anuncio cadastrarAnuncioRequestToAnuncio(CadastrarAnuncioRequest cadastrarAnuncioRequest);

    @Mapping(target = "idImovel", source = "imovel.id")
    @Mapping(target = "idAnunciante", source ="anunciante.id")
    AnuncioResponse anuncioToAnuncioResponse(Anuncio anuncio);

}
