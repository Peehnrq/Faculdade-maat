package main.maat.service;

import main.maat.DAO.PessoaDAO;
import main.maat.model.Pessoa;

import java.util.List;

public class PessoaService {
    private PessoaDAO pessoaDAO = new PessoaDAO();

    public void salvarPessoa(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da pessoa não pode ser vazio.");
        }
        pessoaDAO.salvar(pessoa);
    }

//    public List<Pessoa> listarPessoas() {
//        return pessoaDAO.listar();
//    }

    public Pessoa buscarPorId(int id) {
        return pessoaDAO.buscarPorId(id);
    }

    public void removerPessoa(int id) {
        pessoaDAO.remover(id);
    }
}