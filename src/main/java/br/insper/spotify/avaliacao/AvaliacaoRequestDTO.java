package br.insper.spotify.avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoRequestDTO {

    private String comentario;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long musicaId;
}
