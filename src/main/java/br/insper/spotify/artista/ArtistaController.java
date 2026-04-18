package br.insper.spotify.artista;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    // ==================== POST =======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistaResponseDTO cadastrarArtista(@Valid @RequestBody ArtistaRequestDTO dto) {
        return artistaService.cadastrarArtista(dto);
    }

    // ==================== GET ALL =======================
    @GetMapping
    public Page<ArtistaResponseDTO> getArtistas(Pageable pageable) {
        return artistaService.listarArtistas(pageable);
    }

    // ==================== GET ONE =======================
    @GetMapping("/{id}")
    public ArtistaResponseDTO getArtista(@PathVariable Long id) {
        return artistaService.buscarArtistaDTO(id);
    }

    // ==================== GET /NAME =======================
    @GetMapping("/buscar")
    public Page<ArtistaResponseDTO> buscar(@RequestParam String nome, Pageable pageable) {
        return artistaService.buscarPorNome(nome, pageable);
    }

    // ==================== PUT =======================
    @PutMapping("/{id}")
    public ArtistaResponseDTO updateArtista(@PathVariable Long id, @Valid @RequestBody ArtistaRequestDTO dto) {
        return artistaService.atualizarArtista(id, dto);
    }

    // ==================== DELETE =======================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtista(@PathVariable Long id) {
        artistaService.deletarArtista(id);
    }
}
