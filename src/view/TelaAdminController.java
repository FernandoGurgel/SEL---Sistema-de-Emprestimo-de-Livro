package view;

import connecton.ConnectonFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.bean.Funcionario;
import model.bean.FuncionarioProperty;
import sun.util.resources.LocaleData;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.System.out;
import static view.TelaLoginController.SENHA;
import static view.TelaLoginController.USUARIO;

public class TelaAdminController implements Initializable {

    @FXML
    private ImageView volta;
    @FXML
    private ImageView excluir;
    @FXML
    private ImageView salvar;
    @FXML
    private Pane panePerfil;
    @FXML
    private TextField txtNomePerfil;
    @FXML
    private TextField txtSenhaPerfil;
    @FXML
    private TextField txtConfirPerfil;
    @FXML
    private Button btnEditarUser;
    @FXML
    private Button btnNovoUsuario;
    @FXML
    private Pane paneUser;
    @FXML
    private Text labelPerfil;
    @FXML
    private Label labelUsuario;
    @FXML
    private ComboBox<String> listTabela;
    @FXML
    private CheckBox update;
    @FXML
    private CheckBox select;
    @FXML
    private CheckBox delete;
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
    private Connection connection;
    private Funcionario usuarioSelecionado;
    private ObservableList<Funcionario> properties;

//    @FXML
//    private TableColumn<Funcionario,Button> acoes;

    private double xOffset;
    private double yOffset;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = new ConnectonFactory(USUARIO,SENHA).getConnection();
            loadFuncionario();
            listComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mensagem(String mensagem) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Aletar! Login");
        dialogoInfo.setContentText(mensagem);
        dialogoInfo.showAndWait();
    }

    private void listComboBox() {
        try {
            List<String> list = new ArrayList<>();
            String sql = "call sp_retornaTabelas()";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
            listTabela.getItems().addAll(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewPrivilegio(){
        out.println(listTabela.getValue());
        try {
            String sql = "call sp_retornaPremissoesTabela('"+usuarioSelecionado.getNome()+"','"+listTabela.getValue()+"')";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickTable(MouseEvent event){
        if (event.getSource().equals(btnEditarUser)){
            telaPerfil(true);
        }else if (event.getSource().equals(btnNovoUsuario)){
            telaPerfil(false);
        }else if (event.getClickCount() == 1 ) {
            paneUser.setVisible(true);
            itemSelect(tabFuncionario.getSelectionModel().getSelectedItem(),false);
        }else if(event.getClickCount() == 2 ){
            paneUser.setVisible(false);
            itemSelect(tabFuncionario.getSelectionModel().getSelectedItem(),true);
        }
    }


    private void telaPerfil(boolean b) {
        panePerfil.setVisible(true);
        // para editar
        if (b){
            labelPerfil.setText("Editar perfil");
            txtNomePerfil.setDisable(true);
        }// para Cadastrar
        else{
            labelPerfil.setText("Cadastra perfil");
            txtNomePerfil.setDisable(false);
        }
    }

    @FXML
    private void OnAcionImg(){
        if (volta.isPressed()){
            panePerfil.setVisible(false);
            txtNomePerfil.setText("");
            txtSenhaPerfil.setText("");
            txtConfirPerfil.setText("");
        }else if(excluir.isPressed()){

        }else if(salvar.isPressed()){
            if (txtSenhaPerfil.getText().equals(txtConfirPerfil.getText())){
                if (labelPerfil.getText().equals("Cadastra perfil")){
                    String sql = "call sp_criauser('"+txtNomePerfil.getText()+"','"+txtSenhaPerfil.getText()+"')";
                    try {
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.execute();
                        mensagem("Perfil cadastrado com sucesso");
                        properties.add(new Funcionario(properties.size()+1,txtNomePerfil.getText()));
                        panePerfil.setVisible(false);
                        txtNomePerfil.setText("");
                        txtSenhaPerfil.setText("");
                        txtConfirPerfil.setText("");
                    } catch (SQLException e) {
                        mensagem("Perfil deu merda");
                        e.printStackTrace();
                    }
                }
            }else{
                mensagem("Senha incorreta");
            }
        }

    }

    private void itemSelect(Funcionario selectedItem,boolean b) {
        out.println(selectedItem.getNome());
        usuarioSelecionado = selectedItem;
        if (b){
            tabPrivilegio.setVisible(true);
        }else{
            editarCliente();
        }
    }

    private void editarCliente() {
        paneUser.setVisible(true);
        labelUsuario.setText(usuarioSelecionado.getNome());
    }

    @FXML
    private void onCloseTab(){
        listTabela.getSelectionModel().clearSelection();
        tabPrivilegio.setVisible(false);
    }

    private void loadFuncionario() {

        List<Funcionario> listUsuario = new ArrayList<>();

        try {
            String sql = "select user from mysql.user where user LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"%SEL_%");
            ResultSet resultSet = statement.executeQuery();
            int cont = 1;
            while (resultSet.next()){
                listUsuario.add(new Funcionario(cont,resultSet.getString(1)));
                cont++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        properties = FXCollections.observableArrayList(listUsuario);

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
//        dataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
//        acoes.setCellValueFactory(new PropertyValueFactory<>("buttons"));

        tabFuncionario.setItems(properties);

    }

    // Fecha janela
    @FXML
    public void onClose(){
        Platform.exit();
    }

    @FXML
    private void onSalvarPriv(){

        String sql;

        if(!select.isSelected() && !update.isSelected() && !delete.isSelected()){
            mensagem("Selecione algum item!!!");
        }else {
            sql = "call sp_garantePriv("+update.isSelected()+","+select.isSelected()+","+delete.isSelected()+"," +
                    "'"+usuarioSelecionado.getNome()+"','"+listTabela.getValue()+"')";
            out.println(sql);
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                mensagem("Privilegio atribuido com sucesso");
                tabPrivilegio.setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
