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
    private JButton mostrarComponentesButton;  // Botão para mostrar os componentes relacionados aos funcionários

    // Lista para armazenar clientes
    private ArrayList<ListaFuncionarios> listaFuncionarios;

    public CadastroFuncionariosPainel() {
        super();

        setLayout(new BorderLayout());
        Font font = new Font("Arial Black", Font.PLAIN, 16);

        // Criar a tabela no banco de dados quando o painel for criado
        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
        funcionariosDAO.criarTabela();

        // Inicializa a lista de funcionários
        listaFuncionarios = new ArrayList<>();

        // Componentes iniciais
        // Funcionários
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
        mostrarComponentesButton = new JButton("Adicionar Funcionário");  // Novo botão

        JPanel formPanel = new JPanel(new GridLayout(7, 2));  // Ajuste o número de linhas para incluir o novo botão
        // Funcionários
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
        formPanel.add(new JLabel());  // Espaço vazio
        formPanel.add(mostrarComponentesButton);  // Adicione o novo botão

        // Torna invisíveis os componentes relacionados aos funcionários inicialmente
        esconderComponentesFuncionarios();

        add(formPanel, BorderLayout.CENTER);

        // Adiciona listener aos botões de cadastrar e mostrar
        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });

        mostrarComponentesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarComponentesFuncionarios();
            }
        });
    }

    private void esconderComponentesFuncionarios() {
        // Torna invisíveis os componentes relacionados aos funcionários
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
    }

    private void mostrarComponentesFuncionarios() {
        // Torna visíveis os componentes relacionados aos funcionários
        nomeFuncionarioLabel.setVisible(true);
        nomeFuncionarioTextField.setVisible(true);
        cpfFuncionarioLabel.setVisible(true);
        cpfFuncionarioTextField.setVisible(true);
        emailFuncionarioLabel.setVisible(true);
        emailFuncionarioTextField.setVisible(true);
        telefoneFuncionarioLabel.setVisible(true);
        telefoneFuncionarioTextField.setVisible(true);
        enderecoFuncionarioLabel.setVisible(true);
        enderecoFuncionarioTextField.setVisible(true);
        cadastrarFuncionarioButton.setVisible(true);

        // Opcional: Esconde o botão após exibir os componentes
        mostrarComponentesButton.setVisible(false);
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
