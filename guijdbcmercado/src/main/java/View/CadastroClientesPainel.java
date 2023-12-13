package View;

import Model.ListaClientes;
import Model.ListaFuncionarios;
import Connection.ClientesDAO;
import Connection.FuncionariosDAO;

import javax.swing.*;

import Controller.ClientesControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CadastroClientesPainel extends JPanel {
    // ESCOLHA DE CADASTRO
    private JRadioButton clienteRadioButton;
    // CLIENTES
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

    // Lista para armazenar clientes
    private ArrayList<ListaClientes> listaClientes;

    public CadastroClientesPainel() {
        super();

        setLayout(new BorderLayout());
        Font font = new Font("Arial Black", Font.PLAIN, 16);

        // Criar a tabela no banco de dados quando o painel for criado
        ClientesDAO clientesDAO = new ClientesDAO();
        clientesDAO.criarTabela();

        // Inicializa a lista de clientes
        listaClientes = new ArrayList<>();

        // RadioButtons para escolher entre Cliente e Funcionário
        // CLIENTE
        clienteRadioButton = new JRadioButton("Cliente");
        clienteRadioButton.setFont(font);
        ImageIcon icoCliente = new ImageIcon("guijdbcmercado\\src\\main\\resources\\Icons\\Cliente.png");
        Image ImageCliente = icoCliente.getImage();
        Image scaledImageCliente = ImageCliente.getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        icoCliente = new ImageIcon(scaledImageCliente);
        clienteRadioButton.setIconTextGap(30);
        clienteRadioButton.setIcon(icoCliente);
        clienteRadioButton.setPreferredSize(new Dimension(150, 100));

        ButtonGroup escolha = new ButtonGroup();
        escolha.add(clienteRadioButton);

        JPanel radioPanel = new JPanel();
        radioPanel.add(clienteRadioButton);

        add(radioPanel, BorderLayout.NORTH);

        // Adiciona listener aos RadioButtons
        clienteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarFormularioCliente();
            }
        });

        funcionarioRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarFormularioFuncionario();
            }
        });

        // Componentes iniciais
        // Clientes
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
        // Funcionarios
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

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        // Cliente
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
        formPanel.add(new JLabel()); // Espaço vazio
        formPanel.add(cadastrarClienteButton);
        // Funcionario
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
        formPanel.add(new JLabel()); // Espaço vazio
        formPanel.add(cadastrarFuncionarioButton);

        // Oculta os componentes relacionados aos funcionários inicialmente
        nomeFuncionarioLabel.setVisible(false);
        nomeFuncionarioTextField.setVisible(false);
        cpfFuncionarioLabel.setVisible(false);
        cpfFuncionarioTextField.setVisible(false);
        emailFuncionarioLabel.setVisible(false);
        emailFuncionarioTextField.setVisible(false);
        telefoneFuncionarioLabel.setVisible(false);
        telefoneFuncionarioTextField.setVisible(false);
        enderecoFuncionarioLabel.setVisible(false);
        enderecoFuncionarioTextField.setVisible(false);
        cadastrarFuncionarioButton.setVisible(false);

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

        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });
    }

    private void atualizarFormularioCliente() {
        ajustarLayout(true, false);
        revalidate();
        repaint();
    }

    private void atualizarFormularioFuncionario() {
        ajustarLayout(false, true);
        revalidate();
        repaint();
    }

    // Método para configurar a visibilidade dos componentes e ajustar o layout
    private void ajustarLayout(boolean clienteVisible, boolean funcionarioVisible) {
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
        nomeFuncionarioLabel.setVisible(funcionarioVisible);
        nomeFuncionarioTextField.setVisible(funcionarioVisible);
        cpfFuncionarioLabel.setVisible(funcionarioVisible);
        cpfFuncionarioTextField.setVisible(funcionarioVisible);
        emailFuncionarioLabel.setVisible(funcionarioVisible);
        emailFuncionarioTextField.setVisible(funcionarioVisible);
        telefoneFuncionarioLabel.setVisible(funcionarioVisible);
        telefoneFuncionarioTextField.setVisible(funcionarioVisible);
        enderecoFuncionarioLabel.setVisible(funcionarioVisible);
        enderecoFuncionarioTextField.setVisible(funcionarioVisible);
        cadastrarFuncionarioButton.setVisible(funcionarioVisible);
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
        new ClientesControl(listaClientes, null, null).cadastrar(nome, cpf, email, telefone, endereco);

        // Limpa os campos do formulário
        nomeClienteTextField.setText("");
        cpfClienteTextField.setText("");
        emailClienteTextField.setText("");
        telefoneClienteTextField.setText("");
        enderecoClienteTextField.setText("");
<<<<<<< Updated upstream:guijdbcmercado/src/main/java/View/CadastroClientesPainel.java

        // Exibe uma mensagem de sucesso (ou faça o que for apropriado no seu contexto)
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
=======
        
>>>>>>> Stashed changes:guijdbcmercado/src/main/java/View/CadastroPainel.java
    }

    private void cadastrarFuncionario() {
        // Obtém os dados do formulário
        String nome = nomeFuncionarioTextField.getText();
        String cpf = cpfFuncionarioTextField.getText();
        String email = emailFuncionarioTextField.getText();
        String telefone = telefoneFuncionarioTextField.getText();
        String endereco = enderecoFuncionarioTextField.getText();

        // Cria um novo Funcionario e adiciona à lista
        ListaFuncionarios novoFuncionario = new ListaFuncionarios(nome, cpf, email, telefone, endereco);
        listaFuncionarios.add(novoFuncionario);

        // Limpa os campos do formulário
        nomeFuncionarioTextField.setText("");
        cpfFuncionarioTextField.setText("");
        emailFuncionarioTextField.setText("");
        telefoneFuncionarioTextField.setText("");
        enderecoFuncionarioTextField.setText("");

        // Exibe uma mensagem de sucesso (ou faça o que for apropriado no seu contexto)
        JOptionPane.showMessageDialog(this, "Funcionario cadastrado com sucesso!");
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new Runnable() {
    // @Override
    // public void run() {
    // JFrame frame = new JFrame();
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.getContentPane().add(new CadastroPainel());
    // frame.setSize(400, 300);
    // frame.setVisible(true);
    // }
    // });
    // }
}