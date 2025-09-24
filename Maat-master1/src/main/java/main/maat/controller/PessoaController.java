package main.maat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import main.maat.DAO.PessoaDAO;
import main.maat.model.Pessoa;

public class PessoaController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cargoField;

    @FXML
    private TextField departamentoField;

    private PessoaDAO pessoaDAO = new PessoaDAO();

    @FXML
    private void salvarPessoa() {
        Pessoa p = new Pessoa();
        p.setNome(nomeField.getText());
        p.setCargo(cargoField.getText());
        p.setDepartamento(departamentoField.getText());

        pessoaDAO.salvar(p);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Pessoa");
        alert.setHeaderText(null);
        alert.setContentText("Pessoa cadastrada com sucesso!");
        alert.showAndWait();
    }
}