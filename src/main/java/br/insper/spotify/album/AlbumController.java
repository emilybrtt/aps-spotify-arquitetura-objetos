package br.insper.spotify.album;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    // ==================== POST =======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponseDTO cadastrarAlbum(@Valid @RequestBody AlbumRequestDTO dto) {
        return albumService.cadastrarAlbum(dto);
    }

    // ==================== GET ALL =======================
    @GetMapping
    public Page<AlbumResponseDTO> getAlbums(Pageable pageable) {
        return albumService.listarAlbums(pageable);
    }


    // ==================== GET ONE =======================
    @GetMapping("/{id}")
    public AlbumResponseDTO getAlbum(@PathVariable Long id) {
        return albumService.buscarAlbumDTO(id);
    }


    // ==================== PUT =======================
    @PutMapping("/{id}")
    public AlbumResponseDTO updateAlbum(@PathVariable Long id, @RequestBody AlbumUpdateDTO dto) {
        return albumService.atualizarAlbum(id, dto);
    }

    // ==================== DELETE =======================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deletarAlbum(id);
    }
}
