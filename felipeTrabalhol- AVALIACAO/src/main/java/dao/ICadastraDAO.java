package dao;

import model.Produto;

import java.util.List;

public interface ICadastraDAO {
    void cadastrarProduto(Produto produto);

    List<Produto> listarProdutos();

    void salvarDados();
}
