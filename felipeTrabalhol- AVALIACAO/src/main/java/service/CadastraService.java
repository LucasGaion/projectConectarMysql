package service;

import dao.CadastraDBDAO;
import dao.CadastraTXTDAO;
import dao.CadastraXLSDAO;
import dao.ICadastraDAO;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CadastraService implements ICadastraService {
    public ICadastraDAO dbDAO;
    public ICadastraDAO xlsDAO;
    public ICadastraDAO txtDAO;

    public CadastraService() {
        this.dbDAO = new CadastraDBDAO();
        this.xlsDAO = new CadastraXLSDAO();
        this.txtDAO = new CadastraTXTDAO();
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        dbDAO.cadastrarProduto(produto);
        xlsDAO.cadastrarProduto(produto);
        txtDAO.cadastrarProduto(produto);
    }

    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.addAll(dbDAO.listarProdutos());
        produtos.addAll(xlsDAO.listarProdutos());
        produtos.addAll(txtDAO.listarProdutos());
        return produtos;
    }

    @Override
    public void salvarDados() {
        dbDAO.salvarDados();
        xlsDAO.salvarDados();
        txtDAO.salvarDados();
    }
}
