package supermercado_marinho.model;

public class Registro_TransacoesModel {
    private int idTransacao;
    private String nomeCliente;
    private String categoriaProduto;
    private String nomeProduto;
    private int quantidade;
    private double preco;
    
    
    public Registro_TransacoesModel(String nomeCliente, String categoriaProduto, String nomeProduto, int quantidade, double preco) {
        this.nomeCliente = nomeCliente;
        this.categoriaProduto = categoriaProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters
    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
}
