package main.maat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import main.maat.DAO.ProjetoDAO;
import main.maat.model.Projeto;

public class ProjetoController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField descricaoField;

    @FXML
    private DatePicker dataInicioField;

    @FXML
    private DatePicker dataPrevistaFimField;

    @FXML
    private DatePicker dataFimField;

    private ProjetoDAO projetoDAO = new ProjetoDAO();

    @FXML
    private void salvarProjeto() {
        Projeto p = new Projeto();
        p.setNome(nomeField.getText());
        p.setDescricao(descricaoField.getText());
        p.setDataInicio(dataInicioField.getValue());
        p.setDataFim(dataPrevistaFimField.getValue());
        p.setDataFim(dataFimField.getValue());

        projetoDAO.salvar(p);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Projeto");
        alert.setHeaderText(null);
        alert.setContentText("Projeto cadastrado com sucesso!");
        alert.showAndWait();
    }
}
