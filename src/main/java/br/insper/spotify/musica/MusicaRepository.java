package br.insper.spotify.musica;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

    Optional<Musica> findByIdAndAtivoTrue(Long id);

    Page<Musica> findByAtivoTrueAndTituloContainingIgnoreCase(String titulo, Pageable pageable);

    Page<Musica> findByAtivoTrueAndArtistaId(Long artistaId, Pageable pageable);

    Page<Musica> findByAtivoTrueAndAlbumId(Long albumId, Pageable pageable);

    List<Musica> findByAtivoTrueOrderByTotalReproducoesDesc();

    List<Musica> findByAtivoTrue();

    Page<Musica> findByAtivoTrue(Pageable pageable);

    List<Musica> findTop10ByAtivoTrueOrderByTotalReproducoesDesc();
}