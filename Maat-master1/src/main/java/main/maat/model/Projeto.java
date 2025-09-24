package main.maat.model;

import java.time.LocalDate;

public class Projeto {
    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataPrevistaFim;
    private LocalDate dataFim;

    public Projeto() {}

    public Projeto(int id, String nome, String descricao, LocalDate dataInicio, LocalDate dataPrevistaFim, LocalDate dataFim) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataPrevistaFim = dataPrevistaFim;
        this.dataFim = dataFim;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public LocalDate getDataInicio() {return dataInicio;}
    public void setDataInicio(LocalDate dataInicio) {this.dataInicio = dataInicio;}

    public LocalDate getDataPrevistaFim() {return dataPrevistaFim;}
    public void setDataPrevistaFim(LocalDate dataPrevistaFim) {this.dataPrevistaFim = dataPrevistaFim;}

    public LocalDate getDataFim() {return dataFim;}
    public void setDataFim(LocalDate dataFim) {this.dataFim = dataFim;}
}
