package io.github.joefarias.apireservas.service.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.joefarias.apireservas.domain.CaracteristicaImovel;
import io.github.joefarias.apireservas.domain.TipoImovel;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastrarImovelRequest {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Campo identificação é obrigatório.")
    private String identificacao;

    @NotNull(message = "Campo tipo imóvel é obrigatório.")
    private TipoImovel tipoImovel;

    @Valid
    @NotNull(message = "Campo endereço é obrigatório.")
    private EnderecoRequest endereco;

    @NotNull(message = "Campo proprietário é obrigatório.")
    private Long idProprietario;

    @NotEmpty(message = "Campo carcteristicas é obrigatório")
    private List<CaracteristicaImovel> caracteristicas;

}
