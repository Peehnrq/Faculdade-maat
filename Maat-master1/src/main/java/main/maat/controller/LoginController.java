package main.maat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import main.maat.DAO.UsuarioDAO;
import main.maat.model.Usuario;
import main.maat.controller.EquipeController;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void fazerLogin() {
        String email = emailField.getText();
        String senha = senhaField.getText();

        boolean autenticado = false;
        for (Usuario u : usuarioDAO.listarTodos()) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                autenticado = true;
                break;

            }
        }

        Alert alert = new Alert(autenticado ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(autenticado ? "Login realizado com sucesso!" : "Usuário ou senha inválidos.");
        alert.showAndWait();
    }
}

