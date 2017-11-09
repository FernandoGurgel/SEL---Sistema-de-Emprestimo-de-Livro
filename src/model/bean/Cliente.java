package model.bean;

import java.util.Date;

public class Cliente {

    private int matricula;
    private String nome;
    private String email;
    private Date dataNascimento;
    private Date datacadastro;
    private String senha;

    public Cliente(int matricula, String nome, String email, Date dataNascimento, Date datacadastro, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.datacadastro = datacadastro;
        this.senha = senha;
    }

    public Cliente(int matricula, String nome, Date datacadastro, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.datacadastro = datacadastro;
        this.senha = senha;
    }


    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
