package View;

import Connection.EstoqueDAO;
import Model.ListaEstoque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private int valorTotal;

    // Tabela Produtos
    private List<ListaEstoque> listaEstoque;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    public CadastroProdutosPainel() {
        super();

        setLayout(new BorderLayout());

        // Configuração da tabela
        String[] columnNames = { "Código", "Tag", "Descrição", "Quantidade", "Preço" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        atualizarTabela();

        // Componentes iniciais
        codigoProdutoLabel = new JLabel("Código:");
        codigoProdutoTextField = new JTextField(20);
        tagProdutoLabel = new JLabel("Tag:");
        tagProdutoTextField = new JTextField(20);
        descricaoProdutoLabel = new JLabel("Descrição:");
        descricaoProdutoTextField = new JTextField(20);
        quantidadeProdutoLabel = new JLabel("Quantidade:");
        quantidadeProdutoTextField = new JTextField(10);
        precoProdutoLabel = new JLabel("Preço:");
        precoProdutoTextField = new JTextField(10);
        cadastrarProdutoButton = new JButton("Cadastrar");

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);

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
        int codigo = Integer.parseInt(codigoProdutoTextField.getText());
        String tag = tagProdutoTextField.getText();
        String descricao = descricaoProdutoTextField.getText();
        int quantidade = Integer.parseInt(quantidadeProdutoTextField.getText());
        double preco = Double.parseDouble(precoProdutoTextField.getText());
        valorTotal += preco;

        // Cria uma instância do DAO e cadastra o produto no banco de dados
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        estoqueDAO.cadastrar(codigo, tag, descricao, quantidade, preco);

        // Adiciona uma nova linha à tabela com os dados do produto cadastrado
        Object[] rowData = { codigo, tag, descricao, quantidade, preco };
        tableModel.addRow(rowData);

        // Limpa os campos do formulário
        codigoProdutoTextField.setText("");
        tagProdutoTextField.setText("");
        descricaoProdutoTextField.setText("");
        quantidadeProdutoTextField.setText("");
        precoProdutoTextField.setText("");

        System.out.println("Produto cadastrado");

        // Atualiza a lista de produtos (opcional, dependendo da lógica de negócios)
        listaEstoque = estoqueDAO.listarTodos();
        atualizarTabela();
    }

    // Método opcional para obter a lista de produtos (dependendo da lógica de
    // negócios)
    public List<ListaEstoque> getListaEstoque() {
        return listaEstoque;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        listaEstoque = new EstoqueDAO().listarTodos();
        for (ListaEstoque produto : listaEstoque) {
            tableModel.addRow(new Object[] {
                    produto.getCodigo(), produto.getTag(), produto.getDescricao(), produto.getQuantidade(),
                    produto.getPreco() });
        }
    }
}
