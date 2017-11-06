package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable{

    @FXML
    private Circle close;
    @FXML
    private Circle minimize;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onKeyboard(){

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
    private void onButtonAction(){

    }

}
