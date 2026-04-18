package br.insper.spotify.album;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AlbumUpdateDTO {
    private String titulo;
    private LocalDate dataLancamento;
    private Long artistaId;
}
