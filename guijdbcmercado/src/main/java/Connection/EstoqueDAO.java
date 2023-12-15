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
    private Connection conexao;
    private List<ListaEstoque> produtos;

    public EstoqueDAO() {
        this.conexao = ConnectionFactory.getConnection();
        criarTabela();  // Chama o método de criação da tabela no construtor
        produtos = listarTodos();
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS ESTOQUE_MERCADO(" +
        "CODIGO INT PRIMARY KEY, " +
        "TAG VARCHAR(255), " +
        "DESCRICAO VARCHAR(255), " +
        "QUANTIDADE INT, " +
        "PRECO DOUBLE PRECISION)";
        try (Statement stmt = this.conexao.createStatement()) {
            stmt.execute(sql);
            System.out.println("(ESTOQUE) - Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("(ESTOQUE) - Erro ao criar a tabela: " + e.getMessage(), e);
        }
    }

    public List<ListaEstoque> listarTodos() {
        try (PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM ESTOQUE_MERCADO");
             ResultSet rs = stmt.executeQuery()) {
            produtos = new ArrayList<>();
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
            ex.printStackTrace();
        }
        return produtos;
    }

    public void cadastrar(int codigo, String tag, String descricao, int quantidade, double preco) {
        String sql = "INSERT INTO ESTOQUE_MERCADO (CODIGO, TAG, DESCRICAO, QUANTIDADE, PRECO) VALUES (?,?,?,?,?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.setString(2, tag);
            stmt.setString(3, descricao);
            stmt.setInt(4, quantidade);
            stmt.setDouble(5, preco);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "(ESTOQUE) - Dados inseridos com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao inserir dados no banco de dados.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizar(int codigo, String tag, String descricao, int quantidade, double preco) {
        String sql = "UPDATE ESTOQUE_MERCADO SET TAG = ?, DESCRICAO = ?, QUANTIDADE = ?, PRECO = ? WHERE CODIGO = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tag);
            stmt.setString(2, descricao);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, preco);
            stmt.setInt(5, codigo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "(ESTOQUE) - Dados atualizados com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(ESTOQUE) - Erro ao atualizar dados no banco de dados");
        }
    }

    // Adiciona o método para obter a lista de produtos
    public List<ListaEstoque> getListaEstoque() {
        return produtos;
    }

    public void apagar(int codigo) {
        if (produtoExiste(codigo)) {
            String sql = "DELETE FROM ESTOQUE_MERCADO WHERE CODIGO = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, codigo);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "(ESTOQUE) - Dado apagado com sucesso");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "(ESTOQUE) - Erro ao apagar dados no banco de dados");
            }
        } else {
            JOptionPane.showMessageDialog(null, "(ESTOQUE) - Produto não encontrado");
        }
    }

    public boolean produtoExiste(int codigo) {
        try (PreparedStatement stmt = conexao.prepareStatement("SELECT 1 FROM ESTOQUE_MERCADO WHERE CODIGO = ?");
             ResultSet rs = stmt.executeQuery()) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
