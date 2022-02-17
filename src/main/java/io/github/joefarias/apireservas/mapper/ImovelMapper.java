package io.github.joefarias.apireservas.mapper;

import io.github.joefarias.apireservas.domain.Imovel;
import io.github.joefarias.apireservas.service.request.CadastrarImovelRequest;
import io.github.joefarias.apireservas.service.response.ImovelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImovelMapper{

    ImovelMapper INSTANCE = Mappers.getMapper(ImovelMapper.class);

    Imovel cadastrarImovelRequestToImovel(CadastrarImovelRequest cadastrarImovelRequest);

    @Mapping(target = "idProprietario", source = "proprietario.id")
    ImovelResponse imovelToImovelResponse(Imovel imovel);
}

