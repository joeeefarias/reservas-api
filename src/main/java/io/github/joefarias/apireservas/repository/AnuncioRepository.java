package io.github.joefarias.apireservas.repository;

import io.github.joefarias.apireservas.domain.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    @Query("select count(a) > 0 from Anuncio a where a.imovel.id = :idImovel and a.delflag = 'N'")
    boolean existsByIdImovel(@Param("idImovel") Long idImovel);

    @Query("select a from Anuncio a where a.imovel.id = :idImovel")
    Anuncio findByIdImovel (@Param("idImovel") Long idImovel);

    @Query("select a from Anuncio a where a.anunciante.id = :idAnunciante")
    Page<Anuncio> findImovelByIdAnunciante(@Param("idAnunciante") Long idAnunciante, Pageable pageable);
}
