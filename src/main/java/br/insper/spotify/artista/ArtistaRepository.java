package br.insper.spotify.artista;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Page<Artista> findByAtivoTrueAndNomeContainingIgnoreCase(String nome, Pageable pageable);
    Optional<Artista> findByIdAndAtivoTrue(Long id);
    boolean existsByNomeAndAtivoTrue(String nome);
    Page<Artista> findByAtivoTrue(Pageable pageable);

}
