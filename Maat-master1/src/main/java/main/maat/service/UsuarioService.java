package main.maat.service;

import main.maat.DAO.UsuarioDAO;
import main.maat.model.Usuario;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioDAO.buscarPorEmail(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha incorreta.");
        }
        return usuario;
    }
}

