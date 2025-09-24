package main.maat.DAO;

import main.maat.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método CREATE: Salvar um novo usuário no banco de dados
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nomeCompleto, cpf, email, cargo, login, senha, perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCargo());
            stmt.setString(5, usuario.getLogin());
            stmt.setString(6, usuario.getSenha());
            stmt.setString(7, usuario.getPerfil());
            stmt.executeUpdate();

            // Pega o ID gerado pelo banco e atualiza o objeto Usuario
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário.", e);
        }
    }

    // Método READ: Buscar um usuário pelo seu ID
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarUsuarioDeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID.", e);
        }
        return null;
    }

    // Método READ: Buscar usuário pelo email
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarUsuarioDeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por email.", e);
        }
        return null;
    }

    // Método READ: Listar todos os usuários
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(criarUsuarioDeResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários.", e);
        }
        return usuarios;
    }

    // Método UPDATE: Atualizar um usuário
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nomeCompleto = ?, cpf = ?, email = ?, cargo = ?, login = ?, senha = ?, perfil = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCargo());
            stmt.setString(5, usuario.getLogin());
            stmt.setString(6, usuario.getSenha());
            stmt.setString(7, usuario.getPerfil());
            stmt.setInt(8, usuario.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário.", e);
        }
    }

    // Método DELETE: Remover um usuário
    public void remover(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover usuário.", e);
        }
    }

    // Método auxiliar para criar um objeto Usuario a partir de um ResultSet
    private Usuario criarUsuarioDeResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNomeCompleto(rs.getString("nomeCompleto"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setEmail(rs.getString("email"));
        usuario.setCargo(rs.getString("cargo"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setPerfil(rs.getString("perfil"));
        return usuario;
    }
}