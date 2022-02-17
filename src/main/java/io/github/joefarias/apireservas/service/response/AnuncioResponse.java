package io.github.joefarias.apireservas.service.response;


import io.github.joefarias.apireservas.domain.FormaPagamento;
import io.github.joefarias.apireservas.domain.TipoAnuncio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnuncioResponse {

    private TipoAnuncio tipoAnuncio;

    private Long idImovel;

    private Long idAnunciante;

    private BigDecimal valorDiaria;

    private List<FormaPagamento> formasAceitas;

    private String descricao;
}
