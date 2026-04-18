package br.insper.spotify.avaliacao;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    // ==================== POST =======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoResponseDTO cadastrarAvaliacao(@Valid @RequestBody AvaliacaoRequestDTO dto) {
        return avaliacaoService.cadastrarAvaliacao(dto);
    }

    // ==================== GET ALL =======================
    @GetMapping
    public Page<AvaliacaoResponseDTO> getAvaliacaos(Pageable pageable) {
        return avaliacaoService.listarAvaliacoes(pageable);
    }

    // ==================== GET ONE =======================
    @GetMapping("/{id}")
    public AvaliacaoResponseDTO getAvaliacao(@PathVariable Long id) {
        return avaliacaoService.buscarAvaliacaoDTO(id);
    }

    // ==================== PUT =======================
    @PutMapping("/{id}")
    public AvaliacaoResponseDTO updateAvaliacao(@PathVariable Long id, @Valid @RequestBody AvaliacaoRequestDTO dto) {
        return avaliacaoService.atualizarAvaliacao(id, dto);
    }

    // ==================== DELETE =======================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAvaliacao(@PathVariable Long id) {
        avaliacaoService.deletarAvaliacao(id);
    }
}
