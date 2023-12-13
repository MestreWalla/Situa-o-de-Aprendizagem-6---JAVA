package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.ListaEstoque;

public class EstoqueDAO {
    // atributos
    private Connection conexao;
    private List<ListaEstoque> produtos;

    // construtor
    public EstoqueDAO() {
        this.conexao = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criarTabela(){
        String sql = "CREATE TABLE IF NOT EXISTS ESTOQUE_MERCADO(CODIGO INT AUTO_INCREMENT PRIMARY KEY, TAG VARCHAR(255), DESCRICAO VARCHAR(255), QUANTIDADE INT, PRECO DOUBLE)";
        try (Statement stmt = this.conexao.createStatement()){
            stmt.execute(sql);
            System.out.println("(ESTOQUE) - Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("(ESTOQUE) - Erro ao criar a tabela: "+e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.conexao);
        }
    }

    // Listar todos os precoes cadastrados
    public List<ListaEstoque> listarTodos(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        produtos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ESTOQUE_MERCADO";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ListaEstoque produto = new ListaEstoque(
                        rs.getInt("codigo"),
                        rs.getString("tag"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
        }
        return produtos;
    }

    // Cadastrar Produto no banco de dados
    public void cadastrar(int codigo, String tag, String descricao, int quantidade, double preco) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO ESTOQUE_MERCADO (CODIGO, TAG, DESCRICAO, QUANTIDADE, PRECO) VALUES (?,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.setString(2, tag);
            stmt.setString(3, descricao);
            stmt.setInt(4, quantidade);
            stmt.setDouble(5, preco);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"(ESTOQUE) - Dados inseridos com sucesso");
        } catch (SQLException e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao inserir dados no banco de dados.", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    // Atualizar dados no banco de dados
    public void atualizar(int codigo, String tag, String descricao, int quantidade, double preco) {
        PreparedStatement stmt = null;
        String sql = "UPDATE ESTOQUE_MERCADO SET TAG = ?, DESCRICAO = ?, QUANTIDADE = ?, PRECO = ? WHERE CODIGO = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tag);
            stmt.setString(2, descricao);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, preco);
            stmt.setInt(5, codigo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"(ESTOQUE) - Dados atualizados com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"(ESTOQUE) - Erro ao atualizar dados no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    // Apagar dados do banco
    public void apagar(int codigo) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM ESTOQUE_MERCADO WHERE CODIGO = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"(ESTOQUE) - Dado apagado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"(ESTOQUE) - Erro ao apagar dados no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void cadastrar(int codigo, String tag, String descricao, int quantidade, double preco) {
    }
}