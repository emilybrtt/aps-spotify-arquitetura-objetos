package br.insper.spotify.usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // ==================== POST =======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO cadastrarUsuario(@Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.cadastrarUsuario(dto);
    }

    // ==================== GET ALL =======================
    @GetMapping
    public Page<UsuarioResponseDTO> getUsuarios(
            @RequestParam(required = false) String nome,
            Pageable pageable) {

        if (nome != null && !nome.isBlank()) {
            return usuarioService.buscarPorNome(nome, pageable);
        }

        return usuarioService.listarUsuarios(pageable);
    }

    // ==================== GET ONE =======================
    @GetMapping("/{id}")
    public UsuarioResponseDTO getUsuario(@PathVariable Long id) {
        return usuarioService.buscarUsuarioDTO(id);

    }


    // ==================== PUT =======================
    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.atualizarUsuario(id, dto);
    }

    // ==================== DELETE =======================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }
}
