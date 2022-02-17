package io.github.joefarias.apireservas.service.request;

import io.github.joefarias.apireservas.domain.Periodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarReservaRequest {


    @NotNull(message = "Campo solicitante é obrigatório")
    private Long idSolicitante;

    @NotNull(message = "Campo anuncio é obrigatório")
    private Long idAnuncio;

    @NotNull(message = "Campo periodo é obrigatório")
    private Periodo periodo;

    @NotNull(message = "Campo quantidade de pessoas é obrigatório")
    private Integer quantidadePessoas;

}
