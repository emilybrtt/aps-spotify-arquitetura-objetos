package br.insper.spotify.musica;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    // ==================== POST =======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicaResponseDTO cadastrarMusica(@Valid @RequestBody MusicaRequestDTO dto) {
        return musicaService.cadastrarMusica(dto);
    }

    // ==================== GET ALL =======================
    @GetMapping
    public Page<MusicaResponseDTO> getMusicas(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Long artistaId,
            @RequestParam(required = false) Long albumId,
            Pageable pageable) {

        if (titulo != null) {
            return musicaService.buscarPorTitulo(titulo, pageable);
        }

        if (artistaId != null) {
            return musicaService.buscarPorArtista(artistaId, pageable);
        }

        if (albumId != null) {
            return musicaService.buscarPorAlbum(albumId, pageable);
        }

        return musicaService.listarMusicas(pageable);
    }

    // ==================== GET ONE =======================
    @GetMapping("/{id}")
    public MusicaResponseDTO getMusica(@PathVariable Long id) {
        return musicaService.buscarMusicaDTO(id);
    }

    // ==================== PUT =======================
    @PutMapping("/{id}")
    public MusicaResponseDTO updateMusica(@PathVariable Long id, @Valid @RequestBody MusicaRequestDTO dto) {
        return musicaService.atualizarMusica(id, dto);
    }

    // ==================== DELETE =======================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMusica(@PathVariable Long id) {
        musicaService.deletarMusica(id);
    }

    // ==================== PLAY SONG =======================
    @PostMapping("/{id}/reproduzir")
    public void reproduzirMusica(@PathVariable Long id, @RequestHeader("X-USER-ID") Long userId) {
        musicaService.reproduzirMusica(id, userId);
    }
}