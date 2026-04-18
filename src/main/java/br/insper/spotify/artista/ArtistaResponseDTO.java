package br.insper.spotify.artista;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArtistaResponseDTO {
    private Long id;
    private String nome;
    private String generoMusical;
    private String paisOrigem;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static ArtistaResponseDTO toDTO(Artista artista) {
        ArtistaResponseDTO dto = new ArtistaResponseDTO();
        dto.setId(artista.getId());
        dto.setNome(artista.getNome());
        dto.setGeneroMusical(artista.getGeneroMusical());
        dto.setPaisOrigem(artista.getPaisOrigem());
        dto.setDataCriacao(artista.getDataCriacao());
        dto.setDataAtualizacao(artista.getDataAtualizacao());
        return dto;
    }
}
