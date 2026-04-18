package br.insper.spotify.avaliacao;

import br.insper.spotify.exception.RecursoNaoEncontradoException;
import br.insper.spotify.musica.Musica;
import br.insper.spotify.musica.MusicaService;
import br.insper.spotify.usuario.Usuario;
import br.insper.spotify.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MusicaService musicaService;


    // ==================== POST =======================
    public AvaliacaoResponseDTO cadastrarAvaliacao(AvaliacaoRequestDTO dto) {
        Usuario usuario = usuarioService.buscarUsuario(dto.getUsuarioId());
        Musica musica = musicaService.buscarMusica(dto.getMusicaId());

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setNota(dto.getNota());
        avaliacao.setUsuario(usuario);
        avaliacao.setMusica(musica);
        avaliacao = avaliacaoRepository.save(avaliacao);
        return AvaliacaoResponseDTO.toDTO(avaliacao);
    }


    // ==================== GET ALL =======================
    public Page<AvaliacaoResponseDTO> listarAvaliacoes(Pageable pageable) {
        return avaliacaoRepository.findByAtivoTrue(pageable)
                .map(AvaliacaoResponseDTO::toDTO);
    }


    // ==================== GET ONE =======================
    public Avaliacao buscarAvaliacao(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Avaliação não encontrada"));
        if (!avaliacao.isAtivo()) throw new RecursoNaoEncontradoException("Avaliação não encontrada");
        return avaliacao;
    }

    public AvaliacaoResponseDTO buscarAvaliacaoDTO(Long id) {
        return AvaliacaoResponseDTO.toDTO(buscarAvaliacao(id));
    }


    // ==================== PUT =======================
    public AvaliacaoResponseDTO atualizarAvaliacao(Long id, AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = buscarAvaliacao(id);
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        avaliacao = avaliacaoRepository.save(avaliacao);
        return AvaliacaoResponseDTO.toDTO(avaliacao);
    }


    // ==================== DELETE =======================
    public void deletarAvaliacao(Long id) {
        Avaliacao avaliacao = buscarAvaliacao(id);
        avaliacao.setAtivo(false);
        avaliacaoRepository.save(avaliacao);
    }
}
