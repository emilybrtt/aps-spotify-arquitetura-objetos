package br.insper.spotify.album;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AlbumResponseDTO {
    private Long id;
    private String titulo;
    private LocalDate dataLancamento;
    private String nomeArtista;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static AlbumResponseDTO toDTO(Album album) {
        AlbumResponseDTO dto = new AlbumResponseDTO();
        dto.setId(album.getId());
        dto.setTitulo(album.getTitulo());
        dto.setDataLancamento(album.getDataLancamento());
        dto.setNomeArtista(album.getArtista() != null ? album.getArtista().getNome() : null);
        dto.setDataCriacao(album.getDataCriacao());
        dto.setDataAtualizacao(album.getDataAtualizacao());
        return dto;
    }
}
