package View;

import Model.ListaClientes;
import Connection.ClientesDAO;
import Controller.ClientesControl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroClientesPainel extends JPanel {

    private JLabel nomeClienteLabel;
    private JTextField nomeClienteTextField;
    private JLabel cpfClienteLabel;
    private JTextField cpfClienteTextField;
    private JLabel emailClienteLabel;
    private JTextField emailClienteTextField;
    private JLabel telefoneClienteLabel;
    private JTextField telefoneClienteTextField;
    private JLabel enderecoClienteLabel;
    private JTextField enderecoClienteTextField;
    private JButton cadastrarClienteButton;

    // Tabela Produtos
    private List<ListaClientes> listaClientes;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    public CadastroClientesPainel() {
        super();

        setLayout(new BorderLayout());
        // Font font = new Font("Arial Black", Font.PLAIN, 16);

        // Configuração da tabela
        String[] columnNames = { "Nome", "CPF", "Email", "Telefone", "Endereço" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        atualizarTabela();

        // Componentes iniciais
        nomeClienteLabel = new JLabel("Nome:");
        nomeClienteTextField = new JTextField(20);
        cpfClienteLabel = new JLabel("CPF:");
        cpfClienteTextField = new JTextField(20);
        emailClienteLabel = new JLabel("Email:");
        emailClienteTextField = new JTextField(20);
        telefoneClienteLabel = new JLabel("Telefone:");
        telefoneClienteTextField = new JTextField(11);
        enderecoClienteLabel = new JLabel("Endereco:");
        enderecoClienteTextField = new JTextField(20);
        cadastrarClienteButton = new JButton("Cadastrar");

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(nomeClienteLabel);
        formPanel.add(nomeClienteTextField);
        formPanel.add(cpfClienteLabel);
        formPanel.add(cpfClienteTextField);
        formPanel.add(emailClienteLabel);
        formPanel.add(emailClienteTextField);
        formPanel.add(telefoneClienteLabel);
        formPanel.add(telefoneClienteTextField);
        formPanel.add(enderecoClienteLabel);
        formPanel.add(enderecoClienteTextField);
        formPanel.add(new JLabel());
        formPanel.add(cadastrarClienteButton);

        add(formPanel, BorderLayout.CENTER);

        // Criar as tabelas
        new ClientesDAO().criarTabela();

        // Adiciona listener aos botões de cadastrar
        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        listaClientes = new ClientesDAO().listarTodos();
        for (ListaClientes produto : listaClientes) {
            tableModel.addRow(new Object[] {
                    produto.getNome(), produto.getCpf(), produto.getEmail(), produto.getTelefone(),
                    produto.getEndereco() });
        }
    }

    private void atualizarFormularioCliente() {
        ajustarLayout(true);
        revalidate();
        repaint();
    }

    // Método para configurar a visibilidade dos componentes e ajustar o layout
    private void ajustarLayout(boolean clienteVisible) {
        nomeClienteLabel.setVisible(clienteVisible);
        nomeClienteTextField.setVisible(clienteVisible);
        cpfClienteLabel.setVisible(clienteVisible);
        cpfClienteTextField.setVisible(clienteVisible);
        emailClienteLabel.setVisible(clienteVisible);
        emailClienteTextField.setVisible(clienteVisible);
        telefoneClienteLabel.setVisible(clienteVisible);
        telefoneClienteTextField.setVisible(clienteVisible);
        enderecoClienteLabel.setVisible(clienteVisible);
        enderecoClienteTextField.setVisible(clienteVisible);
        cadastrarClienteButton.setVisible(clienteVisible);

        revalidate();
        repaint();
    }

    private void cadastrarCliente() {
        // Obtém os dados do formulário
        String nome = nomeClienteTextField.getText();
        String cpf = cpfClienteTextField.getText();
        String email = emailClienteTextField.getText();
        String telefone = telefoneClienteTextField.getText();
        String endereco = enderecoClienteTextField.getText();

        // Cria um novo cliente e adiciona à lista
        // Aqui você precisa fornecer a instância correta para ClientesControl
        new ClientesControl(listaClientes, null, null).cadastrar(nome, cpf, email, telefone, endereco);

        // Limpa os campos do formulário
        nomeClienteTextField.setText("");
        cpfClienteTextField.setText("");
        emailClienteTextField.setText("");
        telefoneClienteTextField.setText("");
        enderecoClienteTextField.setText("");
    }
}
