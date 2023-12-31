package app;

import View.JanelaLogin;
import View.JanelaPrincipal;

public class Main {
    public static void main(String[] args) {
        // Crie uma instância da janela de login
        JanelaLogin janelaLogin = new JanelaLogin();

        // Exiba a janela de login e aguarde a autenticação
        boolean autenticado = janelaLogin.autenticar();

        // Se a autenticação for bem-sucedida, abra a JanelaPrincipal
        if (autenticado) {
            new JanelaPrincipal().run();
        } else {
            // Caso contrário, encerre o aplicativo ou faça algo apropriado
            System.out.println("Autenticação falhou. O aplicativo será encerrado.");
        }
    }
}
