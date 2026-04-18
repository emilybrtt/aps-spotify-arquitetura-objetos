package br.insper.spotify.artista;

import br.insper.spotify.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import br.insper.spotify.exception.DadosInvalidosException;
import br.insper.spotify.exception.RecursoJaExisteException;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    // ==================== POST =======================
    public ArtistaResponseDTO cadastrarArtista(ArtistaRequestDTO dto) {
        if (artistaRepository.existsByNomeAndAtivoTrue(dto.getNome())) {
            throw new RecursoJaExisteException("Já existe um artista com esse nome");
        }

        Artista artista = ArtistaRequestDTO.toModel(dto);
        artista = artistaRepository.save(artista);
        return ArtistaResponseDTO.toDTO(artista);
    }


    // ==================== GET ALL =======================
    public Page<ArtistaResponseDTO> listarArtistas(Pageable pageable) {
        return artistaRepository.findByAtivoTrue(pageable)
                .map(ArtistaResponseDTO::toDTO);
    }


    // ==================== GET /ID =======================
    public Artista buscarArtista(Long id) {
        return artistaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Artista não encontrado"));
    }

    public ArtistaResponseDTO buscarArtistaDTO(Long id) {
        return ArtistaResponseDTO.toDTO(buscarArtista(id));
    }

    // ==================== GET /NAME =======================

    public Page<ArtistaResponseDTO> buscarPorNome(String nome, Pageable pageable) {
        return artistaRepository
                .findByAtivoTrueAndNomeContainingIgnoreCase(nome, pageable)
                .map(ArtistaResponseDTO::toDTO);
    }
    // ==================== PUT =======================
    public ArtistaResponseDTO atualizarArtista(Long id, ArtistaRequestDTO dto) {
        Artista artista = buscarArtista(id);
        artista.setNome(dto.getNome());
        artista.setGeneroMusical(dto.getGeneroMusical());
        if (dto.getPaisOrigem() != null) {
            artista.setPaisOrigem(dto.getPaisOrigem());
        }
        artista = artistaRepository.save(artista);
        return ArtistaResponseDTO.toDTO(artista);
    }


    // ==================== DELETE =======================
    public void deletarArtista(Long id) {
        Artista artista = buscarArtista(id);
        artista.setAtivo(false);
        artistaRepository.save(artista);
    }
}