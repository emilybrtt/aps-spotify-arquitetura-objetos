package br.insper.spotify.musica;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MusicaResponseDTO {
    private Long id;
    private String titulo;
    private Integer duracaoSegundos;
    private Integer numeroFaixa;
    private String nomeArtista;
    private String tituloAlbum;
    private Long totalReproducoes;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static MusicaResponseDTO toDTO(Musica musica) {
        MusicaResponseDTO dto = new MusicaResponseDTO();
        dto.setId(musica.getId());
        dto.setTitulo(musica.getTitulo());
        dto.setDuracaoSegundos(musica.getDuracaoSegundos());
        dto.setNumeroFaixa(musica.getNumeroFaixa());
        dto.setNomeArtista(musica.getArtista() != null ? musica.getArtista().getNome() : null);
        dto.setTituloAlbum(musica.getAlbum() != null ? musica.getAlbum().getTitulo() : null);
        dto.setTotalReproducoes(musica.getTotalReproducoes());
        dto.setDataCriacao(musica.getDataCriacao());
        dto.setDataAtualizacao(musica.getDataAtualizacao());
        return dto;
    }
}
