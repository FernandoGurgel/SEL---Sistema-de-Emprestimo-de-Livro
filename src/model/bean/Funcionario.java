package model.bean;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Funcionario {

    private int codigo;
    private String nome;
    private String senha;
    private Date dataCadastro;
    private HBox buttons;
    public Button editar;
    public Button excluir;

    public Funcionario(int codigo, String nome, LocalDate dataCadastro) {
//        this.buttons = new HBox();
//        editar = new Button("Editar");
//        excluir = new Button("Excluir");
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;
        this.dataCadastro = Date.from(dataCadastro.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//        this.buttons.setSpacing(10);
//        this.buttons.getChildren().addAll(editar,excluir);
//        this.buttons.setAlignment(Pos.CENTER);
    }

    public Funcionario(int codigo, String nome, String senha, LocalDate dataCadastro) {
       // this.buttons = new HBox();
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;
        this.dataCadastro = Date.from(dataCadastro.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//        this.buttons.setSpacing(10);
//        this.buttons.setAlignment(Pos.CENTER);
//        this.buttons.getChildren().addAll(editar,excluir);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = Date.from(dataCadastro.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
