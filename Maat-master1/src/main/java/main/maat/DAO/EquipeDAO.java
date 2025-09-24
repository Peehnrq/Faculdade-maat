package main.maat.DAO;

import main.maat.model.Equipe;
import main.maat.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {

    // Salvar uma equipe e seus membros
    public void salvar(Equipe equipe) {
        String sql = "INSERT INTO equipe (nome, gerente_id) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipe.getNome());
            stmt.setInt(2, equipe.getGerente().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int equipeId = rs.getInt(1);

                // Adicionar membros na tabela equipe_pessoa
                for (Pessoa p : equipe.getMembros()) {
                    String sqlMembro = "INSERT INTO equipe_pessoa (id_equipe, id_pessoa) VALUES (?, ?)";
                    try (PreparedStatement stmtMembro = conn.prepareStatement(sqlMembro)) {
                        stmtMembro.setInt(1, equipeId);
                        stmtMembro.setInt(2, p.getId());
                        stmtMembro.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todas as equipes
    public List<Equipe> listarTodos() {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipe";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipe e = new Equipe();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));

                // Aqui você pode carregar o gerente e os membros em consultas separadas (JOIN seria melhor)
                // Exemplo simples:
                Pessoa gerente = buscarGerentePorId(rs.getInt("gerente_id"), conn);
                e.setGerente(gerente);

                e.setMembros(buscarMembrosPorEquipe(e.getId(), conn));

                equipes.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipes;
    }

    // Buscar equipe pelo ID
    public Equipe buscarPorId(int id) {
        String sql = "SELECT * FROM equipe WHERE id = ?";
        Equipe equipe = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("nome"));

                Pessoa gerente = buscarGerentePorId(rs.getInt("gerente_id"), conn);
                equipe.setGerente(gerente);

                equipe.setMembros(buscarMembrosPorEquipe(equipe.getId(), conn));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipe;
    }

    // Remover equipe por ID
    public void remover(int id) {
        String sqlEquipePessoa = "DELETE FROM equipe_pessoa WHERE id_equipe = ?";
        String sqlEquipe = "DELETE FROM equipe WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            // Remove membros associados
            try (PreparedStatement stmtMembros = conn.prepareStatement(sqlEquipePessoa)) {
                stmtMembros.setInt(1, id);
                stmtMembros.executeUpdate();
            }

            // Remove a equipe
            try (PreparedStatement stmtEquipe = conn.prepareStatement(sqlEquipe)) {
                stmtEquipe.setInt(1, id);
                stmtEquipe.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Métodos auxiliares
    private Pessoa buscarGerentePorId(int gerenteId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gerenteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pessoa gerente = new Pessoa();
                gerente.setId(rs.getInt("id"));
                gerente.setNome(rs.getString("nome"));
                gerente.setCargo(rs.getString("cargo"));
                gerente.setDepartamento(rs.getString("departamento"));
                return gerente;
            }
        }
        return null;
    }

    private List<Pessoa> buscarMembrosPorEquipe(int equipeId, Connection conn) throws SQLException {
        List<Pessoa> membros = new ArrayList<>();
        String sql = "SELECT p.* FROM pessoa p INNER JOIN equipe_pessoa ep ON p.id = ep.id_pessoa WHERE ep.id_equipe = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, equipeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCargo(rs.getString("cargo"));
                p.setDepartamento(rs.getString("departamento"));
                membros.add(p);
            }
        }
        return membros;
    }
}
