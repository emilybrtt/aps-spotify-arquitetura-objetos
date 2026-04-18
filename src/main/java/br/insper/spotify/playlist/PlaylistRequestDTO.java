package br.insper.spotify.playlist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistRequestDTO {

    @NotNull
    @NotEmpty
    private String nome;

    private boolean publica;

    @NotNull
    private Long usuarioId;
}
