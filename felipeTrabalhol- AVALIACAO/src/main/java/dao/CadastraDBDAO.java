package dao;

import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastraDBDAO implements ICadastraDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/produtos";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS produtos (id INT PRIMARY KEY, nome VARCHAR(255), descricao VARCHAR(255), preco DOUBLE, qtd INT)";
    private static final String INSERT_PRODUTO_SQL = "INSERT INTO produtos (id, nome, descricao, preco, qtd) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUTOS_SQL = "SELECT * FROM produtos";

    @Override
    public void cadastrarProduto(Produto produto) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(INSERT_PRODUTO_SQL)) {

                statement.setInt(1, produto.getId());
                statement.setString(2, produto.getNome());
                statement.setString(3, produto.getDescricao());
                statement.setDouble(4, produto.getPreco());
                statement.setInt(5, produto.getQtd());

                statement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(SELECT_PRODUTOS_SQL)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String descricao = resultSet.getString("descricao");
                    double preco = resultSet.getDouble("preco");
                    int qtd = resultSet.getInt("qtd");

                    Produto produto = new Produto(id, nome, descricao, preco, qtd);
                    produtos.add(produto);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public void salvarDados() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement statement = connection.createStatement()) {

                statement.execute(CREATE_TABLE_SQL);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
