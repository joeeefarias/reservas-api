package io.github.joefarias.apireservas.mapper;

import io.github.joefarias.apireservas.domain.Usuario;
import io.github.joefarias.apireservas.service.request.UsuarioRequest;
import io.github.joefarias.apireservas.service.response.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest);
    UsuarioResponse usuarioToUsuarioResponse(Usuario usuario);

}
