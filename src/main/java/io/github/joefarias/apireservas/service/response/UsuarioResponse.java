package io.github.joefarias.apireservas.service.response;


import io.github.joefarias.apireservas.domain.Endereco;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private LocalDate dataNascimento;

    private Endereco endereco;

    private String avatarUrl;
}
