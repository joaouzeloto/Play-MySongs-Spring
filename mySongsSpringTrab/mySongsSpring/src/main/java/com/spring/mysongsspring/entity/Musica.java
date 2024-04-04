package com.spring.mysongsspring.entity;

public class Musica {
    private String nome;
    private String genero;
    private String autor;
    private String urlMusica;
    private String urlCapa; // Adicionando o campo para a URL da capa do álbum

    public Musica() {
        this("", "", "", "", "");
    }

    public Musica(String nome, String genero, String autor, String urlMusica, String urlCapa) {
        this.nome = nome;
        this.genero = genero;
        this.autor = autor;
        this.urlMusica = urlMusica;
        this.urlCapa = urlCapa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUrlMusica() {
        return urlMusica;
    }

    public void setUrlMusica(String urlMusica) {
        this.urlMusica = urlMusica;
    }

    public String getUrlCapa() {
        return urlCapa;
    }

    public void setUrlCapa(String urlCapa) {
        this.urlCapa = urlCapa;
    }
}

