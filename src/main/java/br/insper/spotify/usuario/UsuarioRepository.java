package br.insper.spotify.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndAtivoTrue(String email);
    boolean existsByEmailAndAtivoTrue(String email);
    Page<Usuario> findByAtivoTrue(Pageable pageable);
    Page<Usuario> findByAtivoTrueAndNomeContainingIgnoreCase(String nome, Pageable pageable);
    Optional<Usuario> findByIdAndAtivoTrue(Long id);
}