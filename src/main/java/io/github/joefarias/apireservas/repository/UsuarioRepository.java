package io.github.joefarias.apireservas.repository;

import io.github.joefarias.apireservas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Usuario findByCpf(String cpf);


}
