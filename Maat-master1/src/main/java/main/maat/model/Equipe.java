package main.maat.model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
    private int id;
    private String nome;
    private Pessoa gerente;
    private List<Pessoa> membros;

    public Equipe() {
        this.membros = new ArrayList<>();
    }

    public Equipe(int id, String nome, Pessoa gerente) {
        this.id = id;
        this.nome = nome;
        this.gerente = gerente;
        this.membros = new ArrayList<>();
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Pessoa getGerente() {return gerente;}
    public void setGerente(Pessoa gerente) {this.gerente = gerente;}

    public List<Pessoa> getMembros(List<Pessoa> pessoas) {return membros;}


    public void adicionarMembro(Pessoa pessoa) {
        this.membros.add(pessoa);
    }

    public void removerMembro(Pessoa pessoa){
        this.membros.remove(pessoa);
    }

    public void setMembros(List<Pessoa> pessoas) {
    }

    public Pessoa[] getMembros() {
        return new Pessoa[0];
    }
}

