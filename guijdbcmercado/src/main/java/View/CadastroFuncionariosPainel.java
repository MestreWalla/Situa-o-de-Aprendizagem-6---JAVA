package View;

import Model.ListaFuncionarios;
import Connection.FuncionariosDAO;
import Controller.FuncionariosControl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroFuncionariosPainel extends JPanel {

    private JLabel nomeFuncionarioLabel;
    private JTextField nomeFuncionarioTextField;
    private JLabel cpfFuncionarioLabel;
    private JTextField cpfFuncionarioTextField;
    private JLabel emailFuncionarioLabel;
    private JTextField emailFuncionarioTextField;
    private JLabel telefoneFuncionarioLabel;
    private JTextField telefoneFuncionarioTextField;
    private JLabel enderecoFuncionarioLabel;
    private JTextField enderecoFuncionarioTextField;
    private JButton cadastrarFuncionarioButton;

    // Tabela Produtos
    private List<ListaFuncionarios> listaFuncionarios;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    public CadastroFuncionariosPainel() {
        super();

        setLayout(new BorderLayout());
        // Font font = new Font("Arial Black", Font.PLAIN, 16);

        // Configuração da tabela
        String[] columnNames = { "Nome", "CPF", "Email", "Telefone", "Endereço" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        atualizarTabela();

        // Componentes iniciais
        nomeFuncionarioLabel = new JLabel("Nome:");
        nomeFuncionarioTextField = new JTextField(20);
        cpfFuncionarioLabel = new JLabel("CPF:");
        cpfFuncionarioTextField = new JTextField(20);
        emailFuncionarioLabel = new JLabel("Email:");
        emailFuncionarioTextField = new JTextField(20);
        telefoneFuncionarioLabel = new JLabel("Telefone:");
        telefoneFuncionarioTextField = new JTextField(11);
        enderecoFuncionarioLabel = new JLabel("Endereco:");
        enderecoFuncionarioTextField = new JTextField(20);
        cadastrarFuncionarioButton = new JButton("Cadastrar");

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(nomeFuncionarioLabel);
        formPanel.add(nomeFuncionarioTextField);
        formPanel.add(cpfFuncionarioLabel);
        formPanel.add(cpfFuncionarioTextField);
        formPanel.add(emailFuncionarioLabel);
        formPanel.add(emailFuncionarioTextField);
        formPanel.add(telefoneFuncionarioLabel);
        formPanel.add(telefoneFuncionarioTextField);
        formPanel.add(enderecoFuncionarioLabel);
        formPanel.add(enderecoFuncionarioTextField);
        formPanel.add(new JLabel());  // Espaço vazio
        formPanel.add(cadastrarFuncionarioButton);

        add(formPanel, BorderLayout.CENTER);

        // Criar as tabelas
        new FuncionariosDAO().criarTabela();

        // Adiciona listener ao botão de cadastrar
        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });
    }

    private void atualizarTabela() {
         tableModel.setRowCount(0);
        listaFuncionarios = new FuncionariosDAO().listarTodos();
        for (ListaFuncionarios produto : listaFuncionarios) {
            tableModel.addRow(new Object[] {
                    produto.getNome(), produto.getCpf(), produto.getEmail(), produto.getTelefone(),
                    produto.getEndereco() });
        }
    }

    private void cadastrarFuncionario() {
        // Obtém os dados do formulário
        String nome = nomeFuncionarioTextField.getText();
        String cpf = cpfFuncionarioTextField.getText();
        String email = emailFuncionarioTextField.getText();
        String telefone = telefoneFuncionarioTextField.getText();
        String endereco = enderecoFuncionarioTextField.getText();

        // Cria um novo Funcionário e adiciona à lista
        // Aqui você precisa fornecer a instância correta para ClientesControl
        new FuncionariosControl(listaFuncionarios, null, null).cadastrar(nome, cpf, email, telefone, endereco);


        // Limpa os campos do formulário
        nomeFuncionarioTextField.setText("");
        cpfFuncionarioTextField.setText("");
        emailFuncionarioTextField.setText("");
        telefoneFuncionarioTextField.setText("");
        enderecoFuncionarioTextField.setText("");
        atualizarTabela();

        // Exibe uma mensagem de sucesso (ou faça o que for apropriado no seu contexto)
        JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
    }
}
