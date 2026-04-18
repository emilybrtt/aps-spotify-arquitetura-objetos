package br.insper.spotify.album;

import br.insper.spotify.artista.Artista;
import br.insper.spotify.artista.ArtistaService;
import br.insper.spotify.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaService artistaService;


    // ==================== POST =======================
    public AlbumResponseDTO cadastrarAlbum(AlbumRequestDTO dto) {
        Artista artista = artistaService.buscarArtista(dto.getArtistaId());

        Album album = new Album();
        album.setTitulo(dto.getTitulo());
        album.setDataLancamento(dto.getDataLancamento());
        album.setArtista(artista);

        album = albumRepository.save(album);
        return AlbumResponseDTO.toDTO(album);
    }


    // ==================== GET ALL =======================
    public Page<AlbumResponseDTO> listarAlbums(Pageable pageable) {
        return albumRepository.findByAtivoTrue(pageable).map(AlbumResponseDTO::toDTO);
    }


    // ==================== GET ONE =======================
    public Album buscarAlbum(Long id) {
        return albumRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Album não encontrado"));
    }

    public AlbumResponseDTO buscarAlbumDTO(Long id) {
        return AlbumResponseDTO.toDTO(buscarAlbum(id));
    }


    // ==================== PUT =======================
    public AlbumResponseDTO atualizarAlbum(Long id, AlbumUpdateDTO dto) {
        Album album = buscarAlbum(id);
        if (dto.getTitulo() != null) album.setTitulo(dto.getTitulo());
        if (dto.getDataLancamento() != null) album.setDataLancamento(dto.getDataLancamento());
        if (dto.getArtistaId() != null) {
            Artista artista = artistaService.buscarArtista(dto.getArtistaId());
            album.setArtista(artista);
        }
        album = albumRepository.save(album);
        return AlbumResponseDTO.toDTO(album);
    }


    // ==================== DELETE =======================
    public void deletarAlbum(Long id) {
        Album album = buscarAlbum(id);
        album.setAtivo(false);
        albumRepository.save(album);
    }
}
