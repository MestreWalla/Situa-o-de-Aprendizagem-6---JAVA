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

    public EstoqueControl(List<ListaEstoque> produtos, DefaultTableModel tableModel, JTable table) {
        this.produtos = produtos;
        this.tableModel = tableModel;
        this.table = table;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        produtos = new EstoqueDAO().listarTodos();
        for (ListaEstoque produto : produtos) {
            tableModel.addRow(new Object[] {
                    produto.getCodigo(), produto.getTag(), produto.getDescricao(), produto.getQuantidade(), produto.getPreco() });
        }
    }

    private boolean confirmacaoDialog(String mensagem) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                mensagem,
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);
        return acao == 1;
    }

    public void cadastrar(int codigo, String tag, String descricao, int quantidade, double preco) {
        if (confirmacaoDialog("Deseja cadastrar novo veiculo?")) {
            new EstoqueDAO().cadastrar(codigo, tag, descricao, quantidade, preco);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Cadastro concluido com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado");
        }
    }

    public void atualizar(int codigo, String tag, String descricao, int quantidade, double preco) {
        new EstoqueDAO().atualizar(codigo, tag, descricao, quantidade, preco);
        atualizarTabela();
    }

    public void excluir(int codigo) {
        if (confirmacaoDialog("Tem Certeza que deseja Excluir?")) {
            new EstoqueDAO().apagar(codigo);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Cadastro excluido");
        } else {
            JOptionPane.showMessageDialog(null, "Ação cancelada");
        }
    }
}
