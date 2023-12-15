package View;

import Connection.EstoqueDAO;
import Model.ListaEstoque;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
        // Obtém o modelo da tabela
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            // Obtém a componente da célula
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Obtém o valor da coluna "Quantidade"
            int quantidade = Integer.parseInt(table.getValueAt(row, 3).toString());

            // Define a cor de fundo com base na quantidade
            if (quantidade < 10) {
                c.setBackground(new Color(235, 145, 145));
                c.setForeground(Color.WHITE); // Altera a cor do texto para branco para melhor visibilidade
            } else {
                // Restaura as cores padrão
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }

            return c;
        }
    };

    // Aplica o renderizador à coluna "Quantidade"
    table.getColumnModel().getColumn(0).setCellRenderer(renderer);
    table.getColumnModel().getColumn(1).setCellRenderer(renderer);
    table.getColumnModel().getColumn(2).setCellRenderer(renderer);
    table.getColumnModel().getColumn(3).setCellRenderer(renderer);
    table.getColumnModel().getColumn(4).setCellRenderer(renderer);

    }

    private void cadastrarProduto() {
        // Obtém os dados do formulário
        int codigo = Integer.parseInt(codigoProdutoTextField.getText());
        String tag = tagProdutoTextField.getText();
        String descricao = descricaoProdutoTextField.getText();
        int quantidade = Integer.parseInt(quantidadeProdutoTextField.getText());
        double preco = Double.parseDouble(precoProdutoTextField.getText());

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