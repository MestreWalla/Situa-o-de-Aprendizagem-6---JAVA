package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.EstoqueDAO;
import Model.ListaEstoque;

public class EstoqueControl {
    private List<ListaEstoque> produtos;
    private DefaultTableModel tableModel;
    private JTable table;
    private EstoqueDAO estoqueDAO;

    public EstoqueControl(List<ListaEstoque> produtos, DefaultTableModel tableModel, JTable table, EstoqueDAO estoqueDAO) {
        this.produtos = produtos;
        this.tableModel = tableModel;
        this.table = table;
        this.estoqueDAO = estoqueDAO;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        produtos = estoqueDAO.listarTodos();
        for (ListaEstoque produto : produtos) {
            tableModel.addRow(new Object[] {
                    produto.getCodigo(), produto.getTag(), produto.getDescricao(), produto.getQuantidade(),
                    produto.getPreco() });
        }
    }

    public void cadastrar(int codigo, String tag, String descricao, int quantidade, double preco) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Deseja cadastrar novo produto?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);
        if (acao == 1) {
            estoqueDAO.cadastrar(codigo, tag, descricao, quantidade, preco);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Cadastro concluído com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado");
        }
    }

    public void atualizarProduto(int codigo, String tag, String descricao, int quantidade, double preco) {
        estoqueDAO.atualizar(codigo, tag, descricao, quantidade, preco);
        atualizarTabela();
    }

    public void excluirProduto(int codigo) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Tem Certeza de que deseja excluir o esse produto?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            estoqueDAO.apagar(codigo);
            atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
            JOptionPane.showMessageDialog(null, "Cadastro excluído");
        } else {
            JOptionPane.showMessageDialog(null, "Ação cancelada");
        }
    }
}
