package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public VendasPainel() {
        super();
        // Configuração do layout do painel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel cpfPanel = new JPanel();
        cpfPanel.setLayout(new GridLayout(1, 2));
        add(cpfPanel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 5));
        add(inputPanel);

        // Componentes do painel
        // Primeira linha para o CPF
        labelCpf = new JLabel("CPF");
        cpfPanel.add(labelCpf);
        textFieldCpf = new JTextField(11);
        cpfPanel.add(textFieldCpf);

        // Segunda linha para Produto, Quantidade e botão
        labelProduto = new JLabel("Produto:");
        inputPanel.add(labelProduto);
        textFieldProduto = new JTextField(20);
        inputPanel.add(textFieldProduto);

        labelQuantidade = new JLabel("Quantidade:");
        inputPanel.add(labelQuantidade);
        textFieldQuantidade = new JTextField(5);
        inputPanel.add(textFieldQuantidade);

        botaoAdicionar = new JButton("Adicionar à Carrinho");
        inputPanel.add(botaoAdicionar);

        // Área de exibição do carrinho
        areaCarrinho = new JTextArea(55, 30);
        JScrollPane scrollPane = new JScrollPane(areaCarrinho);
        add(scrollPane);

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

    // Exemplo de método para limpar o carrinho (pode ser usado conforme necessário)
    public void limparCarrinho() {
        areaCarrinho.setText("");
    }
}
