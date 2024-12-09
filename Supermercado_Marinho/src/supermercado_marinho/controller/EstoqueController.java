package supermercado_marinho.controller;

import supermercado_marinho.model.CategoriaModel;
import supermercado_marinho.model.EstoqueModel;
import supermercado_marinho.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstoqueController {
    private Connection conexao;
    
    

    //
    public EstoqueController() {
        Conexao.conectar();
        this.conexao = Conexao.con;
    }
    //
    

    // Método para adicionar uma nova categoria
    public void adicionarCategoria(String nomeCategoria) {
        String sql = "INSERT INTO Categoria (nomeCategoria) VALUES (?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoria);
            stmt.executeUpdate();

            // Adiciona à lista local
            CategoriaModel novaCategoria = new CategoriaModel(nomeCategoria);
            CategoriaModel.adicionarCategoria(novaCategoria);
            System.out.println("Categoria adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar categoria: " + e.getMessage());
        }
    }

    // Método para carregar todas as categorias
    public List<String> carregarCategorias() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT nomeCategoria FROM Categoria";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categorias.add(rs.getString("nomeCategoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    
    // Método para adicionar um novo produto
    public void adicionarProduto(String nomeProduto, String nomeCategoria, int quantidade, double preco) {
        String sql = "INSERT INTO Produto (nomeProduto, nomeCategoria, quantidadeProduto, precoProduto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeProduto);
            stmt.setString(2, nomeCategoria);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, preco);
            stmt.executeUpdate();

            // Adiciona o produto à lista local
            EstoqueModel novoProduto = new EstoqueModel(nomeCategoria, nomeProduto, quantidade, preco);
            EstoqueModel.adicionarProduto(novoProduto);
            
            System.out.println("Produto adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    // Método para atualizar um produto existente
    public void atualizarProduto(String nomeProduto, String nomeCategoria, int quantidade, double preco) {
        String sql = "UPDATE Produto SET quantidadeProduto = ?, precoProduto = ? WHERE nomeProduto = ? AND nomeCategoria = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setDouble(2, preco);
            stmt.setString(3, nomeProduto);
            stmt.setString(4, nomeCategoria);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                // Atualiza o produto na lista local
                EstoqueModel.atualizarProduto(nomeCategoria, nomeProduto, quantidade, preco);
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado. Verifique se o produto existe.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // Método para carregar produtos por categoria
    public List<String> carregarProdutosPorCategoria(String nomeCategoria) {
        List<String> produtos = new ArrayList<>();
        String sql = "SELECT nomeProduto FROM Produto WHERE nomeCategoria = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(rs.getString("nomeProduto"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Método para editar uma categoria
    public void editarCategoria(String nomeCategoriaAntiga, String nomeCategoriaNova) {
        String sql = "UPDATE Categoria SET nomeCategoria = ? WHERE nomeCategoria = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoriaNova);
            stmt.setString(2, nomeCategoriaAntiga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para remover uma categoria e todos os produtos associados
    public void removerCategoria(String nomeCategoria) {
        String sql = "DELETE FROM Categoria WHERE nomeCategoria = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoria);
            stmt.executeUpdate();

            // Remove produtos associados
            removerProdutosPorCategoria(nomeCategoria);
        } catch (SQLException e) {
            System.out.println("Erro ao remover categoria: " + e.getMessage());
        }
    }

    // Método para remover produtos por categoria
    private void removerProdutosPorCategoria(String nomeCategoria) {
        String sql = "DELETE FROM Produto WHERE nomeCategoria = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoria);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover produtos por categoria: " + e.getMessage());
        }
    }

    // Método para remover um produto
    public void removerProduto(String categoria, String nomeProduto) {
        String sql = "DELETE FROM Produto WHERE nomeCategoria = ? AND nomeProduto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, categoria);
            stmt.setString(2, nomeProduto);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                EstoqueModel.getProdutos().removeIf(produto -> produto.getNomeCategoria().equals(categoria) && produto.getNomeProduto().equals(nomeProduto));
                System.out.println("Produto removido do estoque: " + nomeProduto);
            } else {
                System.out.println("Produto ou categoria inexistente.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
        }
    }

    // Método para obter um produto por nome
    public EstoqueModel getProdutoPorNome(String nomeProduto) {
        EstoqueModel produto = null;
        String sql = "SELECT * FROM Produto WHERE nomeProduto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new EstoqueModel(
                        rs.getString("nomeCategoria"),
                        rs.getString("nomeProduto"),
                        rs.getInt("quantidadeProduto"),
                        rs.getDouble("precoProduto")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

    // Método para obter um produto por categoria e nome
    public EstoqueModel getProdutoPorCategoriaENome(String nomeCategoria, String nomeProduto) {
        EstoqueModel produto = null;
        String sql = "SELECT * FROM Produto WHERE nomeCategoria = ? AND nomeProduto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoria);
            stmt.setString(2, nomeProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new EstoqueModel(
                        rs.getString("nomeCategoria"),
                        rs.getString("nomeProduto"),
                        rs.getInt("quantidadeProduto"),
                        rs.getDouble("precoProduto")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter produto por categoria e nome: " + e.getMessage());
        }
        return produto;
    }

    // Método para obter a lista de categorias
    public List<String> getCategorias() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT nomeCategoria FROM Categoria";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("nomeCategoria"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter categorias: " + e.getMessage());
        }
        return categorias;
    }

    






////////////








    // Método para obter produtos de uma categoria específica
    public List<EstoqueModel> getProdutosPorCategoria(String nomeCategoria) {
        List<EstoqueModel> produtos = new ArrayList<>();
        String sql = "SELECT nomeProduto, quantidadeProduto, precoProduto, nomeCategoria FROM Produto WHERE nomeCategoria = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EstoqueModel produto = new EstoqueModel(
                        rs.getString("nomeCategoria"),
                        rs.getString("nomeProduto"),
                        rs.getInt("quantidadeProduto"),
                        rs.getDouble("precoProduto")
                    );
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter produtos por categoria: " + e.getMessage());
        }
        return produtos;
    }

    // Método para obter os nomes dos produtos
    public List<String> getNomesProdutos() {
        List<String> nomesProdutos = new ArrayList<>();
        String sql = "SELECT nomeProduto FROM Produto";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                nomesProdutos.add(rs.getString("nomeProduto"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter nomes dos produtos: " + e.getMessage());
        }
        return nomesProdutos;
    }

    // Método para editar um produto
    public void editarProduto(String nomeProdutoAntigo, String nomeProdutoNovo) {
        if (nomeProdutoAntigo == null || nomeProdutoNovo == null || nomeProdutoAntigo.trim().isEmpty() || nomeProdutoNovo.trim().isEmpty()) {
            System.out.println("Erro: Nenhum dos nomes de produto pode ser nulo ou vazio.");
            return;
        }

        if (nomeProdutoAntigo.equals(nomeProdutoNovo)) {
            System.out.println("Erro: O novo nome do produto deve ser diferente do nome antigo.");
            return;
        }

        String sql = "UPDATE Produto SET nomeProduto = ? WHERE nomeProduto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeProdutoNovo);
            stmt.setString(2, nomeProdutoAntigo);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto '" + nomeProdutoAntigo + "' atualizado para '" + nomeProdutoNovo + "' com sucesso.");
            } else {
                System.out.println("Falha ao editar o produto. Verifique se o produto existe.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao editar produto: " + e.getMessage());
        }
    }
    
    
    // Método para editar a categoria de um produto, mantendo o nome do produto
public void editarProdutoCategoria(String nomeProduto, String novaCategoria) {
    String sql = "UPDATE Produto SET nomeCategoria = ? WHERE nomeProduto = ?";
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, novaCategoria);  // Atualiza a categoria do produto
        stmt.setString(2, nomeProduto);    // Mantém o nome do produto
        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Categoria do produto '" + nomeProduto + "' atualizada para '" + novaCategoria + "' com sucesso.");
        } else {
            System.out.println("Produto não encontrado ou categoria já é a mesma.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao editar produto: " + e.getMessage());
    }
}



}


