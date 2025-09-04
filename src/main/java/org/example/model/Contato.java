package org.example.model;

public class Contato {

    private int id;

    private String telefone;

    private String nome;

    private String email;

    private String observacao;


    public Contato(int id, String telefone, String nome, String email, String observacao) {
        this.id = id;
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
        this.observacao = observacao;
    }

    public Contato(String telefone, String nome, String email, String observacao) {
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}