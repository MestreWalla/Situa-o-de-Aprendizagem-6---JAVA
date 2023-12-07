package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VendasPainel extends JPanel {

    // Atributos(componentes)
    private JLabel labelCpf;
    private JTextField textFieldCpf;
    private JLabel labelProduto;
    private JTextField textFieldProduto;
    private JLabel labelQuantidade;
    private JTextField textFieldQuantidade;
    private JButton botaoAdicionar;
    private JTextArea areaCarrinho;
    private JButton finalizarButton;
    private JButton removerButton;
    private JButton devolucaoButton;
    private JButton logoutButton;
    private JLabel labelOperador;
    private JLabel labelDataHora;

    public VendasPainel() {
        super();
        // Configuração do layout do painel
        setLayout(new BorderLayout());
        Font font = new Font("Arial Black", Font.PLAIN, 16);

        // Panel para o input (à esquerda)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout()); // Usando GridBagLayout para maior controle

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Configuração do labelCpf
        labelCpf = new JLabel("CPF do Cliente");
        labelCpf.setFont(font);
        ImageIcon iconCPF = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Documento.png");
        Image ImageCPF = iconCPF.getImage();
        Image scaledImageCPF = ImageCPF.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        iconCPF = new ImageIcon(scaledImageCPF);
        labelCpf.setIcon(iconCPF);
        labelCpf.setPreferredSize(new Dimension(150, 100));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(labelCpf, gbc);

        // Configuração do textFieldCpf
        textFieldCpf = new JTextField(11);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(textFieldCpf, gbc);

        // Configuração do labelProduto
        labelProduto = new JLabel("Codigo do Produto:");
        labelProduto.setFont(font);
        ImageIcon iconProduto = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Barcode.png");
        Image ImageProduto = iconProduto.getImage();
        Image scaledImageProduto = ImageProduto.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        iconProduto = new ImageIcon(scaledImageProduto);
        labelProduto.setIcon(iconProduto);
        labelProduto.setPreferredSize(new Dimension(150, 100));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(labelProduto, gbc);

        // Configuração do textFieldProduto
        textFieldProduto = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(textFieldProduto, gbc);

        // Configuração do labelQuantidade
        labelQuantidade = new JLabel("Quantidade:");
        labelQuantidade.setFont(font);
        ImageIcon iconQuantidade = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Quantidade.png");
        Image ImageQuantidade = iconQuantidade.getImage();
        Image scaledImageQuantidade = ImageQuantidade.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        iconQuantidade = new ImageIcon(scaledImageQuantidade);
        labelQuantidade.setIcon(iconQuantidade);
        labelQuantidade.setPreferredSize(new Dimension(150, 100));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(labelQuantidade, gbc);

        // Configuração do textFieldQuantidade
        textFieldQuantidade = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(textFieldQuantidade, gbc);

        labelOperador = new JLabel("Nome do(a) Operador(a): " + obterNomeOperador());
        labelOperador.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(labelOperador, gbc);

        labelDataHora = new JLabel("Data e Hora Atual: " + obterDataHoraAtual());
        labelDataHora.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(labelDataHora, gbc);

        // Configuração do botaoAdicionar
        botaoAdicionar = new JButton("Adicionar à Carrinho");
        botaoAdicionar.setFont(font);
        ImageIcon iconAdicionar = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Produto.png");
        Image ImageAdicionar = iconAdicionar.getImage();
        Image scaledImageAdicionar = ImageAdicionar.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        iconAdicionar = new ImageIcon(scaledImageAdicionar);
        botaoAdicionar.setIcon(iconAdicionar);
        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa duas células
        // Adiciona o botaoAdicionar ao inputPanel
        inputPanel.add(botaoAdicionar, gbc);

        // Adiciona o inputPanel ao painel principal
        add(inputPanel, BorderLayout.WEST);

        // Área de exibição do carrinho (no centro)
        areaCarrinho = new JTextArea(55, 30);
        JScrollPane scrollPane = new JScrollPane(areaCarrinho);
        add(scrollPane, BorderLayout.CENTER);

        // Configuração do painel de botões (na parte inferior)
        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new GridLayout(1, 4));
        botoesPanel.setPreferredSize(new Dimension(botoesPanel.getPreferredSize().width, 100)); // Ajuste a altura
                                                                                                // conforme necessário

        // Configuração do botão finalizarButton
        finalizarButton = new JButton("Finalizar Venda (F1)");
        finalizarButton.setFont(font);
        ImageIcon iconfinalizar = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Concluir.png");
        Image imageFinalizar = iconfinalizar.getImage();
        Image scaledImageFinalizar = imageFinalizar.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconfinalizar = new ImageIcon(scaledImageFinalizar);
        finalizarButton.setIcon(iconfinalizar);
        botoesPanel.add(finalizarButton);

        // Configuração do botão removerButton
        removerButton = new JButton("Remover (F2)");
        removerButton.setFont(font);
        ImageIcon iconRemover = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Remover.png");
        Image imageRemover = iconRemover.getImage();
        Image scaledimageRemover = imageRemover.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconRemover = new ImageIcon(scaledimageRemover);
        removerButton.setIcon(iconRemover);
        botoesPanel.add(removerButton);

        // Configuração do botão devolucaoButton
        devolucaoButton = new JButton("Devolução (F3)");
        devolucaoButton.setFont(font);
        ImageIcon iconDevolucao = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Devolucao.png");
        Image imageDevolucao = iconDevolucao.getImage();
        Image scaledimageDevolucao = imageDevolucao.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconDevolucao = new ImageIcon(scaledimageDevolucao);
        devolucaoButton.setIcon(iconDevolucao);
        botoesPanel.add(devolucaoButton);

        // Configuração do botão logoutButton
        logoutButton = new JButton("Logout (F4)");
        logoutButton.setFont(font);
        ImageIcon iconLougout = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Logout.png");
        Image ImageLogout = iconLougout.getImage();
        Image scaledImageLogout = ImageLogout.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconLougout = new ImageIcon(scaledImageLogout);
        logoutButton.setIcon(iconLougout);
        botoesPanel.add(logoutButton);

        // Adiciona o painel de botões ao painel principal na parte inferior
        add(botoesPanel, BorderLayout.SOUTH);

        // Adiciona ação ao botão Adicionar à Carrinho
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém dados do produto e quantidade
                String produto = textFieldProduto.getText();
                String quantidade = textFieldQuantidade.getText();

                // Adiciona ao carrinho
                areaCarrinho.append("Produto: " + produto + ", Quantidade: " + quantidade + "\n");

                // Limpa os campos após adicionar ao carrinho
                textFieldProduto.setText("");
                textFieldQuantidade.setText("");
            }
        });
    }

    private String obterDataHoraAtual() {
        SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date agora = new Date();
        return formatoDataHora.format(agora);
    }

    private String obterNomeOperador() {
        return "Nome do Operador";
    }

    // Exemplo de método para limpar o carrinho (pode ser usado conforme necessário)
    public void limparCarrinho() {
        areaCarrinho.setText("");
    }
}
