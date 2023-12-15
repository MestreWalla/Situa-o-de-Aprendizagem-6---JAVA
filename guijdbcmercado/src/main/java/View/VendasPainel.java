package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.VendasControl;
import Model.ListaVendas;

public class VendasPainel extends JPanel {

    // Atributos(componentes)
    private JLabel labelCpf;
    private JTextField textFieldCpf;
    private JLabel labelProduto;
    private JTextField textFieldProduto;
    private JLabel labelQuantidade;
    private JTextField textFieldQuantidade;
    private JButton botaoAdicionar;
    // private JTextArea areaCarrinho;
    
    private List<ListaVendas> vendas;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    private JButton finalizarButton;
    private JButton removerButton;
    private JButton devolucaoButton;
    private JButton logoutButton;
    private JLabel labelOperador;
    private JLabel labelDataHora;
    private JLabel labelCliente;

    private static final String COL_CODIGO = "Código";
    private static final String COL_DESCRICAO = "Descrição";
    private static final String COL_QUANTIDADE = "Quantidade";
    private static final String COL_PRECO = "Preço";

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
        labelCpf = new JLabel("CPF do Cliente:");
        labelCpf.setFont(font);
        ImageIcon iconCPF = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Documento.png");
        Image ImageCPF = iconCPF.getImage();
        Image scaledImageCPF = ImageCPF.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        iconCPF = new ImageIcon(scaledImageCPF);
        labelCpf.setIconTextGap(30);
        labelCpf.setIcon(iconCPF);
        labelCpf.setPreferredSize(new Dimension(150, 100));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(labelCpf, gbc);

        // Configuração do textFieldCpf
        textFieldCpf = new JTextField(11);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(textFieldCpf, gbc);

        // Configuração do labelProduto
        labelProduto = new JLabel("Codigo do Produto:");
        labelProduto.setFont(font);
        ImageIcon iconProduto = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Barcode.png");
        Image ImageProduto = iconProduto.getImage();
        Image scaledImageProduto = ImageProduto.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        iconProduto = new ImageIcon(scaledImageProduto);
        labelProduto.setIconTextGap(30);
        labelProduto.setIcon(iconProduto);
        labelProduto.setPreferredSize(new Dimension(150, 100));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(labelProduto, gbc);

        // Configuração do textFieldProduto
        textFieldProduto = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
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
        labelQuantidade.setIconTextGap(30);
        labelQuantidade.setPreferredSize(new Dimension(150, 100));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(labelQuantidade, gbc);

        // Configuração do textFieldQuantidade
        textFieldQuantidade = new JTextField(5);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(textFieldQuantidade, gbc);

        // Descriçoes de Usuario, Data e Hora
        labelCliente = new JLabel("Nome do(a) Cliente(a): " + obterNomeCliente());
        labelCliente.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int paddingTopCliente = 5;
        int paddingLeftCliente = 5;
        int paddingBottomCliente = 5;
        int paddingRightCliente = 5;
        labelCliente.setBorder(BorderFactory.createCompoundBorder(
                labelCliente.getBorder(),
                BorderFactory.createEmptyBorder(paddingTopCliente, paddingLeftCliente, paddingBottomCliente,
                        paddingRightCliente)));
        inputPanel.add(labelCliente, gbc);

        // Descriçoes de Usuario, Data e Hora
        labelOperador = new JLabel("Nome do(a) Operador(a): " + obterNomeOperador());
        labelOperador.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int paddingTop = 5;
        int paddingLeft = 5;
        int paddingBottom = 5;
        int paddingRight = 5;
        labelOperador.setBorder(BorderFactory.createCompoundBorder(
                labelOperador.getBorder(),
                BorderFactory.createEmptyBorder(paddingTop, paddingLeft, paddingBottom, paddingRight)));
        inputPanel.add(labelOperador, gbc);

        labelDataHora = new JLabel("Data e Hora Atual: " + obterDataHoraAtual());
        labelDataHora.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int paddingTopData = 5;
        int paddingLeftData = 5;
        int paddingBottomData = 5;
        int paddingRightData = 5;
        labelOperador.setBorder(BorderFactory.createCompoundBorder(
                labelOperador.getBorder(),
                BorderFactory.createEmptyBorder(paddingTopData, paddingLeftData, paddingBottomData, paddingRightData)));

        inputPanel.add(labelDataHora, gbc);

        // Configuração do botaoAdicionar
        botaoAdicionar = new JButton("Adicionar à Carrinho");
        botaoAdicionar.setBackground(new Color(167, 254, 180)); // Cor verde RGB
        botaoAdicionar.setFont(font);
        ImageIcon iconAdicionar = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Produto.png");
        Image ImageAdicionar = iconAdicionar.getImage();
        Image scaledImageAdicionar = ImageAdicionar.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        iconAdicionar = new ImageIcon(scaledImageAdicionar);
        botaoAdicionar.setIconTextGap(30);
        botaoAdicionar.setIcon(iconAdicionar);
        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 9;
        gbc.gridwidth = 2; // Ocupa duas células

