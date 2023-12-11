package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.EstoqueDAO;
import Model.ListaEstoque;

public class EstoqueControl {
    //
    // atributos
    private List<ListaEstoque> produtos;
    private DefaultTableModel tableModel;
    private JTable table;
    //
    // contrutor
    public EstoqueControl(List<ListaEstoque> produtos, DefaultTableModel tableModel, JTable table) {
        this.produtos = produtos;
        this.tableModel = tableModel;
        this.table = table;
    }

    //
    // métodos do descricao CRUD
    //
    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        produtos = new EstoqueDAO().listarTodos();
        // Obtém os produtos atualizados do banco de dados
        for (ListaEstoque produto : produtos) {
            // Adiciona os dados de cada produto como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] {
                    produto.getCodigo(), produto.getTag(), produto.getDescricao(), produto.getQuantidade(), produto.getPreco() });
        }
    }

    //
    // Método para cadastrar um novo produto no banco de dados
    public void cadastrar(int codigo, String tag, String descricao, int quantidade, double preco) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Deseja cadastrar novo veiculo?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            new EstoqueDAO().cadastrar(codigo, tag, descricao, quantidade, preco);
            //
            // Chama o método de cadastro no banco de dados
            atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
            JOptionPane.showMessageDialog(null, "Cadastro concluido com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado");
        }
    }

    //
    // Método para atualizar os dados de um produto no banco de dados
    public void atualizar(int codigo, String tag, String descricao, int quantidade, double preco) {
        new EstoqueDAO().atualizar(codigo, tag, descricao, quantidade, preco);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    //
    // Método para apagar um produto do banco de dados
    public void apagar(int codigo) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Tem Certeza que deseja Excluir?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            new EstoqueDAO().apagar(codigo);
            // Chama o método de exclusão no banco de dados
            atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
            JOptionPane.showMessageDialog(null, "Cadastro excluido");
        } else {
            JOptionPane.showMessageDialog(null, "Ação cancelada");
        }
    }

}
