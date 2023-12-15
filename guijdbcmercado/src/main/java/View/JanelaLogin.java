package View;

import java.util.Scanner;

import javax.swing.JFrame;

import Connection.FuncionariosDAO;
import Model.ListaFuncionarios;

public class JanelaLogin extends JFrame {
    private FuncionariosDAO funcionariosDAO;

    public JanelaLogin() {
        this.funcionariosDAO = new FuncionariosDAO();
    }

    public boolean autenticar() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o nome de usuário: ");
            String usuario = scanner.nextLine();

            // Buscar usuário na tabela de funcionários
            ListaFuncionarios funcionarioAutenticado = funcionariosDAO.buscarPorUsuario(usuario);

            // Verificar se o funcionário foi encontrado e autenticado
            boolean autenticado = funcionarioAutenticado != null;

            if (autenticado) {
                System.out.println("Autenticação bem-sucedida para o usuário: " + usuario);
            } else {
                System.out.println("Falha na autenticação para o usuário: " + usuario);
            }

            return autenticado;
        }
    }
}
