package br.insper.spotify.avaliacao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AvaliacaoResponseDTO {
    private Long id;
    private String comentario;
    private Integer nota;
    private String nomeUsuario;
    private String tituloMusica;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static AvaliacaoResponseDTO toDTO(Avaliacao avaliacao) {
        AvaliacaoResponseDTO dto = new AvaliacaoResponseDTO();
        dto.setId(avaliacao.getId());
        dto.setComentario(avaliacao.getComentario());
        dto.setNota(avaliacao.getNota());
        dto.setNomeUsuario(avaliacao.getUsuario() != null ? avaliacao.getUsuario().getNome() : null);
        dto.setTituloMusica(avaliacao.getMusica() != null ? avaliacao.getMusica().getTitulo() : null);
        dto.setDataCriacao(avaliacao.getDataCriacao());
        dto.setDataAtualizacao(avaliacao.getDataAtualizacao());
        return dto;
    }
}
