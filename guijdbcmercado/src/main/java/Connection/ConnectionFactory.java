package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {
    //Atributos
    private static final String url = "jdbc:postgresql://localhost:5432/RedeTrambique";
    private static final String usuario = "devnoturno"; //Nome do ADM do Banco de Dados
    private static final String senha = "devdatardeficaatarde"; //Senha do ADM do Banco de Dados
    //Método para ABRIR uma conexão com o Banco de Dados
    public static Connection getConnction(){
        try {
            return DriverManager.getConnection(url,usuario,senha);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados");
        }
    }
    //Método para FECHAR a conexão com o banco de dados
    public static void closeConnection(Connection connection){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();// Pesquisar
        }
    }
    //Método para FECHAR a conexão e o objeto PreparedStatement
    public static void closeConnection(Connection connection, PreparedStatement stmt) {
        closeConnection (connection);
        try {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}