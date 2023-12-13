package View;

import Connection.EstoqueDAO;
import Model.ListaEstoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroProdutosPainel extends JPanel {

    private JLabel codigoProdutoLabel;
    private JTextField codigoProdutoTextField;
    private JLabel tagProdutoLabel;
    private JTextField tagProdutoTextField;
    private JLabel descricaoProdutoLabel;
    private JTextField descricaoProdutoTextField;
    private JLabel quantidadeProdutoLabel;
    private JTextField quantidadeProdutoTextField;
    private JLabel precoProdutoLabel;
    private JTextField precoProdutoTextField;
    private JButton cadastrarProdutoButton;

    // Lista para armazenar produtos (opcional, dependendo da lógica de negócios)
    private List<ListaEstoque> listaEstoque;

    public CadastroProdutosPainel() {
        super();

        setLayout(new BorderLayout());

        // Inicializa a lista de produtos (opcional, dependendo da lógica de negócios)
        listaEstoque = new EstoqueDAO().listarTodos();

        // Componentes iniciais
        codigoProdutoLabel = new JLabel("codigo:");
        codigoProdutoTextField = new JTextField(20);
        tagProdutoLabel = new JLabel("tag:");
        tagProdutoTextField = new JTextField(20);
        descricaoProdutoLabel = new JLabel("Descrição:");
        descricaoProdutoTextField = new JTextField(20);
        quantidadeProdutoLabel = new JLabel("Quantidade:");
        quantidadeProdutoTextField = new JTextField(10);
        precoProdutoLabel = new JLabel("Preço:");
        precoProdutoTextField = new JTextField(10);
        cadastrarProdutoButton = new JButton("Cadastrar");

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(codigoProdutoLabel);
        formPanel.add(codigoProdutoTextField);
        formPanel.add(tagProdutoLabel);
        formPanel.add(tagProdutoTextField);
        formPanel.add(descricaoProdutoLabel);
        formPanel.add(descricaoProdutoTextField);
        formPanel.add(quantidadeProdutoLabel);
        formPanel.add(quantidadeProdutoTextField);
        formPanel.add(precoProdutoLabel);
        formPanel.add(precoProdutoTextField);
        formPanel.add(new JLabel());
        formPanel.add(cadastrarProdutoButton);

        add(formPanel, BorderLayout.CENTER);

        // Adiciona listener ao botão de cadastrar
        cadastrarProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto();
            }
        });
    }

    private void cadastrarProduto() {
        // Obtém os dados do formulário
        String codigo = codigoProdutoTextField.getText();
        String tag = tagProdutoTextField.getText();
        String descricao = descricaoProdutoTextField.getText();
        int quantidade = Integer.parseInt(quantidadeProdutoTextField.getText());
        double preco = Double.parseDouble(precoProdutoTextField.getText());

        // Cria uma instância do DAO e cadastra o produto no banco de dados
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        estoqueDAO.cadastrar(codigo, tag, descricao, quantidade, preco);

        // Limpa os campos do formulário
        codigoProdutoTextField.setText("");
        tagProdutoTextField.setText("");
        descricaoProdutoTextField.setText("");
        quantidadeProdutoTextField.setText("");
        precoProdutoTextField.setText("");

        // Atualiza a lista de produtos (opcional, dependendo da lógica de negócios)
        listaEstoque = estoqueDAO.listarTodos();
    }

    // Método opcional para obter a lista de produtos (dependendo da lógica de negócios)
    public List<ListaEstoque> getListaEstoque() {
        return listaEstoque;
    }
}