package io.github.joefarias.apireservas.service.response.reserva;

import io.github.joefarias.apireservas.domain.Pagamento;
import io.github.joefarias.apireservas.domain.Periodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoReservaResponse {

    private Long idReserva;

    private DadosSolicitanteResponse solicitante;

    private DadosAnuncioResponse anuncio;

    private Periodo periodo;

    private Integer quantidadePessoas;

    private Pagamento pagamento;
}
