package br.insper.spotify.usuario;

import br.insper.spotify.exception.DadosInvalidosException;
import br.insper.spotify.exception.RecursoJaExisteException;
import br.insper.spotify.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    // ==================== POST =======================
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new DadosInvalidosException("Nome é obrigatório");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new DadosInvalidosException("Email é obrigatório");
        }

        if (usuarioRepository.existsByEmailAndAtivoTrue(dto.getEmail())) {
            throw new RecursoJaExisteException("Usuário já existe");
        }

        Usuario usuarioExistente = usuarioRepository.findByEmail(dto.getEmail()).orElse(null);

        if (usuarioExistente != null) {
            usuarioExistente.setNome(dto.getNome());
            usuarioExistente.setTipoPlano(dto.getTipoPlano());
            usuarioExistente.setAtivo(true);
            return UsuarioResponseDTO.toDTO(usuarioRepository.save(usuarioExistente));
        }

        Usuario usuario = UsuarioRequestDTO.toModel(dto);
        return UsuarioResponseDTO.toDTO(usuarioRepository.save(usuario));
    }


    // ==================== GET ALL =======================
    public Page<UsuarioResponseDTO> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findByAtivoTrue(pageable)
                .map(UsuarioResponseDTO::toDTO);
    }

    // ==================== GET /NOME =======================

    public Page<UsuarioResponseDTO> buscarPorNome(String nome, Pageable pageable) {
        return usuarioRepository.findByAtivoTrueAndNomeContainingIgnoreCase(nome, pageable)
                .map(UsuarioResponseDTO::toDTO);
    }

    // ==================== GET /ID =======================
    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    public Usuario buscarUsuarioIgnorandoAtivo(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    // ==================== GET /ID =======================


    public UsuarioResponseDTO buscarUsuarioDTO(Long id) {
        return UsuarioResponseDTO.toDTO(buscarUsuario(id));
    }


    // ==================== PUT =======================
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = buscarUsuario(id);

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTipoPlano(dto.getTipoPlano());

        usuario = usuarioRepository.save(usuario);
        return UsuarioResponseDTO.toDTO(usuario);
    }


    // ==================== DELETE =======================
    public void deletarUsuario(Long id) {
        Usuario usuario = buscarUsuario(id);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }
}
