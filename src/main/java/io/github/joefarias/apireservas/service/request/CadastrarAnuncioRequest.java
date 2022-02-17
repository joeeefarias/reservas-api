package io.github.joefarias.apireservas.service.request;

import io.github.joefarias.apireservas.domain.FormaPagamento;
import io.github.joefarias.apireservas.domain.TipoAnuncio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastrarAnuncioRequest {

    @NotNull(message = "Campo tipo anuncio é obrigatório")
    private TipoAnuncio tipoAnuncio;

    @NotNull(message = "Campo ID imóvel é obrigatório")
    private Long idImovel;

    @NotNull(message = "Campo ID anunciante é obrigatório")
    private Long idAnunciante;

    @NotNull(message = "Campo valor diária é obrigatório")
    private BigDecimal valorDiaria;

    @NotEmpty(message = "Campo formas aceitas é obrigatório")
    private List<FormaPagamento> formasAceitas;

    @NotNull(message = "Campo decrição é obrigatório")
    private String descricao;

}
