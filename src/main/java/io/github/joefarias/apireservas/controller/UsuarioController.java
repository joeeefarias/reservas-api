package io.github.joefarias.apireservas.controller;

import io.github.joefarias.apireservas.domain.Usuario;
import io.github.joefarias.apireservas.service.UsuarioService;
import io.github.joefarias.apireservas.service.request.AtualizarUsuarioRequest;
import io.github.joefarias.apireservas.service.request.UsuarioRequest;
import io.github.joefarias.apireservas.service.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse cadastrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        return usuarioService.cadastraUsuario(usuarioRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Usuario> listarUsuarios(@ApiIgnore
                                            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC)
                                        Pageable pageable) {
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse buscarUsuarioPorId(@PathVariable Long idUsuario) {
        return usuarioService.buscarUsuarioResponsePorId(idUsuario);
    }

    @GetMapping("/cpf/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse buscarUsuarioPorCpf(@PathVariable String cpf) {
        return usuarioService.buscarUsuarioPorCpf(cpf);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse alteraUsuarioPorId(@PathVariable Long id, @RequestBody @Valid
                                              AtualizarUsuarioRequest atualizarUsuarioRequest){
        return usuarioService.alteraUsuarioPorId(id, atualizarUsuarioRequest);
    }


}
