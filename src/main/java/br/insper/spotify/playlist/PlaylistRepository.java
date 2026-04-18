package br.insper.spotify.playlist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Page<Playlist> findByAtivoTrue(Pageable pageable);

    Optional<Playlist> findByIdAndAtivoTrue(Long id);

    Page<Playlist> findByAtivoTrueAndUsuarioId(Long usuarioId, Pageable pageable);

    Page<Playlist> findByAtivoTrueAndPublicaTrue(Pageable pageable);

    Page<Playlist> findByAtivoTrueAndNomeContainingIgnoreCase(String nome, Pageable pageable);
}