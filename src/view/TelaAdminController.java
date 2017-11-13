package view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.bean.Funcionario;
import model.bean.FuncionarioProperty;
import sun.util.resources.LocaleData;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static java.lang.System.out;

public class TelaAdminController implements Initializable {

    @FXML
    private TableView<Funcionario> tabFuncionario;
    @FXML
    private Circle close;
    @FXML
    private Pane tabPrivilegio;
    @FXML
    private TableColumn<Funcionario,String> nome;
    @FXML
    private TableColumn<Funcionario,Integer> codigo;
    @FXML
    private TableColumn<Funcionario,Date> dataCadastro;
//    @FXML
//    private TableColumn<Funcionario,Button> acoes;

    private double xOffset;
    private double yOffset;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFuncionario();

    }

    @FXML
    private void onClickTable(MouseEvent event){
        if(event.getClickCount() == 2 ){
            itemSelect(tabFuncionario.getSelectionModel().getSelectedItem());
        }
    }

    private void itemSelect(Funcionario selectedItem) {
        out.println(selectedItem.getNome());
        tabPrivilegio.setVisible(true);
    }

    @FXML
    private void onCloseTab(){
        tabPrivilegio.setVisible(false);
    }

    @FXML
    private void 

    private void loadFuncionario() {

        final ObservableList<Funcionario> properties = FXCollections.observableArrayList(
                new Funcionario(1,"fernando","fered", LocalDate.of(2017,11,10)),
                new Funcionario(2,"Gustavo", "43652431", LocalDate.of(2017,11,10))
        );

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        dataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
//        acoes.setCellValueFactory(new PropertyValueFactory<>("buttons"));

        tabFuncionario.setItems(properties);

    }

    // Fecha janela
    @FXML
    public void onClose(){
        Platform.exit();
    }

    /*
    *   Metodos para mover Janela
    */
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
