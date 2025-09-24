package main.maat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import main.maat.DAO.EquipeDAO;
import main.maat.DAO.PessoaDAO;
import main.maat.model.Equipe;
import main.maat.model.Pessoa;

import java.util.List;

public class EquipeController {

    @FXML
    private TextField nomeEquipeField;

    @FXML
    private ComboBox<Pessoa> gerenteComboBox;

    @FXML
    private ComboBox<Pessoa> membrosComboBox;

    private EquipeDAO equipeDAO = new EquipeDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();

    private Equipe equipeAtual = new Equipe();

    @FXML
    public void initialize() {
        // Carregar pessoas do banco para os ComboBox
        List<Pessoa> pessoas = pessoaDAO.listarTodos();
        gerenteComboBox.getItems().addAll(pessoas);
        membrosComboBox.getItems().addAll(pessoas);
    }

    @FXML
    private void adicionarMembro() {
        Pessoa selecionado = membrosComboBox.getValue();
        if (selecionado != null) {
            equipeAtual.adicionarMembro(selecionado);
        }
    }

    @FXML
    private void salvarEquipe() {
        equipeAtual.setNome(nomeEquipeField.getText());
        equipeAtual.setGerente(gerenteComboBox.getValue());

        equipeDAO.salvar(equipeAtual);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Equipe");
        alert.setHeaderText(null);
        alert.setContentText("Equipe cadastrada com sucesso!");
        alert.showAndWait();

        equipeAtual = new Equipe(); // resetar após salvar
    }
}

