package io.github.joefarias.apireservas.controller;

import io.github.joefarias.apireservas.domain.Anuncio;
import io.github.joefarias.apireservas.service.AnuncioService;
import io.github.joefarias.apireservas.service.request.CadastrarAnuncioRequest;
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
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio anunciarImovel(@RequestBody @Valid CadastrarAnuncioRequest cadastrarAnuncioRequest){
        return anuncioService.anunciarImovel(cadastrarAnuncioRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Anuncio> listarAnuncios(@ApiIgnore
                                            @PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC)
                                        Pageable pageable){
        return anuncioService.listarAnuncios(pageable);
    }

    @GetMapping("/anunciantes/{idAnunciante}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Anuncio> listarAnunciosPorAnunciante(@PathVariable Long idAnunciante, @ApiIgnore
                                                     @PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC)
                                                     Pageable pageable){
        return anuncioService.listarAnunciosPorAnunciante(idAnunciante, pageable);
    }

    @DeleteMapping("/{idAnuncio}")
    @ResponseStatus(HttpStatus.OK)
    public void excluirAnuncio(@PathVariable Long idAnuncio){
        anuncioService.excluirAnuncio(idAnuncio);
    }

}
