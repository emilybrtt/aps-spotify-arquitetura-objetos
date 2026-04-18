package br.insper.spotify.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @Email
    private String email;

    private TipoPlano tipoPlano;

    public static Usuario toModel(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTipoPlano(dto.getTipoPlano());
        return usuario;
    }
}
