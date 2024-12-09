/*package supermercado_marinho.view;

public class ComboBoxMediator implements ComboBoxListener {
    private PesquisaEstoqueView pesquisaEstoqueView;
    private EstoqueView estoqueView;

    public ComboBoxMediator(PesquisaEstoqueView pesquisaEstoqueView, EstoqueView estoqueView) {
        this.pesquisaEstoqueView = pesquisaEstoqueView;
        this.estoqueView = estoqueView;
    }

    @Override
    public void onCategoriaChanged(String categoria) {
        pesquisaEstoqueView.onCategoriaChanged(categoria);
        estoqueView.onCategoriaChanged(categoria);
    }

    @Override
    public void onProdutoChanged(String produto) {
        pesquisaEstoqueView.onProdutoChanged(produto);
        estoqueView.onProdutoChanged(produto);
    }
}*/

package supermercado_marinho.view;

public class ComboBoxMediator implements ComboBoxListener {
    private PesquisaEstoqueView pesquisaEstoqueView;
    private EstoqueView estoqueView;

    // Construtor para injetar as views
    public ComboBoxMediator(PesquisaEstoqueView pesquisaEstoqueView, EstoqueView estoqueView) {
        this.pesquisaEstoqueView = pesquisaEstoqueView;
        this.estoqueView = estoqueView;
    }

    // Método chamado quando a categoria for alterada
    @Override
    public void onCategoriaChanged(String categoria) {
        // Chama os métodos correspondentes nas views para atualizar a categoria
        pesquisaEstoqueView.onCategoriaChanged(categoria);
        estoqueView.onCategoriaChanged(categoria);
    }

    // Método chamado quando o produto for alterado
    @Override
    public void onProdutoChanged(String produto) {
        // Chama os métodos correspondentes nas views para atualizar o produto
        pesquisaEstoqueView.onProdutoChanged(produto);
        estoqueView.onProdutoChanged(produto);
    }
}


