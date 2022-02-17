package io.github.joefarias.apireservas.service.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoRequest {


    @Pattern(regexp = "^\\d{5}-\\d{3}$", message ="O CEP deve ser informado no formato 99999-999." )
    @NotNull(message = "Campo cep é obrigatório")
    private String cep;

    @NotNull(message = "Campo logradouro é obrigatório")
    private String logradouro;

    @NotNull(message = "Campo numero é obrigatório")
    private String numero;

    @NotNull(message = "Campo complemento é obrigatório")
    private String complemento;

    @NotNull(message = "Campo bairro é obrigatório")
    private String bairro;

    @NotNull(message = "Campo cidade é obrigatório")
    private String cidade;

    @NotNull(message = "Campo estado é obrigatório")
    private String estado;


}
