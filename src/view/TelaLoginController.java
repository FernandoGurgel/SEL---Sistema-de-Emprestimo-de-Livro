package view;

import connecton.ConnectonFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class TelaLoginController implements Initializable{

    static String USUARIO;
    static String SENHA;

    @FXML
    private Circle close;
    @FXML
    private Circle minimize;
    @FXML
    private Button btnEntrar;
    @FXML
    private Label label;
    @FXML
    private TextField login;
    @FXML
    private TextField senha;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onKeyboard(KeyEvent event){
        if(event.getCode().equals(KeyCode.TAB) || event.getCode().equals(KeyCode.ENTER))
            addEntrar();
    }

    @FXML
    private void navBarOption(){
        if (close.isPressed()){
            Platform.exit();
        }else if(minimize.isPressed()){
            ((Stage)minimize.getScene().getWindow()).setIconified(true);
        }
    }


    @FXML
    private void addEntrar() {
        if (!login.getText().equals("") && btnEntrar.getText().equals("Próximo")) {
            label.setText("Senha:");
            login.setVisible(false);
            senha.setVisible(true);
            btnEntrar.setText("Entrar");
        } else if (!senha.getText().equals("") && btnEntrar.getText().equals("Entrar")) {
            btnEntrar.setDisable(true);
            btnEntrar.setVisible(false);
            senha.setVisible(false);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setText("Validando...");
            onvalidate();
        } else {
            mensagem("Campo vazio!!!!");
        }
    }

    private void onvalidate() {

        try {
            USUARIO = login.getText().toString();
            SENHA = senha.getText().toString();
            Connection connection = new ConnectonFactory(login.getText().toString(),senha.getText().toString()).getConnection();
            out.println("Conexao aberta!!");
            connection.close();
            telaAdmin();
        } catch (SQLException e) {
            mensagem("Usuario ou login invalido");
            //out.println(e.getErrorCode());
        }
    }

    private void mensagem(String mensagem) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Aletar! Login");
        dialogoInfo.setContentText(mensagem);
        dialogoInfo.showAndWait();
    }

   private void telaAdmin(){

       try {
           Parent root  = FXMLLoader.load(getClass().getResource("../view/tela_admin_principal.fxml"));
           Stage stage = new Stage();
           stage.initStyle(StageStyle.UNDECORATED);
           stage.setScene(new Scene(root));
           (senha.getScene().getWindow()).hide();
           stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
