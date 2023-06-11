package dao;

import model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CadastraTXTDAO implements ICadastraDAO {
    private static final String FILE_PATH = "produtos.txt";

    @Override
    public void cadastrarProduto(Produto produto) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            String linha = produto.getId() + ";" + produto.getNome() + ";" + produto.getDescricao() + ";" + produto.getPreco() + ";" + produto.getQtd();
            bufferedWriter.write(linha);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try (FileReader fileReader = new FileReader(FILE_PATH);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String descricao = dados[2];
                double preco = Double.parseDouble(dados[3]);
                int qtd = Integer.parseInt(dados[4]);

                Produto produto = new Produto(id, nome, descricao, preco, qtd);
                produtos.add(produto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public void salvarDados() {
        // Nenhuma ação necessária, pois os dados já são salvos imediatamente após o cadastro
    }
}
