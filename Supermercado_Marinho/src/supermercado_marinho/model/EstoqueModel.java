package supermercado_marinho.model;

import java.util.ArrayList;
import java.util.List;
import supermercado_marinho.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EstoqueModel {
    private String nomeCategoria;
    private String nomeProduto;
    private int quantidadeProduto;
    private double preco;
    private static List<EstoqueModel> produtos = new ArrayList<>(); // Lista dinâmica de produtos
    private static List<CategoriaModel> categorias = new ArrayList<>(); // Lista de categorias

    // Construtor com parâmetros
    public EstoqueModel(String nomeCategoria, String nomeProduto, int quantidadeProduto, double preco) {
        this.nomeCategoria = nomeCategoria;
        this.nomeProduto = nomeProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.preco = preco;
    }

    // Métodos para gerenciar a lista de produtos
    public static List<EstoqueModel> getProdutos() {
        return produtos;
    }

    public static void adicionarProduto(EstoqueModel produto) {
        produtos.add(produto);
    }

    public void removerProduto(EstoqueModel produto) {
        produtos.remove(produto);
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    // Métodos para gerenciar a lista de categorias
    public static List<CategoriaModel> getCategorias() {
        return categorias;
    }

    public static void adicionarCategoria(CategoriaModel categoria) {
        categorias.add(categoria);
    }

    public static void removerCategoria(CategoriaModel categoria) {
        categorias.remove(categoria);
    }

    public static List<CategoriaModel> obterCategorias() {
        return categorias;
    }

    public static void adicionarNovaCategoria(String nomeCategoria) {
        adicionarCategoria(new CategoriaModel(nomeCategoria));
    }

    public static void removerCategoriaExistente(String nomeCategoria) {
        categorias.removeIf(categoria -> categoria.getNomeCategoria().equals(nomeCategoria));
    }

    public static boolean atualizarProduto(String categoria, String nomeProduto, int quantidade, double preco) {
        for (EstoqueModel produto : produtos) {
            if (produto.getNomeCategoria().equals(categoria) && produto.getNomeProduto().equals(nomeProduto)) {
                produto.setQuantidadeProduto(quantidade);
                produto.setPreco(preco);
                return true; 
            }
        }
        return false; 
    }

    // Método para verificar se o produto existe dentro de uma categoria
    public static boolean produtoExiste(String categoria, String nomeProduto) {
        for (EstoqueModel produto : produtos) {
            if (produto.getNomeCategoria().equals(categoria) && produto.getNomeProduto().equals(nomeProduto)) {
                return true; // Produto encontrado
            }
        }
        return false; // Produto não encontrado
    }

    // Método para verificar se a categoria existe
    public static boolean categoriaExiste(String nomeCategoria) {
        for (CategoriaModel categoria : categorias) {
            if (categoria.getNomeCategoria().equals(nomeCategoria)) {
                return true; // Categoria encontrada
            }
        }
        return false; // Categoria não encontrada
    }

    public static List<EstoqueModel> getProdutosPorCategoria(String nomeCategoria) {
        List<EstoqueModel> produtosPorCategoria = new ArrayList<>();
        for (EstoqueModel produto : produtos) {
            if (produto.getNomeCategoria().equalsIgnoreCase(nomeCategoria)) {
                produtosPorCategoria.add(produto);
            }
        }
        return produtosPorCategoria;
    }

    public EstoqueModel getProdutoPorCategoriaENome(String nomeCategoria, String nomeProduto) {
        List<EstoqueModel> produtos = EstoqueModel.getProdutos(); // Obter a lista de produtos (simulação)

        // Filtra para encontrar o produto com a categoria e nome específicos
        for (EstoqueModel produto : produtos) {
            if (produto.getNomeCategoria().equalsIgnoreCase(nomeCategoria) &&
                produto.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }

        return null; // Retorna null se o produto não for encontrado
    }
    /**/
   
    /**/
    
}
