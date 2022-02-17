package io.github.joefarias.apireservas.service.response;

import io.github.joefarias.apireservas.domain.CaracteristicaImovel;
import io.github.joefarias.apireservas.domain.TipoImovel;
import io.github.joefarias.apireservas.service.request.EnderecoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImovelResponse {

    private Long id;

    private String identificacao;

    private TipoImovel tipoImovel;

    private EnderecoRequest endereco;

    private Long idProprietario;

    private List<CaracteristicaImovel> caracteristicas;
}
