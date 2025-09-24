package main.maat.service;

import main.maat.DAO.ProjetoDAO;
import main.maat.model.Projeto;

import java.util.List;

public class ProjetoService {
    private ProjetoDAO projetoDAO = new ProjetoDAO();

    public void salvarProjeto(Projeto projeto) {
        if (projeto.getNome() == null || projeto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do projeto não pode ser vazio.");
        }
        projetoDAO.salvar(projeto);
    }

    public List<Projeto> listarProjetos() {
        return projetoDAO.listarTodos();
    }

    public Projeto buscarPorId(int id) {
        return projetoDAO.buscarPorId(id);
    }

    public void removerProjeto(int id) {
        projetoDAO.remover(id);
    }
}
