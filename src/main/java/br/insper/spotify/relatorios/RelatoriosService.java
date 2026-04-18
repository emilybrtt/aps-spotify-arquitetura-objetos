package br.insper.spotify.relatorios;

import br.insper.spotify.musica.Musica;
import br.insper.spotify.musica.MusicaRepository;
import br.insper.spotify.musica.MusicaResumoDTO;
import br.insper.spotify.musica.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RelatoriosService {

    private final MusicaRepository musicaRepository;

    public RelatoriosService(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    public List<MusicaResumoDTO> rankMusicas() {
        return musicaRepository
                .findTop10ByAtivoTrueOrderByTotalReproducoesDesc()
                .stream()
                .map(m -> new MusicaResumoDTO(
                        m.getTitulo(),
                        m.getArtista().getNome(),
                        m.getTotalReproducoes()
                ))
                .toList();
    }
}