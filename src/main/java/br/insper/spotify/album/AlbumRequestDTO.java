package br.insper.spotify.album;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AlbumRequestDTO {

    @NotNull
    @NotEmpty
    private String titulo;

    private LocalDate dataLancamento;

    @NotNull
    private Long artistaId;
}
