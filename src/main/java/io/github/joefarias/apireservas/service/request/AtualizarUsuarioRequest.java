package io.github.joefarias.apireservas.service.request;


import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizarUsuarioRequest {

    @NotNull(message = "Campo nome é obrigatório.")
    private String nome;
    @NotNull(message = "Campo e-mail é obrigatório.")
    private String email;
    @NotNull(message = "Campo senha é obrigatório.")
    private String senha;
    @NotNull(message = "Campo data de nascimento é obrigatório.")
    private LocalDate dataNascimento;
    @Valid
    private EnderecoRequest endereco;
}
