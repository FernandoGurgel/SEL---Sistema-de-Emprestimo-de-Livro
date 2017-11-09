package model.bean;

public class Administrador {

    private int codigo;
    private String senha;
    private String nome;

    public Administrador(int codigo, String senha, String nome) {
        this.codigo = codigo;
        this.senha = senha;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
