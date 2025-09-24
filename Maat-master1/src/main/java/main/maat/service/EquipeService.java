package main.maat.service;

import main.maat.DAO.EquipeDAO;
import main.maat.model.Equipe;

import java.util.List;

public class EquipeService {
    private EquipeDAO equipeDAO = new EquipeDAO();

    public void salvarEquipe(Equipe equipe) {
        if (equipe.getNome() == null || equipe.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da equipe não pode ser vazio.");
        }
        if (equipe.getGerente() == null) {
            throw new IllegalArgumentException("A equipe precisa de um gerente responsável.");
        }
        equipeDAO.salvar(equipe);
    }

    public List<Equipe> listarEquipes() {
        return equipeDAO.listarTodos();
    }

    public Equipe buscarPorId(int id) {
        return equipeDAO.buscarPorId(id);
    }

    public void removerEquipe(int id) {
        equipeDAO.remover(id);
    }
}

