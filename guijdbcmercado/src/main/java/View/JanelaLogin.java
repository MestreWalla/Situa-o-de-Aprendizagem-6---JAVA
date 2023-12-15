package View;

import java.util.Scanner;

import javax.swing.JFrame;

public class JanelaLogin extends JFrame{
    public boolean autenticar() {
        // Lógica de autenticação - substitua com sua implementação
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome de usuário: ");
        String usuario = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        // Adicione sua lógica de autenticação aqui
        // Exemplo simples: Verifique se o usuário é "admin" e a senha é "admin123"
        boolean autenticado = usuario.equals("admin") && senha.equals("admin123");

        return autenticado;
    }
}
