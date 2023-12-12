package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.ListaVendas;

public class VendasDAO {
    private Connection connection;
    private List<ListaVendas> vendas;

    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS VENDAS_MERCADO(DATA_HORA VARCHAR(255), VALOR DOUBLE, VIP BOOLEAN)";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de vendas criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de vendas: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<ListaVendas> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        vendas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM VENDAS_MERCADO";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ListaVendas venda = new ListaVendas(
                        rs.getString("data_hora"),
                        rs.getDouble("valor"),
                        rs.getBoolean("vip")
                );
                vendas.add(venda);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return vendas;
    }

    public void cadastrar(String dataHora, double valor, boolean vip) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO VENDAS (DATA_HORA, VALOR, VIP) VALUES (?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, dataHora);
            stmt.setDouble(2, valor);
            stmt.setBoolean(3, vip);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao registrar venda.", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void atualizar(String dataHora, double valor, boolean vip) {
        PreparedStatement stmt = null;
        String sql = "UPDATE VENDAS SET VALOR = ?, VIP = ? WHERE DATA_HORA = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, valor);
            stmt.setBoolean(2, vip);
            stmt.setString(3, dataHora);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados de venda atualizados com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados da venda.");
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void apagar(String dataHora) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM VENDAS WHERE DATA_HORA = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, dataHora);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda removida com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover venda do banco de dados.");
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
