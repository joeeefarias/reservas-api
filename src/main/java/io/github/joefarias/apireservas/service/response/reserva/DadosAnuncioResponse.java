package io.github.joefarias.apireservas.service.response.reserva;

import io.github.joefarias.apireservas.domain.FormaPagamento;
import io.github.joefarias.apireservas.domain.Imovel;
import io.github.joefarias.apireservas.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosAnuncioResponse {

    private Long id;

    private Imovel imovel;

    private Usuario anunciante;

    private List<FormaPagamento> formasAceitas;

    private String descricao;
}
