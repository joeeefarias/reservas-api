package io.github.joefarias.apireservas.service;

import io.github.joefarias.apireservas.domain.*;
import io.github.joefarias.apireservas.repository.ReservaRepository;
import io.github.joefarias.apireservas.service.request.CadastrarReservaRequest;
import io.github.joefarias.apireservas.service.response.reserva.DadosAnuncioResponse;
import io.github.joefarias.apireservas.service.response.reserva.DadosSolicitanteResponse;
import io.github.joefarias.apireservas.service.response.reserva.InformacaoReservaResponse;
import io.github.joefarias.apireservas.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Service
public class ReservaService {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private ReservaService reservaService;



    @Autowired
    private ReservaRepository repository;

    public InformacaoReservaResponse cadastrarReserva(CadastrarReservaRequest reservaRequest) {

        Periodo periodo = new Periodo();
            periodo.setDataHoraInicial(reservaRequest.getPeriodo().getDataHoraInicial()
                    .withHour(14).withMinute(0).withSecond(0));
            periodo.setDataHoraFinal(reservaRequest.getPeriodo().getDataHoraFinal()
                    .withHour(12).withMinute(0).withSecond(0));
        if (periodo.getDataHoraFinal().toLocalDate().isBefore(periodo.getDataHoraInicial().toLocalDate()))
                throw new PeriodoInvalidoException();
        if (periodo.getDataHoraFinal().toLocalDate().isBefore(periodo.getDataHoraInicial().toLocalDate().plusDays(1)))
                throw new PeriodoMenorQueUmDiaException();

        Usuario solicitante = usuarioService.buscarUsuarioPorId(reservaRequest.getIdSolicitante());

        Anuncio anuncio = anuncioService.buscarAnuncioPorId(reservaRequest.getIdAnuncio());

        if (solicitante.getId().equals(anuncio.getAnunciante().getId()))
            throw new SolicitanteInvalidoExcpetion();


        if (reservaRequest.getQuantidadePessoas() < 2 && anuncio.getImovel().getTipoImovel()
                .equals(TipoImovel.HOTEL)){
            throw new QuantidadeMinimaDePessoasException();
        }

        Long diarias = ChronoUnit.DAYS.between(periodo.getDataHoraInicial().toLocalDate(),
                periodo.getDataHoraFinal().toLocalDate());

        if(diarias < 5 && anuncio.getImovel().getTipoImovel()
                .equals(TipoImovel.POUSADA)){
            throw new QauntidaMinimaDeDiariasException();
        }

        if(repository.existeReservaNoPeriodo(reservaRequest.getIdAnuncio(), periodo.getDataHoraInicial(),
                periodo.getDataHoraFinal())){
            throw new ReservaAtivaNoMesmoPeriodoException();
        }

        Pagamento pagamento = Pagamento.builder().status(StatusPagamento.PENDENTE).
                valorTotal(anuncio.getValorDiaria().multiply(BigDecimal.valueOf(diarias))).build();

        Reserva reserva = Reserva.builder().solicitante(solicitante).anuncio(anuncio).pagamento(pagamento).
                dataHoraReserva(LocalDateTime.now()).quantidadePessoas(reservaRequest.getQuantidadePessoas()).
                periodo(periodo).build();



        reserva = repository.save(reserva);

        return getInformacaoReservaResponse(reserva);

    }

    private InformacaoReservaResponse getInformacaoReservaResponse(Reserva reserva) {
        return InformacaoReservaResponse.builder().idReserva(reserva.getId())
                .solicitante(DadosSolicitanteResponse.builder().idSolicitante(reserva.getSolicitante().getId())
                        .nomeSolicitante(reserva.getSolicitante().getNome()).build()).quantidadePessoas(reserva.getQuantidadePessoas()).
                            anuncio(DadosAnuncioResponse.builder().id(reserva.getAnuncio().getId()).imovel(reserva.getAnuncio().getImovel())
                                    .anunciante(reserva.getAnuncio().getAnunciante()).formasAceitas(reserva.getAnuncio().getFormasAceitas())
                                    .descricao(reserva.getAnuncio().getDescricao()).build()).periodo(reserva.getPeriodo()).
                                        pagamento(reserva.getPagamento()).build();
    }

        public Page<Reserva> listarReservaSolicitante(Long idSolicitante, Pageable pageable, String dataEntrada, String dataSaida){
            LocalDateTime localDateTimeEntrada = null;
            LocalDateTime localDateTimeSaida = null;

        if(dataEntrada != null && !dataEntrada.isEmpty() && dataSaida != null && !dataSaida.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                localDateTimeEntrada = LocalDateTime.parse(dataEntrada, formatter);
                localDateTimeSaida = LocalDateTime.parse(dataSaida, formatter);
            }


            return repository.findByIdSolicitante(idSolicitante, pageable, localDateTimeEntrada, localDateTimeSaida);

        }

    public void pagarReserva(Long idReserva, FormaPagamento formaPagamento){
        Reserva reserva = repository.findById(idReserva).orElseThrow(() ->
                new ReservaNaoEncontradaException(idReserva));

        if(Arrays.asList(StatusPagamento.CANCELADO, StatusPagamento.PAGO, StatusPagamento.ESTORNADO)
                .contains(reserva.getPagamento().getStatus())){
            throw new ReservaNaoPagavelException();
        }
        if (!formaPagamentoAceita(formaPagamento, reserva)){
            throw new FormaNaoAceitaException(formaPagamento,
                    reserva.getAnuncio().getFormasAceitas());
        }


        reserva.getPagamento().setStatus(StatusPagamento.PAGO);
        reserva.getPagamento().setFormaEscolhida(formaPagamento);
        repository.save(reserva);

    }

    private Boolean formaPagamentoAceita(FormaPagamento formaPagamento, Reserva reserva){
        return reserva.getAnuncio().getFormasAceitas().contains(formaPagamento);
    }


    public Page<Reserva> listarReservaAnunciante(Long idAnunciante, Pageable pageable) {

        return repository.findByIdAnunciante(idAnunciante, pageable);

    }

    public void cancelarReserva(Long idReserva) {
        Reserva reserva = repository.findById(idReserva).orElseThrow(() ->
                new ReservaNaoEncontradaException(idReserva));

        if (Arrays.asList(StatusPagamento.CANCELADO, StatusPagamento.PAGO, StatusPagamento.ESTORNADO)
                .contains(reserva.getPagamento().getStatus())) {
            throw new ReservaNaoPendenteException();
        }

        reserva.getPagamento().setStatus(StatusPagamento.CANCELADO);
        repository.save(reserva);
    }

    public void estornarReserva(Long idReserva) {
        Reserva reserva = repository.findById(idReserva).orElseThrow(() ->
                new ReservaNaoEncontradaException(idReserva));

        if (Arrays.asList(StatusPagamento.CANCELADO, StatusPagamento.PENDENTE, StatusPagamento.ESTORNADO)
                .contains(reserva.getPagamento().getStatus())) {
            throw new ReservaNaoPagaException();
        }

        reserva.getPagamento().setFormaEscolhida(null);
        reserva.getPagamento().setStatus(StatusPagamento.ESTORNADO);
        repository.save(reserva);
    }
}
