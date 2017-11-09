package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaAdminController implements Initializable {

    @FXML
    private TableView tabFuncionario;
    @FXML
    private Circle close;

    private double xOffset;
    private double yOffset;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFuncionario();
    }

    @FXML
    public void onClose(){
        Platform.exit();
    }

    @FXML
    private void onMousePressed(MouseEvent event){

        xOffset = (close.getScene().getWindow()).getX() - event.getScreenX();
        yOffset = (close.getScene().getWindow()).getY() - event.getScreenY();
    }

    @FXML
    private void onMouseDragged(MouseEvent event){
        (close.getScene().getWindow()).setX(event.getScreenX() + xOffset);
        (close.getScene().getWindow()).setY(event.getScreenY() + yOffset);
    }
}
