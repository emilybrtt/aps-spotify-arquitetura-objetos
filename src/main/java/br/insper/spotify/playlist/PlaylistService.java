package br.insper.spotify.playlist;

import br.insper.spotify.exception.DadosInvalidosException;
import br.insper.spotify.exception.OperacaoNaoPermitidaException;
import br.insper.spotify.exception.RecursoNaoEncontradoException;
import br.insper.spotify.musica.Musica;
import br.insper.spotify.musica.MusicaService;
import br.insper.spotify.usuario.Usuario;
import br.insper.spotify.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MusicaService musicaService;


    // ==================== POST =======================
    public PlaylistResponseDTO cadastrarPlaylist(PlaylistRequestDTO dto) {
        Usuario usuario = usuarioService.buscarUsuario(dto.getUsuarioId());
        if (!usuario.isAtivo()) throw new OperacaoNaoPermitidaException("Usuário inativo não pode criar playlists");
        if (dto.getNome() == null || dto.getNome().isBlank()) throw new DadosInvalidosException("Nome da playlist é obrigatório");

        Playlist playlist = new Playlist();
        playlist.setNome(dto.getNome());
        playlist.setPublica(dto.isPublica());
        playlist.setUsuario(usuario);
        playlist = playlistRepository.save(playlist);
        return PlaylistResponseDTO.toDTO(playlist);
    }


    // ==================== GET ALL =======================
    public Page<PlaylistResponseDTO> listarPlaylists(Pageable pageable) {
        return playlistRepository.findByAtivoTrue(pageable)
                .map(PlaylistResponseDTO::toDTO);
    }


    // ==================== GET /ID =======================
    public Playlist buscarPlaylist(Long id) {
        return playlistRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Playlist não encontrada"));
    }

    public PlaylistResponseDTO buscarPlaylistDTO(Long id) {
        return PlaylistResponseDTO.toDTO(buscarPlaylist(id));
    }

    // ==================== GET / USER =======================
    public Page<PlaylistResponseDTO> buscarPorUsuario(Long usuarioId, Pageable pageable) {
        return playlistRepository
                .findByAtivoTrueAndUsuarioId(usuarioId, pageable)
                .map(PlaylistResponseDTO::toDTO);
    }


    // ==================== GET / NOME =======================

    public Page<PlaylistResponseDTO> buscarPorNome(String nome, Pageable pageable) {
        return playlistRepository
                .findByAtivoTrueAndNomeContainingIgnoreCase(nome, pageable)
                .map(PlaylistResponseDTO::toDTO);
    }


    // ==================== GET PUBLICAS =======================
    public Page<PlaylistResponseDTO> buscarPublicas(Pageable pageable) {
        return playlistRepository
                .findByAtivoTrueAndPublicaTrue(pageable)
                .map(PlaylistResponseDTO::toDTO);
    }

    // ==================== PUT =======================
    public PlaylistResponseDTO atualizarPlaylist(Long id, PlaylistRequestDTO dto) {
        Playlist playlist = buscarPlaylist(id);
        playlist.setNome(dto.getNome());
        playlist.setPublica(dto.isPublica());
        playlist = playlistRepository.save(playlist);
        return PlaylistResponseDTO.toDTO(playlist);
    }


    // ==================== DELETE =======================
    public void deletarPlaylist(Long id) {
        Playlist playlist = buscarPlaylist(id);
        playlist.setAtivo(false);
        playlistRepository.save(playlist);
    }


    // ==================== ADD MUSICA =======================
    @Transactional
    public void addMusic(Long playlistId, Long musicaId, Long userId) {
        Playlist playlist = buscarPlaylist(playlistId);
        Musica musica = musicaService.buscarMusica(musicaId);

        if (!playlist.getUsuario().getId().equals(userId))
            throw new OperacaoNaoPermitidaException("Apenas o dono da playlist pode adicionar músicas");
        if (playlist.getMusicas().contains(musica))
            throw new OperacaoNaoPermitidaException("Música já está na playlist");

        playlist.adicionarMusica(musica);
        playlistRepository.save(playlist);
    }
}
