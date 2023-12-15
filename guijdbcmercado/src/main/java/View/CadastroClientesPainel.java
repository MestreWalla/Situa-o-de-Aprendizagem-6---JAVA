package View;

import Model.ListaClientes;
import Connection.ClientesDAO;
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

    // Tabela clientes
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

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);

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

        // Adiciona listener aos botões de cadastrar
        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
    }

    private void cadastrarCliente() {
        // Obtém os dados do formulário
        String nome = nomeClienteTextField.getText();
        String cpf = cpfClienteTextField.getText();
        String email = emailClienteTextField.getText();
        String telefone = telefoneClienteTextField.getText();
        String endereco = enderecoClienteTextField.getText();

        // Cria uma instância do DAO e cadastra o cliente no banco de dados
        ClientesDAO clientesDAO = new ClientesDAO();
        clientesDAO.cadastrar(nome, cpf, email, telefone, endereco);

        // Adiciona uma nova linha à tabela com os dados do cliente cadastrado
        Object[] rowCpf = { nome, cpf, email, telefone, endereco };
        tableModel.addRow(rowCpf);

        // Limpa os campos do formulário
        nomeClienteTextField.setText("");
        cpfClienteTextField.setText("");
        emailClienteTextField.setText("");
        telefoneClienteTextField.setText("");
        enderecoClienteTextField.setText("");
    }

    public List<ListaClientes> getListaClientes() {
        return listaClientes;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        listaClientes = new ClientesDAO().listarTodos();
        for (ListaClientes cliente : listaClientes) {
            tableModel.addRow(new Object[] {
                    cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone(),
                    cliente.getEndereco() });
        }
    }
}
