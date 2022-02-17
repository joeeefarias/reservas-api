package io.github.joefarias.apireservas.controller;

import io.github.joefarias.apireservas.domain.FormaPagamento;
import io.github.joefarias.apireservas.domain.Reserva;
import io.github.joefarias.apireservas.service.ReservaService;
import io.github.joefarias.apireservas.service.request.CadastrarReservaRequest;
import io.github.joefarias.apireservas.service.response.reserva.InformacaoReservaResponse;
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
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoReservaResponse cadastrarReserva(@RequestBody @Valid CadastrarReservaRequest reservaRequest){
        return reservaService.cadastrarReserva(reservaRequest);
    }

    @PutMapping("/{idReserva}/pagamentos")
    @ResponseStatus(HttpStatus.OK)
    public void pagarReserva(@PathVariable Long idReserva, @RequestBody @Valid FormaPagamento formaPagamento){
        reservaService.pagarReserva(idReserva, formaPagamento);
    }

    @PutMapping("/{idReserva}/pagamentos/cancelar")
    @ResponseStatus(HttpStatus.OK)
    public void cancelarReserva(@PathVariable Long idReserva){
        reservaService.cancelarReserva(idReserva);
    }

    @PutMapping("/{idReserva}/pagamentos/estornar")
    @ResponseStatus(HttpStatus.OK)
    public void estornarReserva(@PathVariable Long idReserva){
        reservaService.estornarReserva(idReserva);
    }

    @GetMapping("/solicitantes/{idSolicitante}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Reserva> listarReservaSolicitante(@PathVariable Long idSolicitante,
                                                  @ApiIgnore @PageableDefault(sort = "periodo.dataHoraFinal",
                                                          direction = Sort.Direction.DESC)
                                                          Pageable pageable, @RequestParam(name = "dataHoraInicial",
            required = false) String dataEntrada,@RequestParam(name = "dataHoraFinal", required = false) String dataSaida) {
        return reservaService.listarReservaSolicitante(idSolicitante, pageable, dataEntrada, dataSaida);
    }

    @GetMapping("/anuncios/anunciantes/{idAnunciante}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Reserva> listarReservaAnunciante(@PathVariable Long idAnunciante,
                                                  @ApiIgnore @PageableDefault(sort = "periodo.dataHoraFinal",
                                                          direction = Sort.Direction.DESC)
                                                          Pageable pageable) {
        return reservaService.listarReservaAnunciante(idAnunciante, pageable);
    }


}
