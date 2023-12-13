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
