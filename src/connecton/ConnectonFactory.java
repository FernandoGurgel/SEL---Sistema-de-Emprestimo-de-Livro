package connecton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectonFactory{

    private String usuario;
    private String senha;
    private String url = "jdbc:mysql://localhost/sel_db";
    private Connection connection;
    private static ConnectonFactory connectonFactory;

    public ConnectonFactory(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;

        try {
            connection = DriverManager.getConnection(url,usuario,senha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){return connection;}

}
