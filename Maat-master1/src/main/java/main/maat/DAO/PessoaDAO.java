package main.maat.DAO;

import main.maat.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
   private Connection conn;

        // Salvar pessoa
    public void salvar(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, cargo, departamento) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCargo());
            stmt.setString(3, pessoa.getDepartamento());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pessoa.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todas as pessoas
    public List<Pessoa> listarTodos() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCargo(rs.getString("cargo"));
                p.setDepartamento(rs.getString("departamento"));
                pessoas.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    // Buscar por ID
    public Pessoa buscarPorId(int id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        Pessoa pessoa = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCargo(rs.getString("cargo"));
                pessoa.setDepartamento(rs.getString("departamento"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    // Remover pessoa
    public void remover(int id) {
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
