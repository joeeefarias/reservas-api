package io.github.joefarias.apireservas.controller;

import io.github.joefarias.apireservas.domain.Imovel;
import io.github.joefarias.apireservas.service.ImovelService;
import io.github.joefarias.apireservas.service.request.CadastrarImovelRequest;
import io.github.joefarias.apireservas.service.response.ImovelResponse;
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
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Imovel cadastrarImovel(@RequestBody @Valid CadastrarImovelRequest cadastrarImovelRequest){
        return imovelService.cadastrarImovel(cadastrarImovelRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Imovel> listarTodos(@ApiIgnore
                                    @PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC)
                                                Pageable pageable){
      return imovelService.listarImoveis(pageable);
    }

    @GetMapping("proprietarios/{idProprietario}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Imovel> listarImoveisProprietario(@PathVariable Long idProprietario,
                                                          @PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC)
                                                          Pageable pageable){
        return imovelService.listarImoveisProprietario(idProprietario, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImovelResponse buscarImovelPorId(@PathVariable Long id){
        return imovelService.buscarImovelResponsePorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void excluirImovel(@PathVariable Long id){
        imovelService.excluirImovel(id);
    }
}
