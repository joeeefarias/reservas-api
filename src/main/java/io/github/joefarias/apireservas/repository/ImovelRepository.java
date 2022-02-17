package io.github.joefarias.apireservas.repository;

import io.github.joefarias.apireservas.domain.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    @Query("select i from Imovel i where i.proprietario.id = :idProprietario")
    Page<Imovel> findByIdProprietarioIn(@Param("idProprietario") Long idPropirtario, Pageable pageable);


}
