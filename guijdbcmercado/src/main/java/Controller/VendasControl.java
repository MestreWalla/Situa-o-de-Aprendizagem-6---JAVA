package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.VendasDAO;
import Model.ListaVendas;

public class VendasControl {
    //
    // atributos
    private List<ListaVendas> vendas;
    private DefaultTableModel tableModel;
    private JTable table;

    //
    // contrutor
    public VendasControl(List<ListaVendas> vendas, DefaultTableModel tableModel, JTable table) {
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    //
    // métodos do modelo CRUD
    //
    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        vendas = new VendasDAO().listarTodos();
        // Obtém os vendas atualizados do banco de dados
        for (ListaVendas venda : vendas) {
            // Adiciona os dados de cada vendas como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] {
                    venda.getDataHora(), venda.getValor(), venda.getVip() });
        }
    }

    //
    // Método para cadastrar uma nova venda no banco de dados
    public void cadastrar(String dataHora, double valor, boolean vip) {
        
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Deseja finalizar a compra?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            new VendasDAO().cadastrar(dataHora, valor, vip);
            //
            // Chama o método de cadastro no banco de dados
            JOptionPane.showMessageDialog(null, "Venda concluida com sucesso");
            //atualizarTabela(); // Atualiza a tabela de exibição após a Venda
        } else {
            JOptionPane.showMessageDialog(null, "Venda cancelada");
        }
    }

    //
    // Método para atualizar os dados de um vendas no banco de dados
    public void atualizar(String dataHora, double valor, boolean vip) {
        new VendasDAO().atualizar(dataHora, valor, vip);
        // Chama o método de atualização no banco de dados
            atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    private String obterDescricaoProduto(String codigo) {
        // Implemente a lógica para obter a descrição do produto com base no código
        // Exemplo: Consultar o banco de dados ou usar um mapeamento fixo
        return "Descrição do Produto";
    }
    
    private String obterPrecoProduto(String codigo) {
        // Implemente a lógica para obter o preço do produto com base no código
        // Exemplo: Consultar o banco de dados ou usar um mapeamento fixo
        return "Preço do Produto";
    }
    
    private void adicionarProdutoATabela(String codigo, String tag, String descricao, String quantidade, String preco) {
        // Adiciona os dados à tabela
        tableModel.addRow(new Object[]{codigo, tag, descricao, quantidade, preco});
    }

    
    //
    // Método para apagar um vendas do banco de dados
    public void apagar(String dataHora) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Tem Certeza de que deseja excluir o registro dessa venda?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            new VendasDAO().apagar(dataHora);
            // Chama o método de exclusão no banco de dados
            //atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
            JOptionPane.showMessageDialog(null, "Registro de venda excluido");
        } else {
            JOptionPane.showMessageDialog(null, "Ação cancelada");
        }
    }

    

}
