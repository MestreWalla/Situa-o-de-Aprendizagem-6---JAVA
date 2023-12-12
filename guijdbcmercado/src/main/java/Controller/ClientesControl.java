package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.ClientesDAO;
import Model.ListaClientes;

public class ClientesControl {
    //
    // atributos
    private List<ListaClientes> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    //
    // contrutor
    public ClientesControl(List<ListaClientes> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }

    //
    // métodos do modelo CRUD
    //
    // Método para atualizar a tabela de exibição com dados do banco de dados
    // private void atualizarTabela() {
    //     tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
    //     clientes = new ClientesDAO().listarTodos();
    //     // Obtém os clientes atualizados do banco de dados
    //     for (ListaClientes cliente : clientes) {
    //         // Adiciona os dados de cada clientes como uma nova linha na tabela Swing
    //         tableModel.addRow(new Object[] {
    //                 cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone(), 
    //                 cliente.getEndereco() });
    //     }
    // }

    //
    // Método para cadastrar um novo clientes no banco de dados
    public void cadastrar(String nome, String cpf, String email, String telefone, String endereco) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Deseja cadastrar novo cliente?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            new ClientesDAO().cadastrar(nome, cpf, email, telefone, endereco);
            //
            // Chama o método de cadastro no banco de dados
            JOptionPane.showMessageDialog(null, "Cadastro concluido com sucesso");
            //atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado");
        }
    }

    //
    // Método para atualizar os dados de um clientes no banco de dados
    public void atualizar(String nome, String cpf, String email, String telefone, String endereco) {
        new ClientesDAO().atualizar(nome, cpf, email, telefone, endereco);
        // Chama o método de atualização no banco de dados
        //atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    //
    // Método para apagar um clientes do banco de dados
    public void apagar(String cpf) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Tem Certeza de que deseja excluir o cadastro desse cliente?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            new ClientesDAO().apagar(cpf);
            // Chama o método de exclusão no banco de dados
            //atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
            JOptionPane.showMessageDialog(null, "Cadastro excluido");
        } else {
            JOptionPane.showMessageDialog(null, "Ação cancelada");
        }
    }

}
