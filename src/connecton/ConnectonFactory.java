package connecton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectonFactory{

    private String usuario;
    private String senha;
    private String url = "jdbc:mysql://localhost/";
    private String banco;

    public Connection getConnectionAdm(){
        usuario = "SELadm";
        senha = "admin";
        banco = "teste";
        try {
            return DriverManager.getConnection(url+banco,usuario,senha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
