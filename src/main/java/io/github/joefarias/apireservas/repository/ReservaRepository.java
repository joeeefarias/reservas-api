package io.github.joefarias.apireservas.repository;

import io.github.joefarias.apireservas.domain.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("select count(*) > 0 from Reserva r where r.anuncio.id = :idAnuncio and ((r.periodo.dataHoraFinal" +
            " between :dataEntrada and :dataSaida) or (r.periodo.dataHoraInicial between :dataEntrada and :dataSaida))")
    boolean existeReservaNoPeriodo(@Param("idAnuncio") Long idAnuncio,
                                   @Param("dataEntrada") LocalDateTime dataEntrada,
                                   @Param("dataSaida") LocalDateTime dataSaida);

    @Query("select r from Reserva r where r.solicitante.id = :idSolicitante and (((:dataEntrada IS NULL and :dataSaida IS NULL)" +
            " or (:dataEntrada IS NULL and :dataSaida IS NOT NULL) or (:dataEntrada IS NOT NULL and :dataSaida IS NULL)) or ((r.periodo.dataHoraFinal " +
            " between :dataEntrada and :dataSaida) or (r.periodo.dataHoraInicial between :dataEntrada and :dataSaida)))")
    Page<Reserva> findByIdSolicitante(@Param("idSolicitante") Long idSolicitante, Pageable pageable,
                                        @Param("dataEntrada")LocalDateTime dataEntrada,
                                        @Param("dataSaida") LocalDateTime dataSaida);

    @Query("select r from Reserva r where r.anuncio.anunciante.id = :idAnunciante")
    Page<Reserva> findByIdAnunciante(@Param("idAnunciante") Long idAnunciante, Pageable pageable);

}
