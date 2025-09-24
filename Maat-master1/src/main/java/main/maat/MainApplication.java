package main.maat;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.maat.DAO.ConnectionFactory;
import java.sql.Connection;

public class MainApplication extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Gerenciamento de Projetos - Maat");

        // 🔍 Testa a conexão com o banco ANTES de carregar qualquer tela
        testarConexaoBanco();

        // Carrega a tela de login como inicial
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Testa a conexão com o banco de dados e mostra uma mensagem ao usuário.
     */
    private void testarConexaoBanco() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            // Se chegou aqui, a conexão foi bem-sucedida
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Conexão com Banco de Dados");
            alert.setHeaderText(null);
            alert.setContentText("✅ Conexão com o banco de dados estabelecida com sucesso!");
            alert.showAndWait();
        } catch (Exception e) {
            // Erro na conexão
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Conexão");
            alert.setHeaderText("Falha ao conectar ao banco de dados");
            alert.setContentText("Erro: " + e.getMessage());
            alert.showAndWait();
            // Opcional: encerrar a aplicação se não conseguir conectar
            System.exit(1);
        }
    }

    public static void trocarTela(String fxml, int largura, int altura) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/view/" + fxml));
            Scene novaCena = new Scene(fxmlLoader.load(), largura, altura);
            stage.setScene(novaCena);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao trocar de tela: " + fxml);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}