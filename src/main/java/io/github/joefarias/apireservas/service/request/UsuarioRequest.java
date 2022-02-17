package io.github.joefarias.apireservas.service.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class UsuarioRequest {

    private Long id;
    @NotBlank(message = "Campo nome é obrigatório.")
    private String nome;
    @NotBlank(message = "Campo e-mail é obrigatório.")
    private String email;
    @NotBlank(message = "Campo senha é obrigatório.")
    private String senha;
    @Pattern(regexp = "^\\d{11}$", message = "O CPF deve ser informado no formato 99999999999.")
    @NotBlank(message = "Campo CPF é obrigatório.")
    private String cpf;

    @NotNull(message = "Campo data de nascimento é obrigatório.")
    private LocalDate dataNascimento;
    @Valid
    private EnderecoRequest endereco;


}
