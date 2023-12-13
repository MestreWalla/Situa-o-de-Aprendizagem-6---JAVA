package View;

import Model.ListaFuncionarios;
import Connection.FuncionariosDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    // Lista para armazenar funcionários
    private ArrayList<ListaFuncionarios> listaFuncionarios;

    public CadastroFuncionariosPainel() {
        super();

        setLayout(new BorderLayout());
        // Font font = new Font("Arial Black", Font.PLAIN, 16);

        // Inicializa a lista de funcionários
        listaFuncionarios = new ArrayList<>();

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

    private void cadastrarFuncionario() {
        // Obtém os dados do formulário
        String nome = nomeFuncionarioTextField.getText();
        String cpf = cpfFuncionarioTextField.getText();
        String email = emailFuncionarioTextField.getText();
        String telefone = telefoneFuncionarioTextField.getText();
        String endereco = enderecoFuncionarioTextField.getText();

        // Cria um novo Funcionário e adiciona à lista
        ListaFuncionarios novoFuncionario = new ListaFuncionarios(nome, cpf, email, telefone, endereco);
        listaFuncionarios.add(novoFuncionario);

        // Limpa os campos do formulário
        nomeFuncionarioTextField.setText("");
        cpfFuncionarioTextField.setText("");
        emailFuncionarioTextField.setText("");
        telefoneFuncionarioTextField.setText("");
        enderecoFuncionarioTextField.setText("");

        // Exibe uma mensagem de sucesso (ou faça o que for apropriado no seu contexto)
        JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
    }
}
