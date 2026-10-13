package model;

import contracts.Faturavel;
import contracts.Reproduzivel;
import helpers.Writer;

public class Anuncio implements Faturavel, Reproduzivel {
    private String anunciante;
    public String getAnunciante() {
        return anunciante;
    }
    public void setAnunciante(String anunciante) {
        if (anunciante == null || anunciante.isBlank()) {
            throw new IllegalArgumentException("Anunciante inválido! O anunciante não deve ser vazio.");
        }
        this.anunciante = anunciante;
    }

    private String titulo;
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título inválido! O título não deve ser vazio.");
        }
        this.titulo = titulo;
    }

    private int duracaoSegundos;

    @Override 
    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração inválida! A duração deve ser um valor positivo.");
        }
        this.duracaoSegundos = duracaoSegundos;
    }

    private int valorPorMilImpressoes;
    public int getValorPorMilImpressoes() {
        return valorPorMilImpressoes;
    }
    public void setValorPorMilImpressoes(int valorPorMilImpressoes) {
        if (valorPorMilImpressoes <= 0) {
            throw new IllegalArgumentException("Valor por mil impressões inválido! O valor deve ser um valor positivo.");
        }
        this.valorPorMilImpressoes = valorPorMilImpressoes;
    }

    private int impressoes = 0;
    public int getImpressoes() {
        return impressoes;
    }

    public Anuncio(String anunciante, String titulo, int duracaoSegundos, int valorPorMilImpressoes) {
        setAnunciante(anunciante);
        setTitulo(titulo);
        setDuracaoSegundos(duracaoSegundos);
        setValorPorMilImpressoes(valorPorMilImpressoes);
    }

    @Override 
    public void reproduzir() {
        impressoes++;
        Writer.escreverNovaLinha("[Anúncio] " + titulo + " oferecimento de " + anunciante);
    }

    public double calcularValorVeiculacao() {
        return (getImpressoes() / 1000.0) * getValorPorMilImpressoes();
    }

    @Override
    public double calcularMensalidade(){
        return this.calcularValorVeiculacao();
    }
}
