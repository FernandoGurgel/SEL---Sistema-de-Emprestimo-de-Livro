package model.bean;

import javafx.beans.property.*;
import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class FuncionarioProperty {

    private IntegerProperty codigo;
    private StringProperty nome;
    private StringProperty senha;
    //private List<Button> butoes;
    private ObjectProperty<LocalDate> dataCadastro;


    public FuncionarioProperty(int codigo, String nome, String senha) { //, List<Button> buttons
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
        this.senha = new SimpleStringProperty(senha);
       // this.butoes = buttons;
        this.dataCadastro = new SimpleObjectProperty<LocalDate>(LocalDate.of(2017,10,27));
    }

    public FuncionarioProperty(int codigo, String nome, String senha, Object o) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
        this.senha = new SimpleStringProperty(senha);
        // this.butoes = buttons;
        this.dataCadastro = new SimpleObjectProperty<LocalDate>(LocalDate.of(2017,10,27));
    }

    public FuncionarioProperty(int codigo, String nome) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
        this.dataCadastro = new SimpleObjectProperty<LocalDate>(LocalDate.of(2017,10,27));
    }

//    public List<Button> getButoes() {
//        return butoes;
//    }
//
//    public void setButoes(List<Button> butoes) {
//        this.butoes = butoes;
//    }


    public LocalDate getDataCadastro() {
        return dataCadastro.get();
    }

    public ObjectProperty<LocalDate> dataCadastroProperty() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getSenha() {
        return senha.get();
    }

    public StringProperty senhaProperty() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }
}
