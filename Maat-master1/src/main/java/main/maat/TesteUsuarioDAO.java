package main.maat;

import main.maat.DAO.UsuarioDAO;
import main.maat.model.Usuario;

import java.util.List;

public class TesteUsuarioDAO {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // 1. Testando a criação de um novo usuário (C - Create)
        System.out.println("--- Testando a criação de um usuário ---");
        Usuario novoUsuario = new Usuario("Maria Silva", "123.456.789-00", "maria.silva@teste.com", "Analista", "maria.silva", "senha123", "Colaborador");
        usuarioDAO.salvar(novoUsuario);
        System.out.println("Usuário criado com sucesso. ID: " + novoUsuario.getId());

        // 2. Testando a busca de um usuário (R - Read)
        System.out.println("\n--- Testando a busca de um usuário por ID ---");
        Usuario usuarioBuscado = usuarioDAO.buscarPorId(novoUsuario.getId());
        if (usuarioBuscado != null) {
            System.out.println("Usuário encontrado: " + usuarioBuscado.getNomeCompleto() + " (" + usuarioBuscado.getEmail() + ")");
        } else {
            System.out.println("Usuário não encontrado.");
        }

        // 3. Testando a atualização de um usuário (U - Update)
        System.out.println("\n--- Testando a atualização de um usuário ---");
        if (usuarioBuscado != null) {
            usuarioBuscado.setCargo("Gerente de Projetos");
            usuarioBuscado.setPerfil("Gerente");
            usuarioDAO.atualizar(usuarioBuscado);
            System.out.println("Usuário atualizado. Novo cargo: " + usuarioBuscado.getCargo());
        }

        // 4. Testando a listagem de todos os usuários (R - Read)
        System.out.println("\n--- Testando a listagem de todos os usuários ---");
        List<Usuario> todosUsuarios = usuarioDAO.listarTodos();
        for (Usuario u : todosUsuarios) {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNomeCompleto() + ", Cargo: " + u.getCargo());
        }

        // 5. Testando a remoção de um usuário (D - Delete)
        System.out.println("\n--- Testando a remoção de um usuário ---");
        if (novoUsuario.getId() > 0) {
            usuarioDAO.remover(novoUsuario.getId());
            System.out.println("Usuário com ID " + novoUsuario.getId() + " removido.");
        }
    }
}