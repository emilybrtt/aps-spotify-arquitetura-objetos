package br.insper.spotify.musica;

import br.insper.spotify.album.Album;
import br.insper.spotify.album.AlbumService;
import br.insper.spotify.artista.Artista;
import br.insper.spotify.artista.ArtistaService;
import br.insper.spotify.exception.OperacaoNaoPermitidaException;
import br.insper.spotify.exception.RecursoNaoEncontradoException;
import br.insper.spotify.usuario.Usuario;
import br.insper.spotify.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ArtistaService artistaService;
    @Autowired
    private AlbumService albumService;


    // ==================== POST =======================
    public MusicaResponseDTO cadastrarMusica(MusicaRequestDTO dto) {
        Artista artista = artistaService.buscarArtista(dto.getArtistaId());
        Album album = albumService.buscarAlbum(dto.getAlbumId());

        Musica musica = new Musica();
        musica.setTitulo(dto.getTitulo());
        musica.setDuracaoSegundos(dto.getDuracaoSegundos());
        musica.setNumeroFaixa(dto.getNumeroFaixa());
        musica.setArtista(artista);
        musica.setAlbum(album);
        musica = musicaRepository.save(musica);
        return MusicaResponseDTO.toDTO(musica);
    }


    // ==================== GET ALL =======================
    public Page<MusicaResponseDTO> listarMusicas(Pageable pageable) {
        return musicaRepository.findByAtivoTrue(pageable)
                .map(MusicaResponseDTO::toDTO);
    }


    // ==================== GET /ID =======================
    public Musica buscarMusica(Long id) {
        return musicaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Musica não encontrada"));
    }

    public MusicaResponseDTO buscarMusicaDTO(Long id) {
        return MusicaResponseDTO.toDTO(buscarMusica(id));
    }

    // ==================== GET /TITULO, /ARTISTA, /ALBUM  =======================

    public Page<MusicaResponseDTO> buscarPorTitulo(String titulo, Pageable pageable) {
        return musicaRepository
                .findByAtivoTrueAndTituloContainingIgnoreCase(titulo, pageable)
                .map(MusicaResponseDTO::toDTO);
    }

    public Page<MusicaResponseDTO> buscarPorArtista(Long artistaId, Pageable pageable) {
        return musicaRepository
                .findByAtivoTrueAndArtistaId(artistaId, pageable)
                .map(MusicaResponseDTO::toDTO);
    }

    public Page<MusicaResponseDTO> buscarPorAlbum(Long albumId, Pageable pageable) {
        return musicaRepository
                .findByAtivoTrueAndAlbumId(albumId, pageable)
                .map(MusicaResponseDTO::toDTO);
    }

    // ==================== PUT =======================
    public MusicaResponseDTO atualizarMusica(Long id, MusicaRequestDTO dto) {
        Musica musica = buscarMusica(id);
        musica.setTitulo(dto.getTitulo());
        musica.setDuracaoSegundos(dto.getDuracaoSegundos());
        musica.setNumeroFaixa(dto.getNumeroFaixa());
        musica = musicaRepository.save(musica);
        return MusicaResponseDTO.toDTO(musica);
    }


    // ==================== DELETE =======================
    public void deletarMusica(Long id) {
        Musica musica = buscarMusica(id);
        musica.setAtivo(false);
        musicaRepository.save(musica);
    }


    // ==================== PLAY SONG =======================
    public void reproduzirMusica(Long musicaId, Long usuarioId) {
        Usuario usuario = usuarioService.buscarUsuarioIgnorandoAtivo(usuarioId);
        if (!usuario.isAtivo()) throw new OperacaoNaoPermitidaException("Usuário inativo não pode reproduzir músicas");
        Musica musica = buscarMusica(musicaId);
        musica.reproduzirMusica();
        musicaRepository.save(musica);
    }

}