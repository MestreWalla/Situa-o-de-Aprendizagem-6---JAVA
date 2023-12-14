package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.FuncionariosDAO;
import Model.ListaFuncionarios;

public class FuncionariosControl {
    //
    // atributos
    private List<ListaFuncionarios> funcionarios;
    private DefaultTableModel tableModel;
    private JTable table;

    //
    // contrutor
    public FuncionariosControl(List<ListaFuncionarios> funcionarios, DefaultTableModel tableModel, JTable table) {
        this.funcionarios = funcionarios;
        this.tableModel = tableModel;
        this.table = table;
    }

    //
    // métodos do modelo CRUD
    //
    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        funcionarios = new FuncionariosDAO().listarTodos();
        // Obtém os Funcionarios atualizados do banco de dados
        for (ListaFuncionarios funcionarios : funcionarios) {
            // Adiciona os dados de cada funcionarios como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] {
                    funcionarios.getNome(), funcionarios.getCpf(), funcionarios.getEmail(), funcionarios.getTelefone(), 
                    funcionarios.getEndereco() });
        }
    }

    //
    // Método para cadastrar um novo funcionarios no banco de dados
    public void cadastrar(String nome, String cpf, String email, String telefone, String endereco) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Deseja cadastrar novo funcionarios?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            new FuncionariosDAO().cadastrar(nome, cpf, email, telefone, endereco);
            //
            // Chama o método de cadastro no banco de dados
            JOptionPane.showMessageDialog(null, "Cadastro concluido com sucesso");
            //atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado");
        }
    }

    //
    // Método para atualizar os dados de um Funcionarios no banco de dados
    public void atualizar(String nome, String cpf, String email, String telefone, String endereco) {
        new FuncionariosDAO().atualizar(nome, cpf, email, telefone, endereco);
        // Chama o método de atualização no banco de dados
            atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    //
    // Método para apagar um funcionarios do banco de dados
    public void apagar(String cpf) {
        Object[] options = { "NÃO", "SIM" };
        int acao = JOptionPane.showOptionDialog(
                null,
                "Tem Certeza de que deseja excluir o cadastro desse funcionario?",
                "Confirmação",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

        if (acao == 1) {
            new FuncionariosDAO().apagar(cpf);
            // Chama o método de exclusão no banco de dados
            //atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
            JOptionPane.showMessageDialog(null, "Cadastro excluido");
        } else {
            JOptionPane.showMessageDialog(null, "Ação cancelada");
        }
    }
}
