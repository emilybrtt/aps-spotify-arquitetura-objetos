package br.insper.spotify.musica;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicaRequestDTO {

    @NotNull
    @NotEmpty
    private String titulo;

    @Min(1)
    private Integer duracaoSegundos;

    private Integer numeroFaixa;

    @NotNull
    private Long artistaId;

    @NotNull
    private Long albumId;
}
