package View;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class JanelaPrincipal extends JFrame {
    // criação do tabbedPane para incluir as tabs
    private JTabbedPane jTPane;

    public JanelaPrincipal() {

        this.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2)),
                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2)));
        AplicaNimbusLookAndFeel.pegaNimbus();

        jTPane = new JTabbedPane();
        add(jTPane);

        // Criandos as tabs
        // Tab01: Venda
        ImageIcon iconVendas = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Carrinho.png");
        Image image = iconVendas.getImage();
        Image scaledImage = image.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconVendas = new ImageIcon(scaledImage);
        VendasPainel tab1 = new VendasPainel();
        jTPane.addTab("", iconVendas, tab1, "Vendas");

        // Tab02: Clientes
        ImageIcon iconClientes = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Cliente.png");
        Image imageClientes = iconClientes.getImage();
        Image scaledImageClientes = imageClientes.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconClientes = new ImageIcon(scaledImageClientes);
        CadastroClientesPainel tab2 = new CadastroClientesPainel();
        jTPane.addTab("", iconClientes, tab2, "Clientes");

        // Tab03: Funcionarios
        ImageIcon iconFuncionarios = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Funcionario.png");
        Image imageFuncionarios = iconFuncionarios.getImage();
        Image scaledImageFuncionarios = imageFuncionarios.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconFuncionarios = new ImageIcon(scaledImageFuncionarios);
        CadastroFuncionariosPainel tab3 = new CadastroFuncionariosPainel();
        jTPane.addTab("", iconFuncionarios, tab3, "Funcionarios");

         // Tab04: Produtos
        ImageIcon iconProdutos = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Produtos.png");
        Image imageProdutos = iconProdutos.getImage();
        Image scaledImageProdutos = imageProdutos.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconProdutos = new ImageIcon(scaledImageProdutos);
        CadastroProdutosPainel tab4 = new CadastroProdutosPainel();
        jTPane.addTab("", iconProdutos, tab4, "Exibir");

        setBounds(0, 0, 1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(
                ((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2)),
                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2)));

        // Define o estado da janela como maximizado (tela cheia)
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Oculta a barra da janela principal
        setUndecorated(true);

        // Define o estado da janela como fullscreen
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
    }

    // Tornar a janela visível
    public void run() {
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JanelaPrincipal janela = new JanelaPrincipal();
        janela.run();
    }

    // - Singleton para aplicação do Nimbus:
    public static class AplicaNimbusLookAndFeel {

        private AplicaNimbusLookAndFeel() {
        }

        public static void pegaNimbus() {
            try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (UnsupportedLookAndFeelException e) {

                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();

            } catch (ClassNotFoundException e) {

                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();

            } catch (InstantiationException e) {

                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();

            } catch (IllegalAccessException e) {

                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
