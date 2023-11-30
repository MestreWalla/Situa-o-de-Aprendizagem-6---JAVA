package Connection;

import java.sql.Connection;
import java.util.List;

import Model.Funcionarios;

public class FuncionariosDAO {
    //atributos
    private Connection connection;
    private List<Funcionarios> funcionarios;
    //construtor
    public FuncionariosDAO(){
        this.connection = ConnectionFactory.getConnection();
    }
}