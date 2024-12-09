package supermercado_marinho.view;

import supermercado_marinho.controller.EstoqueController;
import supermercado_marinho.model.EstoqueModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PesquisaEstoqueView extends JPanel {
    
    

    private JLabel tituloLabel;
    private JLabel categoriaLabel;
    private JComboBox<String> categoriaComboBox;
    private JComboBox<String> nomeProdutoComboBox;
    private JLabel nomeProdutoLabel;
    private JButton btnPesquisar;
    private JButton btnVoltarMain;
    // Labels adicionais para exibir as informações do produto selecionado
    private JLabel nomeCategoriaLabel;
    private JLabel nomeCategoriaValorLabel;
    private JLabel nomeProdutoResultadoLabel;
    private JLabel nomeProdutoValorLabel;
    private JLabel quantidadeProdutoLabel;
    private JLabel quantidadeProdutoValorLabel;
    private JLabel precoProdutoLabel;
    private JLabel precoProdutoValorLabel;
    private EstoqueController estoqueController;

    // Construtor sem parâmetros
    public PesquisaEstoqueView()   {
        this.estoqueController = new EstoqueController(); // Inicializa o controlador de estoque
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        tituloLabel = new JLabel("Pesquisa de Estoque", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Ubuntu", Font.BOLD, 24));
        mainPanel.add(tituloLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        categoriaLabel = new JLabel("Categoria do Produto:");
        gbc.gridy = 0;
        centerPanel.add(categoriaLabel, gbc);

        categoriaComboBox = new JComboBox<>();
        categoriaComboBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        centerPanel.add(categoriaComboBox, gbc);

        nomeProdutoLabel = new JLabel("Nome do Produto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(nomeProdutoLabel, gbc);

        nomeProdutoComboBox = new JComboBox<>();
        nomeProdutoComboBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        centerPanel.add(nomeProdutoComboBox, gbc);

        btnPesquisar = new JButton("Pesquisar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(btnPesquisar, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(separator, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.gridx = 0;

        nomeCategoriaLabel = new JLabel("Nome da Categoria do Produto:");
        centerPanel.add(nomeCategoriaLabel, gbc);

        nomeCategoriaValorLabel = new JLabel("------");
        gbc.gridx = 1;
        centerPanel.add(nomeCategoriaValorLabel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;

        nomeProdutoResultadoLabel = new JLabel("Nome do Produto:");
        centerPanel.add(nomeProdutoResultadoLabel, gbc);

        nomeProdutoValorLabel = new JLabel("------");
        gbc.gridx = 1;
        centerPanel.add(nomeProdutoValorLabel, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;

        quantidadeProdutoLabel = new JLabel("Quantidade do Produto Disponível no Estoque:");
        centerPanel.add(quantidadeProdutoLabel, gbc);

        quantidadeProdutoValorLabel = new JLabel("------");
        gbc.gridx = 1;
        centerPanel.add(quantidadeProdutoValorLabel, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;

        precoProdutoLabel = new JLabel("Preço do Produto:");
        centerPanel.add(precoProdutoLabel, gbc);

        precoProdutoValorLabel = new JLabel("------");
        gbc.gridx = 1;
        centerPanel.add(precoProdutoValorLabel, gbc);

        JPanel navigationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcNav = new GridBagConstraints();
        gbcNav.insets = new Insets(10, 10, 10, 10);
        gbcNav.gridx = 0;
        gbcNav.gridy = 0;
        gbcNav.anchor = GridBagConstraints.SOUTH;
        gbcNav.weighty = 1.0;

        btnVoltarMain = new JButton("Voltar ao Painel Principal");
        navigationPanel.add(btnVoltarMain, gbcNav);

        mainPanel.add(navigationPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        // Adiciona o ActionListener para o botão de pesquisa
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoriaSelecionada = (String) categoriaComboBox.getSelectedItem();
                String produtoSelecionado = (String) nomeProdutoComboBox.getSelectedItem();

                if (categoriaSelecionada != null && produtoSelecionado != null) {
                    EstoqueModel produto = estoqueController.getProdutoPorCategoriaENome(categoriaSelecionada, produtoSelecionado);

                    if (produto != null) {
                        nomeCategoriaValorLabel.setText(produto.getNomeCategoria());
                        nomeProdutoValorLabel.setText(produto.getNomeProduto());
                        quantidadeProdutoValorLabel.setText(String.valueOf(produto.getQuantidadeProduto()));
                        precoProdutoValorLabel.setText(String.format("R$ %.2f", produto.getPreco()));
                    } else {
                        JOptionPane.showMessageDialog(PesquisaEstoqueView.this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(PesquisaEstoqueView.this, "Por favor, selecione uma categoria e um produto.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Carrega as categorias ao iniciar a view
        carregarCategorias();
        
        
    }
    
    

    // Método para carregar categorias no ComboBox
    public void carregarCategorias() {
        categoriaComboBox.removeAllItems();
        List<String> categorias = estoqueController.getCategorias();

        // Adiciona as categorias no ComboBox
        for (String categoria : categorias) {
            categoriaComboBox.addItem(categoria);
        }

        // Adiciona o ActionListener para carregar produtos ao selecionar uma categoria
        categoriaComboBox.addActionListener(e -> carregarProdutos());
    }

    // Método para carregar os produtos de uma categoria selecionada
    public void carregarProdutos() {
        nomeProdutoComboBox.removeAllItems(); // Limpa os produtos atuais

        String categoriaSelecionada = (String) categoriaComboBox.getSelectedItem();
        if (categoriaSelecionada != null) {
            List<EstoqueModel> produtos = estoqueController.getProdutosPorCategoria(categoriaSelecionada);

            // Adiciona os produtos ao ComboBox
            for (EstoqueModel produto : produtos) {
                nomeProdutoComboBox.addItem(produto.getNomeProduto());
            }

            // Revalida e repinta o JComboBox para garantir que a interface seja atualizada
            nomeProdutoComboBox.revalidate();
            nomeProdutoComboBox.repaint();
        }
    }

    // Este método deve ser chamado quando uma categoria ou produto for criado ou modificado
/*    @Override
    public void onEstoqueUpdated() {
        carregarCategorias(); // Recarrega as categorias
        carregarProdutos();   // Recarrega os produtos para a categoria selecionada
    }*/

    public JButton getBtnVoltarMain() {
        return btnVoltarMain;
    }
    
    public interface EstoqueUpdateListener {
    void onEstoqueUpdated();
}
    
    /**/
    
    
    // Método que será chamado quando a categoria mudar
    public void onCategoriaChanged(String categoria) {
        System.out.println("Categoria alterada para: " + categoria);
        // Implementação para atualizar a pesquisa com a nova categoria
    }

    // Método que será chamado quando o produto mudar
    public void onProdutoChanged(String produto) {
        System.out.println("Produto alterado para: " + produto);
        // Implementação para atualizar a pesquisa com o novo produto
    }


    
    /**/
    
    
    

}




