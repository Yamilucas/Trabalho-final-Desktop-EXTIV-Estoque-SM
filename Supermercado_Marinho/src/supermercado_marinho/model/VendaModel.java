package supermercado_marinho.model;

public class VendaModel {
    private String nomeCliente;
    private EstoqueModel produto;  // Referência ao EstoqueModel, que representa o produto
    private int quantidadeVendida;
    private double precoTotal;

    // Construtor padrão
    public VendaModel() {
    }

    // Construtor completo para iniciar a venda com informações
    public VendaModel(String nomeCliente, EstoqueModel produto, int quantidadeVendida) {
        this.nomeCliente = nomeCliente;
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
        calcularPrecoTotal();  // Calcula o preço total no momento da criação
    }

    // Getters e setters
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public EstoqueModel getProduto() {
        return produto;
    }

    public void setProduto(EstoqueModel produto) {
        this.produto = produto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
        calcularPrecoTotal();  // Atualiza o preço total sempre que a quantidade muda
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    // Método para calcular o preço total da venda com base na quantidade e preço do produto
    private void calcularPrecoTotal() {
        if (produto != null) {
            this.precoTotal = produto.getPreco() * quantidadeVendida;
        } else {
            this.precoTotal = 0;
        }
    }

    // Método para verificar a disponibilidade do produto em estoque
    public boolean verificarDisponibilidadeEstoque() {
        return produto != null && produto.getQuantidadeProduto() >= quantidadeVendida;
    }

    // Método para realizar a venda e atualizar o estoque do produto
    public boolean realizarVenda() {
        if (verificarDisponibilidadeEstoque()) {
            int novaQuantidade = produto.getQuantidadeProduto() - quantidadeVendida;
            produto.setQuantidadeProduto(novaQuantidade);  // Atualiza a quantidade no EstoqueModel
            calcularPrecoTotal();  // Recalcula o preço total
            return true;  // Venda realizada com sucesso
        }
        return false;  // Falha na venda (estoque insuficiente)
    }

    // Método para exibir informações detalhadas da venda
    public String obterDetalhesVenda() {
        return String.format("Cliente: %s \n Produto: %s \n Quantidade: %d \n Preço Total: R$ %.2f",
                nomeCliente, produto != null ? produto.getNomeProduto() : "Indisponível",
                quantidadeVendida, precoTotal);
    }

    // Método para cancelar a venda e devolver a quantidade ao estoque
    public void cancelarVenda() {
        if (produto != null) {
            int novaQuantidade = produto.getQuantidadeProduto() + quantidadeVendida;
            produto.setQuantidadeProduto(novaQuantidade);  // Restaura a quantidade no estoque
            this.precoTotal = 0;  // Zera o preço total
            this.quantidadeVendida = 0;  // Zera a quantidade vendida
        }
    }
}