        // Adiciona a margem ao botão
        int topMargin = 10;
        int leftMargin = 10;
        int bottomMargin = 10;
        int rightMargin = 10;
        botaoAdicionar.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));

        // Adiciona o botaoAdicionar ao inputPanel
        inputPanel.add(botaoAdicionar, gbc);

        // Adiciona o inputPanel ao painel principal
        add(inputPanel, BorderLayout.WEST);

        // tabela de carros
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "codigo", "tag", "Descrição", "Quantidade", "Preço" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // Configuração do painel de botões (na parte inferior)
        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new GridLayout(1, 4));
        botoesPanel.setPreferredSize(new Dimension(botoesPanel.getPreferredSize().width, 100));

        // Configuração do botão finalizarButton
        finalizarButton = new JButton("Finalizar Venda (F1)");
        finalizarButton.setFont(font);
        ImageIcon iconfinalizar = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Concluir.png");
        Image imageFinalizar = iconfinalizar.getImage();
        Image scaledImageFinalizar = imageFinalizar.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconfinalizar = new ImageIcon(scaledImageFinalizar);
        finalizarButton.setIconTextGap(30);
        finalizarButton.setIcon(iconfinalizar);
        botoesPanel.add(finalizarButton);

        // Configuração do botão removerButton
        removerButton = new JButton("Remover (F2)");
        removerButton.setFont(font);
        ImageIcon iconRemover = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Remover.png");
        Image imageRemover = iconRemover.getImage();
        Image scaledimageRemover = imageRemover.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconRemover = new ImageIcon(scaledimageRemover);
        removerButton.setIconTextGap(30);
        removerButton.setIcon(iconRemover);
        botoesPanel.add(removerButton);

        // Configuração do botão devolucaoButton
        devolucaoButton = new JButton("Devolução (F3)");
        devolucaoButton.setFont(font);
        ImageIcon iconDevolucao = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Devolucao.png");
        Image imageDevolucao = iconDevolucao.getImage();
        Image scaledimageDevolucao = imageDevolucao.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconDevolucao = new ImageIcon(scaledimageDevolucao);
        devolucaoButton.setIconTextGap(30);
        devolucaoButton.setIcon(iconDevolucao);
        botoesPanel.add(devolucaoButton);

        // Configuração do botão logoutButton
        logoutButton = new JButton("Logout (F4)");
        logoutButton.setFont(font);
        ImageIcon iconLougout = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Logout.png");
        Image ImageLogout = iconLougout.getImage();
        Image scaledImageLogout = ImageLogout.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconLougout = new ImageIcon(scaledImageLogout);
        logoutButton.setIconTextGap(30);
        logoutButton.setIcon(iconLougout);
        botoesPanel.add(logoutButton);

        // Adiciona o painel de botões ao painel principal na parte inferior
        add(botoesPanel, BorderLayout.SOUTH);

        // Adiciona ação ao botão Adicionar à Carrinho
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoProduto = textFieldProduto.getText();
        
                // Verifica se o produto com o código informado está no estoque
                if (produtoExisteNoEstoque(codigoProduto)) {
                    // Lógica para adicionar o produto à tabela de vendas
                    // (Você precisará implementar essa lógica)
                    String tag = ""; // Coloque a lógica para obter a tag do produto
                    String descricao = obterDescricaoProduto(codigoProduto);
                    String quantidade = textFieldQuantidade.getText();
                    String preco = obterPrecoProduto(codigoProduto);
        
                    adicionarProdutoATabela(codigoProduto, tag, descricao, quantidade, preco);
        
                    // Limpa os campos após adicionar à tabela
                    textFieldProduto.setText("");
                    textFieldQuantidade.setText("");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Produto com o código " + codigoProduto + " não encontrado no estoque.");
                }
            }

        
            private void adicionarProdutoATabela(String codigoProduto, String tag, String descricao, String quantidade,
                    String preco) {
                // Adiciona os dados à tabela
                tableModel.addRow(new Object[] { codigoProduto, tag, descricao, quantidade, preco });

                // Atualiza a tabela
                table.setModel(tableModel);
            }

            private String obterDescricaoProduto(String codigo) {
                return null;
            }

            private boolean produtoExiste(int int1) {
                return false;
            }
        });
    }

    // Método para verificar se o produto existe no estoque
    private boolean produtoExisteNoEstoque(String codigoProduto) {
        // Implemente a lógica adequada aqui (por exemplo, chamando métodos do seu DAO)
        // Retorne true se existir, false caso contrário
        return VendasControl.produtoExiste(codigoProduto);
    }

    private String obterPrecoProduto(String codigoProduto) {
        // Substitua este exemplo pela lógica real para obter o preço do produto no seu
        // sistema
        // Exemplo fictício:
        if ("1".equals(codigoProduto)) {
            return "10.00"; // Preço do Produto A
        } else if ("2".equals(codigoProduto)) {
            return "20.00"; // Preço do Produto B
        } else {
            return "Preço não encontrado";
        }
    }

    private void adicionarProdutoATabela(String codigoProduto) {
        // Adiciona os dados à tabela (Você precisará implementar essa lógica)
        // Exemplo:
        String descricao = obterDescricaoProduto(codigoProduto);
        String quantidade = textFieldQuantidade.getText();
        String preco = obterPrecoProduto(codigoProduto);

        tableModel.addRow(new Object[] { codigoProduto, descricao, quantidade, preco });
    }

    private String obterDescricaoProduto(String codigoProduto) {
        // Substitua este exemplo pela lógica real para obter a descrição do produto no
        // seu sistema
        // Exemplo fictício:
        if ("1".equals(codigoProduto)) {
            return "Produto A";
        } else if ("2".equals(codigoProduto)) {
            return "Produto B";
        } else {
            return "Descrição não encontrada";
        }
    }

    private String obterNomeCliente() {
        // Substitua este exemplo pela lógica real para obter o nome do cliente no seu
        // sistema
        // Exemplo fictício:
        return "Nome do Cliente";
    }

    private String obterNomeOperador() {
        // Substitua este exemplo pela lógica real para obter o nome do operador no seu
        // sistema
        // Exemplo fictício:
        return "Nome do Operador";
    }

    private String obterDataHoraAtual() {
        // Utiliza a classe SimpleDateFormat para obter a data e hora atual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataHoraAtual = new Date();
        return sdf.format(dataHoraAtual);
    }

}
