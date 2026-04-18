package br.insper.spotify.playlist;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlaylistResponseDTO {
    private Long id;
    private String nome;
    private boolean publica;
    private String nomeUsuario;
    private int totalMusicas;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public static PlaylistResponseDTO toDTO(Playlist playlist) {
        PlaylistResponseDTO dto = new PlaylistResponseDTO();
        dto.setId(playlist.getId());
        dto.setNome(playlist.getNome());
        dto.setPublica(playlist.isPublica());
        dto.setNomeUsuario(playlist.getUsuario() != null ? playlist.getUsuario().getNome() : null);
        dto.setTotalMusicas(playlist.getMusicas() != null ? playlist.getMusicas().size() : 0);
        dto.setDataCriacao(playlist.getDataCriacao());
        dto.setDataAtualizacao(playlist.getDataAtualizacao());
        return dto;
    }
}
