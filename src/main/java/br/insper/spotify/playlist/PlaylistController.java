package br.insper.spotify.playlist;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    // ==================== POST =======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistResponseDTO cadastrarPlaylist(@Valid @RequestBody PlaylistRequestDTO dto) {
        return playlistService.cadastrarPlaylist(dto);
    }

    // ==================== GET ALL =======================
    @GetMapping
    public Page<PlaylistResponseDTO> getPlaylists(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Boolean publica,
            @RequestParam(required = false) String nome,
            Pageable pageable) {

        if (nome != null) {
            return playlistService.buscarPorNome(nome, pageable);
        }

        if (usuarioId != null) {
            return playlistService.buscarPorUsuario(usuarioId, pageable);
        }

        if (Boolean.TRUE.equals(publica)) {
            return playlistService.buscarPublicas(pageable);
        }

        return playlistService.listarPlaylists(pageable);
    }

    // ==================== GET ONE =======================
    @GetMapping("/{id}")
    public PlaylistResponseDTO getPlaylist(@PathVariable Long id) {
        return playlistService.buscarPlaylistDTO(id);
    }

    // ==================== PUT =======================
    @PutMapping("/{id}")
    public PlaylistResponseDTO updatePlaylist(@PathVariable Long id, @Valid @RequestBody PlaylistRequestDTO dto) {
        return playlistService.atualizarPlaylist(id, dto);
    }

    // ==================== DELETE =======================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlaylist(@PathVariable Long id) {
        playlistService.deletarPlaylist(id);
    }

    // ==================== ADD MUSICA =======================
    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public void addMusic(@PathVariable Long playlistId, @PathVariable Long musicaId,
                         @RequestHeader("X-USER-ID") Long userId) {
        playlistService.addMusic(playlistId, musicaId, userId);
    }
}
