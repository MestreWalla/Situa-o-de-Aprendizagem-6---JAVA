package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    // Atributos
    private static final String url = "jdbc:postgresql://localhost:5432/RedeTrambique";
    private static final String usuario = "postgres"; // Nome do ADM do Banco de Dados
    private static final String senha = "postgres"; // Senha do ADM do Banco de Dados
    // Método para ABRIR uma conexão com o Banco de Dados

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados", e);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement stmt) {
        closeConnection(connection);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement stmt, ResultSet rs) {
        closeConnection(connection, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para criar o banco de dados caso não exista
    public static void criarBanco() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, usuario, senha);
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS RedeTrambique";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createDatabaseSQL);
                System.out.println("Banco de dados criado com sucesso.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar o banco de dados", e);
        } finally {
            closeConnection(connection);
        }
    }

    public static void main(String[] args) {
        criarBanco();
    }
}
