package supermercado_marinho.model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaModel {
    private String nomeCategoria;
    private static List<CategoriaModel> categorias = new ArrayList<>(); // Lista de categorias

    public CategoriaModel(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
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
    
    
    
}
