package view;

import model.Produto;
import service.CadastraService;
import service.ICadastraService;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private static ICadastraService service;
    private static Scanner scanner;

    public static void main(String[] args) {
        // apache biblioteca salva no xlxs
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");

        service = new CadastraService();
        scanner = new Scanner(System.in);
        cadastrarProduto();
        listarProdutos();
        salvarDados();
        scanner.close();
    }

    public static void cadastrarProduto() {

        System.out.println("Codigo (ID) do produto:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nome do seu produto: ");
        String nome = scanner.nextLine();
        System.out.println("Descrição do seu produto :");
        String descricao = scanner.nextLine();
        System.out.println("Preço do seu produto: ");
        double preco = scanner.nextDouble();
        System.out.println("Digite a qtd do produto :");
        int quantidade = scanner.nextInt();
        System.out.println(" Programa finalizado.... by LEANDRÃO DO CS\n" );
        Produto produto = new Produto(id, nome, descricao, preco, quantidade);
        service.cadastrarProduto(produto);
    }

    public static void listarProdutos() {
        List<Produto> produtos = service.listarProdutos();
        for (Produto produto : produtos) {
        }
    }

    public static void salvarDados() {
        service.salvarDados();
    }
}
