package br.insper.spotify.musica;

public class MusicaResumoDTO {

    private String titulo;
    private String nomeArtista;
    private long totalReproducoes;

    public MusicaResumoDTO(String titulo, String nomeArtista, long totalReproducoes) {
        this.titulo = titulo;
        this.nomeArtista = nomeArtista;
        this.totalReproducoes = totalReproducoes;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getNomeArtista() { return nomeArtista; }
    public void setNomeArtista(String nomeArtista) { this.nomeArtista = nomeArtista; }
    public long getTotalReproducoes() { return totalReproducoes; }
    public void setTotalReproducoes(long totalReproducoes) { this.totalReproducoes = totalReproducoes; }
}
