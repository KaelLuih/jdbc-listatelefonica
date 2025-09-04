package org.example.app;

import org.example.dao.contatoDAO;
import org.example.model.Contato;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        inicio();
    }

    public static void inicio() {
        boolean sair = false;
        System.out.println("======================Lista Telefonica====================== \n" +
                "1. Cadastrar contato: Nome, Telefone, Email, Observação. \n" +
                "2. Listar todos os contatos cadastrados. \n" +
                "3. Buscar contato por nome. \n" +
                "4. Atualizar dados de um contato (telefone, email, observação). \n" +
                "5. Remover contato. \n" +
                "6. Sair do sistema. \n");
        int opcao = input.nextInt();
        input.nextLine();

        switch (opcao) {
            case 1: {
                cadastrarContato();
                break;
            }

            case 2: {
                listarContatos();
                break;
            }
            case 3: {
                buscarPorNome();
                break;
            }
            case 4: {
                atualizarContato();
                break;
            }
            case 6: {
                sair = true;
                break;
            }

        }
        if (!sair) {
            inicio();
        }
    

    }

    public static void cadastrarContato() {
        System.out.println("---Cadastro de contato---");
        System.out.println("Digite o nome:");
        String nome = input.nextLine();
        System.out.println("Digite o telefone:");
        String telefone = input.nextLine();
        System.out.println("Digite o email:");
        String email = input.nextLine();
        System.out.println("Digite a observação:");
        String observacao = input.nextLine();

        var contato = new Contato(nome, telefone, email, observacao);
        var dao = new contatoDAO();


        try {
            dao.inserirContato(contato);
            System.out.println("Contato cadastrado com sucesso");
        } catch (Exception erro) {
            System.out.println("Erro ao cadastrar" + erro);
        }
    }

    public static void listarContatos() {
        var dao = new contatoDAO();

        List<Contato> contatos = null;
        try {
            contatos = dao.listarContato();
            for (Contato c : contatos) {
                System.out.println("===========");
                System.out.println("ID:" + c.getId());
                System.out.println("Nome:" + c.getNome());
                System.out.println("Telefone:" + c.getTelefone());
                System.out.println("Email:" + c.getEmail());
                System.out.println("Observação:" + c.getObservacao());
                System.out.println("===========");


            }

        } catch (SQLException e) {
            System.out.println("Erro de execução ");
            e.printStackTrace();

        }

    }

    public static void buscarPorNome() {
        System.out.println("---Listar Por Nome---");
        System.out.println("Digite o nome");
        String nome = input.nextLine();

        var dao = new contatoDAO();
        try {
            List<Contato> contatos = dao. buscarContatoPorNome(nome);
         exibirContatos(contatos);
        } catch (SQLException e) {
            System.out.println("Erro de execução ");
            e.printStackTrace();

        }


    }

    public static void atualizarContato(){
        System.out.println("-- ATUALIZAR CONTATO --");
        List<Integer> idContatos = new ArrayList<>();
        List<Contato> contatos = new ArrayList<>();
        var dao = new contatoDAO();
        try{
            contatos = dao.listarContato();
            idContatos = exibirContatos(contatos);
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("ID do contato para edição:");
        int id = input.nextInt();
        input.nextLine();

        if(idContatos.contains(id)){
            inserirDados(2, id);
        } else {
            System.out.println("Opção inválida!");
            atualizarContato();
        }
    }

    public static List<Integer> exibirContatos(List<Contato> contatos) {
        List<Integer> idContatos = new ArrayList<>();
        for (Contato c : contatos) {
            System.out.println("===========");
            System.out.println("ID:" + c.getId());
            System.out.println("Nome:" + c.getNome());
            System.out.println("Telefone:" + c.getTelefone());
            System.out.println("Email:" + c.getEmail());
            System.out.println("Observação:" + c.getObservacao());
            System.out.println("===========");

            idContatos.add(c.getId());
        }

        return idContatos;
    }
    public static void inserirDados(int opcao, int id){
        var dao = new contatoDAO();
        System.out.println("Nome do contato:");
        String nome = input.nextLine();

        System.out.println("Telefone do contato:");
        String telefone = input.nextLine();

        System.out.println("Email do contato:");
        String email = input.nextLine();

        System.out.println("Observação:");
        String observacao = input.nextLine();

        switch (opcao){
            case 1 ->{
                try{
                    var contato = new Contato(nome, telefone, email, observacao);
                    dao.inserirContato(contato);
                    System.out.println("Contato inserido com sucesso!");
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            case 2 ->{
                try{
                    var contato = new Contato(id, nome, telefone, email, observacao);
                    dao.atualizarContato(contato);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    }

