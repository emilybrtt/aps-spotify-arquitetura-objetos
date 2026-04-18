package br.insper.spotify.relatorios;

import br.insper.spotify.musica.MusicaResumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RelatoriosController {

    @Autowired
    private RelatoriosService relatoriosService;

    @GetMapping("/relatorios/top-musicas")
    public List<MusicaResumoDTO> topMusicas() {
        return relatoriosService.rankMusicas();
    }

}
