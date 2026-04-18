package br.insper.spotify.musica;

import br.insper.spotify.album.Album;
import br.insper.spotify.artista.Artista;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "musicas")
@Getter
@Setter
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Min(1)
    @Column(name = "duracao_segundos")
    private Integer duracaoSegundos;

    @Column(name = "numero_faixa")
    private Integer numeroFaixa;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @Column(name = "total_reproducoes", nullable = false)
    private Long totalReproducoes = 0L;

    @Column(nullable = false)
    private boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public void reproduzirMusica() {
        this.totalReproducoes++;
    }
}
