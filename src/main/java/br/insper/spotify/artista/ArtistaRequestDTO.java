package br.insper.spotify.artista;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistaRequestDTO {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    private String generoMusical;

    private String paisOrigem;

    public static Artista toModel(ArtistaRequestDTO dto) {
        Artista artista = new Artista();
        artista.setNome(dto.getNome());
        artista.setGeneroMusical(dto.getGeneroMusical());
        artista.setPaisOrigem(dto.getPaisOrigem());
        return artista;
    }
}
