package model.bean;

import java.util.Date;

public class Funcionario {

    private int codigo;
    private String nome;
    private String senha;
    private Date dataCadastro;

    public Funcionario(int codigo, String nome, String senha, Date dataCadastro) {
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
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
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
