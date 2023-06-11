package service;

import model.Produto;

import java.util.List;

public interface ICadastraService {
    void cadastrarProduto(Produto produto);

    List<Produto> listarProdutos();

    void salvarDados();
}
