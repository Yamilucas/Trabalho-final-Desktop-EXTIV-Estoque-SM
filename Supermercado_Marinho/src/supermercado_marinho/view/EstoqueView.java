package supermercado_marinho.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import supermercado_marinho.controller.EstoqueController;
import supermercado_marinho.model.EstoqueModel;

public class EstoqueView extends JPanel {
    
    /*//////////////
    @Override
    public void onCategoriaChanged(String categoria) {
        pesquisaEstoqueView.getCategoriaComboBox().setSelectedItem(categoria);
       // carregarProdutos();
    }

    @Override
    public void onProdutoChanged(String produto) {
        pesquisaEstoqueView.getNomeProdutoComboBox().setSelectedItem(produto);
    }
    ///////////////////*/
    
    
    
    private JButton btnVoltarMain;
    private JLabel lblProduto;
    private JComboBox<String> comboProduto;
    private JLabel lblQuantidade;
    private JTextField txtQuantidade;
    private JLabel lblCategoria;
    private JTextField txtCategoria;
    private JLabel lblPreco;
    private JTextField txtPreco;
    private JButton btnAtualizar;
    private JLabel lblEstoqueSistema;
    private JLabel lblCategoriaProduto;
    private JLabel lblCategoriaProduto_c;
    private JComboBox<String> comboCategoria;
    private JComboBox<String> comboCategoriaAdicionaProduto;
    private JButton btnAdicionarCategoria;
    private JTextField txtNovaCategoria;
    private JLabel lblNomeProduto;
    private JTextField txtNomeProduto;
    private JButton btnAdicionarProduto;

    // Right panel components
    private JLabel lblCategoriaAntiga;
    private JComboBox<String> comboCategoriaAntiga;
    private JLabel lblCategoriaNova;
    private JTextField txtCategoriaNova;
    private JButton btnEditarCategoria;
    private JLabel lblNomeAntigoProduto;
    private JComboBox<String> comboNomeAntigoProduto;
    private JLabel lblNovoNomeProduto;
    private JTextField txtNovoNomeProduto;
    private JButton btnEditarProduto;
    
    private JLabel lblCategoriaExcluir;
    private JComboBox<String> comboCategoriaExcluir;
    private JComboBox<String> comboCategoriaCentro;
    private JButton btnExcluirCategoria;
    private JLabel lblCategoriaDoProdutoExcluir;
    private JComboBox<String> comboCategoriaDoProdutoExcluir;
    private JLabel lblProdutoExcluir;
    private JComboBox<String> comboProdutoExcluir;
    private JButton btnExcluirProduto;
    
    private EstoqueController estoqueController;
    private EstoqueModel estoqueModel;
    private PesquisaEstoqueView pesquisaEstoqueView;

    public  EstoqueView() {
        setLayout(new BorderLayout());
        
        

        // Inicialize os JComboBox antes de usá-los
        comboCategoria = new JComboBox<>();
        comboCategoriaExcluir = new JComboBox<>();
        comboCategoriaDoProdutoExcluir = new JComboBox<>();
        comboCategoriaAntiga = new JComboBox<>();
        comboCategoriaCentro = new JComboBox<>();
        comboCategoriaAdicionaProduto = new JComboBox<>();

        atualizarComboCategorias(comboCategoria);
        atualizarComboCategorias(comboCategoriaExcluir);
        atualizarComboCategorias(comboCategoriaDoProdutoExcluir);
        atualizarComboCategorias(comboCategoriaAntiga);
        atualizarComboCategorias(comboCategoriaCentro);
        atualizarComboCategorias(comboCategoriaAdicionaProduto);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        estoqueController = new EstoqueController();

        lblEstoqueSistema = new JLabel("Estoque do sistema");
        lblEstoqueSistema.setFont(new Font("Ubuntu", Font.BOLD, 18));
        lblEstoqueSistema.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblEstoqueSistema, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(10, 30, 10, 10);
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;

        // Seção de adicionar nova categoria
        lblCategoria = new JLabel("Nova Categoria:");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        leftPanel.add(lblCategoria, gbcLeft);

        txtNovaCategoria = new JTextField(20);
        gbcLeft.gridx = 1;
        leftPanel.add(txtNovaCategoria, gbcLeft);

        btnAdicionarCategoria = new JButton("Adicionar Categoria");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;
        gbcLeft.gridwidth = 2;
        leftPanel.add(btnAdicionarCategoria, gbcLeft);

        // ActionListener para btnAdicionarCategoria
        btnAdicionarCategoria.addActionListener(e -> {
            String novaCategoria = txtNovaCategoria.getText().trim();
            if (!novaCategoria.isEmpty()) {
                estoqueController.adicionarCategoria(novaCategoria);
                JOptionPane.showMessageDialog(null, "Categoria adicionada com sucesso!");
                atualizarComboCategorias(comboCategoria);
                atualizarComboCategorias(comboCategoriaExcluir);
                atualizarComboCategorias(comboCategoriaDoProdutoExcluir);
                atualizarComboCategorias(comboCategoriaAntiga);
                atualizarComboCategorias(comboCategoriaCentro);
                atualizarComboCategorias(comboCategoriaAdicionaProduto);
//                pesquisaEstoqueView.atualizarCategoriasEProdutos();

                // Atualiza a PesquisaEstoqueView
                //PesquisaEstoqueView.atualizarCategorias();
            }
        });

        // Inicialização dos componentes da UI para adicionar produto à categoria
        JSeparator separatorHorizontal = new JSeparator();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.gridwidth = 2;
        leftPanel.add(separatorHorizontal, gbcLeft);

        lblCategoriaProduto = new JLabel("Categoria do Produto:");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 3;
        leftPanel.add(lblCategoriaProduto, gbcLeft);

        comboCategoriaAdicionaProduto = new JComboBox<>();
        gbcLeft.gridx = 1;
        leftPanel.add(comboCategoriaAdicionaProduto, gbcLeft);

        lblNomeProduto = new JLabel("Nome do Produto:");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 4;
        leftPanel.add(lblNomeProduto, gbcLeft);

        txtNomeProduto = new JTextField(20);
        gbcLeft.gridx = 1;
        leftPanel.add(txtNomeProduto, gbcLeft);

        btnAdicionarProduto = new JButton("Adicionar Produto");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 5;
        gbcLeft.gridwidth = 2;
        leftPanel.add(btnAdicionarProduto, gbcLeft);

        // Ação do botão de adicionar produto
btnAdicionarProduto.addActionListener(e -> {
    String categoriaSelecionada = (String) comboCategoriaAdicionaProduto.getSelectedItem();
    String nomeProduto = txtNomeProduto.getText().trim();
    
    // Verifica se os campos estão preenchidos
    if (categoriaSelecionada == null || nomeProduto.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
    } else {
        // Adiciona o novo produto na categoria selecionada com valores iniciais de quantidade e preço
        estoqueController.adicionarProduto(nomeProduto, categoriaSelecionada, 0, 0.0);

        // Exibe mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");

        // Limpa os campos de entrada
        txtNomeProduto.setText("");

        // Atualiza apenas as ComboBoxes de produtos relacionadas à categoria selecionada
        atualizarComboProdutos(comboProdutoExcluir, categoriaSelecionada);
        atualizarComboProdutos(comboProduto, categoriaSelecionada);
        atualizarComboProdutos(comboNomeAntigoProduto, categoriaSelecionada);
//        pesquisaEstoqueView.atualizarCategoriasEProdutos();


        // Atualiza a PesquisaEstoqueView (descomente se necessário)
        //PesquisaEstoqueView.atualizarProdutos(categoriaSelecionada);
    }
});
        

        // Adiciona um separador
        JSeparator separatorAfterAddProduto = new JSeparator();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 6;
        gbcLeft.gridwidth = 2;
        leftPanel.add(separatorAfterAddProduto, gbcLeft);

        // Inicialização dos componentes da UI para exclusão de categoria
        lblCategoriaExcluir = new JLabel("Categoria a Excluir:");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 7;
        leftPanel.add(lblCategoriaExcluir, gbcLeft);

        comboCategoriaExcluir = new JComboBox<>();
        gbcLeft.gridx = 1;
        leftPanel.add(comboCategoriaExcluir, gbcLeft);

        btnExcluirCategoria = new JButton("Excluir Categoria");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 8;
        gbcLeft.gridwidth = 2;
        leftPanel.add(btnExcluirCategoria, gbcLeft);

        // Ação do botão de excluir categoria
        btnExcluirCategoria.addActionListener((ActionEvent e) -> {
            String categoriaParaExcluir = (String) comboCategoriaExcluir.getSelectedItem();
            if (categoriaParaExcluir == null || categoriaParaExcluir.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma categoria selecionada para exclusão.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir a categoria " + categoriaParaExcluir + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirmacao == JOptionPane.YES_OPTION) {
                    estoqueController.removerCategoria(categoriaParaExcluir);
                    JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    atualizarComboCategorias(comboCategoria);
                    atualizarComboCategorias(comboCategoriaExcluir);
                    atualizarComboCategorias(comboCategoriaDoProdutoExcluir);
                    atualizarComboCategorias(comboCategoriaAntiga);
                    atualizarComboCategorias(comboCategoriaCentro);
                    atualizarComboCategorias(comboCategoriaAdicionaProduto);
//                    pesquisaEstoqueView.atualizarCategoriasEProdutos();

                    // Atualiza a PesquisaEstoqueView
                    //PesquisaEstoqueView.atualizarCategorias();
                }
            }
        });

        





///////////////////////////////////////////







        // Adiciona um separador
        JSeparator separatorAfterExcluirCategoria = new JSeparator();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 9;
        gbcLeft.gridwidth = 2;
        leftPanel.add(separatorAfterExcluirCategoria, gbcLeft);

        // Inicialização dos componentes da UI para exclusão de produto
        lblCategoriaDoProdutoExcluir = new JLabel("Categoria do Produto:");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 10;
        leftPanel.add(lblCategoriaDoProdutoExcluir, gbcLeft);

        comboCategoriaDoProdutoExcluir = new JComboBox<>();
        gbcLeft.gridx = 1;
        leftPanel.add(comboCategoriaDoProdutoExcluir, gbcLeft);

        lblProdutoExcluir = new JLabel("Produto a Excluir:");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 11;
        leftPanel.add(lblProdutoExcluir, gbcLeft);

        comboProdutoExcluir = new JComboBox<>();
        gbcLeft.gridx = 1;
        leftPanel.add(comboProdutoExcluir, gbcLeft);

        // Adiciona um ActionListener para comboCategoriaDoProdutoExcluir
        comboCategoriaDoProdutoExcluir.addActionListener(e -> {
            String categoriaSelecionada = (String) comboCategoriaDoProdutoExcluir.getSelectedItem();
            atualizarComboProdutos(comboProdutoExcluir, categoriaSelecionada);
            atualizarComboProdutoPorCategoria(comboCategoriaAdicionaProduto, comboProduto);
            
        });

        btnExcluirProduto = new JButton("Excluir Produto");
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 12;
        gbcLeft.gridwidth = 2;
        leftPanel.add(btnExcluirProduto, gbcLeft);

        // Ação do botão de excluir produto
        btnExcluirProduto.addActionListener(e -> {
            String categoria = (String) comboCategoriaDoProdutoExcluir.getSelectedItem();
            String produto = (String) comboProdutoExcluir.getSelectedItem();
            if (categoria == null || produto == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma categoria e um produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                estoqueController.removerProduto(categoria, produto);
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
                atualizarComboProdutos(comboProdutoExcluir, categoria);
                atualizarComboProdutos(comboProduto, categoria);
                atualizarComboProdutos(comboNomeAntigoProduto, categoria);
//                pesquisaEstoqueView.atualizarCategoriasEProdutos();

                // Atualiza a PesquisaEstoqueView
                //PesquisaEstoqueView.atualizarProdutos(categoria);
            }
        });
        
        /////////////////////////////////////////
// Adiciona o painel à interface principal
mainPanel.add(leftPanel, BorderLayout.WEST);

// Separator vertical entre leftPanel e centerPanel
JSeparator separatorVertical = new JSeparator(SwingConstants.VERTICAL);
separatorVertical.setPreferredSize(new Dimension(2, 500));
mainPanel.add(separatorVertical, BorderLayout.CENTER);

// Centralizando o centerPanel e adicionando padding de 20 pixels à direita do separator
JPanel centerPanelWrapper = new JPanel(new GridBagLayout());
GridBagConstraints gbcWrapper = new GridBagConstraints();
gbcWrapper.anchor = GridBagConstraints.CENTER;

JPanel centerPanel = new JPanel(new GridBagLayout());
centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));  // Adicionando 20 pixels de padding à direita do separator
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(10, 10, 10, 10);
gbc.fill = GridBagConstraints.HORIZONTAL;

// Adicionando o label e o JComboBox de Categoria acima do Produto
JLabel lblCategoriaProduto_c = new JLabel("Categoria do Produto:");
gbc.gridx = 0;
gbc.gridy = 0;
centerPanel.add(lblCategoriaProduto_c, gbc);

comboCategoriaCentro = new JComboBox<>();
gbc.gridx = 1;
centerPanel.add(comboCategoriaCentro, gbc);

lblProduto = new JLabel("Produto:");
gbc.gridx = 0;
gbc.gridy = 1;
centerPanel.add(lblProduto, gbc);

comboProduto = new JComboBox<>();
gbc.gridx = 1;
centerPanel.add(comboProduto, gbc);

lblQuantidade = new JLabel("Quantidade:");
gbc.gridx = 0;
gbc.gridy = 2;
centerPanel.add(lblQuantidade, gbc);

txtQuantidade = new JTextField(20);
gbc.gridx = 1;
centerPanel.add(txtQuantidade, gbc);

lblPreco = new JLabel("Preço:");
gbc.gridx = 0;
gbc.gridy = 3;
centerPanel.add(lblPreco, gbc);

txtPreco = new JTextField(20);
gbc.gridx = 1;
centerPanel.add(txtPreco, gbc);

btnAtualizar = new JButton("Atualizar Informações");
gbc.gridx = 1;
gbc.gridy = 4;
centerPanel.add(btnAtualizar, gbc);


// ActionListener para comboCategoriaCentro
comboCategoriaCentro.addActionListener((ActionEvent e) -> {
    String categoriaSelecionada = (String) comboCategoriaCentro.getSelectedItem();

    if (categoriaSelecionada != null) {
        // Limpar os itens atuais no comboProduto
        comboProduto.removeAllItems();

        // Usar o método do EstoqueController para carregar os produtos da categoria selecionada
        List<String> produtos = estoqueController.carregarProdutosPorCategoria(categoriaSelecionada);

        if (produtos != null && !produtos.isEmpty()) {
            // Adicionar os produtos ao comboProduto
            for (String produto : produtos) {
                comboProduto.addItem(produto);
            }
        } else {
            // Caso não haja produtos para a categoria selecionada
            //JOptionPane.showMessageDialog(null, "Nenhum produto encontrado para a categoria selecionada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

        // Revalidar e repintar o comboProduto para garantir que a interface seja atualizada corretamente
        comboProduto.revalidate();
        comboProduto.repaint();
    }
});

// ActionListener para btnAtualizar
btnAtualizar.addActionListener((ActionEvent e) -> {
    try {
        String nomeProduto = (String) comboProduto.getSelectedItem();
        String quantidadeStr = txtQuantidade.getText().trim();
        String precoStr = txtPreco.getText().trim();
        String categoria = (String) comboCategoriaCentro.getSelectedItem();

        if (nomeProduto == null || quantidadeStr.isEmpty() || precoStr.isEmpty() || categoria == null) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            int quantidade = Integer.parseInt(quantidadeStr);
            double preco = Double.parseDouble(precoStr);
            EstoqueModel produto = estoqueController.getProdutoPorCategoriaENome(categoria, nomeProduto);

            if (produto != null) {
                // Atualiza o produto no banco de dados e na lista local
                estoqueController.atualizarProduto(nomeProduto, categoria, quantidade, preco);
                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            // Atualiza a PesquisaEstoqueView
            //PesquisaEstoqueView.atualizarProdutos(categoria);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar produto. Verifique os valores inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
});

//////////////////////////////


        // Centralizando o centerPanel no wrapper e no mainPanel
centerPanelWrapper.add(centerPanel, gbcWrapper);
mainPanel.add(centerPanelWrapper, BorderLayout.CENTER);

// Painel da direita
JPanel rightPanel = new JPanel(new GridBagLayout());
rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
GridBagConstraints gbcRight = new GridBagConstraints();
gbcRight.insets = new Insets(10, 40, 10, 10);
gbcRight.fill = GridBagConstraints.HORIZONTAL;

lblCategoriaAntiga = new JLabel("Categoria Antiga:");
gbcRight.gridx = 0;
gbcRight.gridy = 0;
rightPanel.add(lblCategoriaAntiga, gbcRight);

comboCategoriaAntiga = new JComboBox<>();
gbcRight.gridx = 1;
rightPanel.add(comboCategoriaAntiga, gbcRight);

lblCategoriaNova = new JLabel("Categoria Nova:");
gbcRight.gridx = 0;
gbcRight.gridy = 1;
rightPanel.add(lblCategoriaNova, gbcRight);

txtCategoriaNova = new JTextField(20);
gbcRight.gridx = 1;
rightPanel.add(txtCategoriaNova, gbcRight);

btnEditarCategoria = new JButton("Editar Categoria");
gbcRight.gridx = 0;
gbcRight.gridy = 2;
gbcRight.gridwidth = 2;
rightPanel.add(btnEditarCategoria, gbcRight);

// ActionListener para o botão "Editar Categoria"
btnEditarCategoria.addActionListener((ActionEvent e) -> {
    String categoriaAntiga = (String) comboCategoriaAntiga.getSelectedItem();
    String novaCategoria = txtCategoriaNova.getText().trim();
    
    if (categoriaAntiga == null || categoriaAntiga.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Selecione uma categoria para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
    } else if (novaCategoria.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Digite o nome da nova categoria.", "Erro", JOptionPane.ERROR_MESSAGE);
    } else {
        try {
            // Edita a categoria no banco de dados
            estoqueController.editarCategoria(categoriaAntiga, novaCategoria);
            JOptionPane.showMessageDialog(null, "Categoria editada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpa o campo de texto para a nova categoria
            txtCategoriaNova.setText("");

            // Atualiza os combos de categorias
            atualizarComboCategorias(comboCategoriaCentro);
            atualizarComboCategorias(comboCategoriaAntiga);
            atualizarComboCategorias(comboCategoriaAdicionaProduto);
            atualizarComboCategorias(comboCategoriaExcluir);
            atualizarComboCategorias(comboCategoriaDoProdutoExcluir);
            

            // Move os produtos da categoria antiga para a nova, sem alterar o nome do produto
            List<String> produtos = estoqueController.carregarProdutosPorCategoria(categoriaAntiga);
            for (String produto : produtos) {
                estoqueController.editarProdutoCategoria(produto, novaCategoria); // Apenas altera a categoria do produto
            }

            // Atualiza o combo de produtos (se necessário)
            atualizarComboProdutos(comboNomeAntigoProduto, novaCategoria);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao editar categoria: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
});



        



///////////////////////////////////////




        // Inicialização dos componentes da UI para editar produto
        JSeparator separatorRight = new JSeparator();
        gbcRight.gridy = 3;
        rightPanel.add(separatorRight, gbcRight);

        lblCategoriaProduto = new JLabel("Categoria do Produto:");
        gbcRight.gridx = 0;
        gbcRight.gridy = 4;
        rightPanel.add(lblCategoriaProduto, gbcRight);

        comboCategoria = new JComboBox<>();
        gbcRight.gridx = 1;
        rightPanel.add(comboCategoria, gbcRight);

        lblNomeAntigoProduto = new JLabel("Nome Antigo do Produto:");
        gbcRight.gridx = 0;
        gbcRight.gridy = 5;
        rightPanel.add(lblNomeAntigoProduto, gbcRight);

        comboNomeAntigoProduto = new JComboBox<>();
        gbcRight.gridx = 1;
        rightPanel.add(comboNomeAntigoProduto, gbcRight);

        lblNovoNomeProduto = new JLabel("Novo Nome do Produto:");
        gbcRight.gridx = 0;
        gbcRight.gridy = 6;
        rightPanel.add(lblNovoNomeProduto, gbcRight);

        txtNovoNomeProduto = new JTextField(20);
        gbcRight.gridx = 1;
        rightPanel.add(txtNovoNomeProduto, gbcRight);

        btnEditarProduto = new JButton("Editar Produto");
        gbcRight.gridx = 0;
        gbcRight.gridy = 7;
        gbcRight.gridwidth = 2;
        rightPanel.add(btnEditarProduto, gbcRight);

        // ActionListener para comboCategoria
        comboCategoria.addActionListener(e -> {
            String categoriaSelecionada = (String) comboCategoria.getSelectedItem();
            atualizarComboProdutos(comboNomeAntigoProduto, categoriaSelecionada);
            atualizarComboProdutoPorCategoria(comboCategoriaAdicionaProduto, comboProduto);
        });

        // ActionListener para btnEditarProduto
        btnEditarProduto.addActionListener(e -> {
            String categoria = (String) comboCategoria.getSelectedItem();
            String nomeProduto = (String) comboNomeAntigoProduto.getSelectedItem();
            if (categoria == null || nomeProduto == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma categoria e um produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String novoNome = txtNovoNomeProduto.getText().trim();
                if (novoNome != null && !novoNome.isEmpty()) {
                    estoqueController.editarProduto(nomeProduto, novoNome);
                    JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
                    atualizarComboProdutos(comboNomeAntigoProduto, categoria);
                }
            }
        });

        // Adiciona o painel à interface principal
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // Adicionando o botão 'Voltar ao Main Panel' ao final do mainPanel
        btnVoltarMain = new JButton("Voltar ao Painel Principal");
        btnVoltarMain.setPreferredSize(new Dimension(btnVoltarMain.getPreferredSize().width, btnVoltarMain.getPreferredSize().height));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnVoltarMain);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        
        carregarCategorias();
    }

    // Métodos getters para os botões e campos
    public JButton getBtnVoltarMain() {
        return btnVoltarMain;
    }

    // Método para atualizar a ComboBox de categorias
    private void atualizarComboCategorias(JComboBox<String> comboBox) {
        if (comboBox != null) {
            comboBox.removeAllItems();
            try {
                List<String> categorias = estoqueController.carregarCategorias();
                if (categorias != null) {
                    for (String categoria : categorias) {
                        comboBox.addItem(categoria);
                    }
                } else {
                    System.err.println("Nenhuma categoria encontrada.");
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar categorias: " + e.getMessage());
            }
        }
    }

   public void atualizarComboProdutos(JComboBox<String> comboProduto, String categoriaSelecionada) {
    List<String> produtos = estoqueController.carregarProdutosPorCategoria(categoriaSelecionada);
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    
    // Adiciona os produtos ao modelo da ComboBox
    for (String produto : produtos) {
        model.addElement(produto);
    }
    
    // Atualiza o modelo da ComboBox com a lista de produtos
    comboProduto.setModel(model);
}
    
    
    
    public void atualizarComboProdutoPorCategoria(JComboBox<String> comboCategoria, JComboBox<String> comboProduto) {
    String categoriaSelecionada = (String) comboCategoria.getSelectedItem();
    
    if (categoriaSelecionada != null) {
        List<String> produtos = estoqueController.carregarProdutosPorCategoria(categoriaSelecionada);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
        for (String produto : produtos) {
            model.addElement(produto);
        }
        
        comboProduto.setModel(model);  // Atualiza a ComboBox com os produtos da categoria selecionada
    }
}
    
    
    
    
    
    
    private List<ComboBoxListener> listeners = new ArrayList<>();

    public void addComboBoxListener(ComboBoxListener listener) {
        listeners.add(listener);
    }
    
    private void notifyCategoriaChanged(String categoria) {
        for (ComboBoxListener listener : listeners) {
            listener.onCategoriaChanged(categoria);
        }
    }

    private void notifyProdutoChanged(String produto) {
        for (ComboBoxListener listener : listeners) {
            listener.onProdutoChanged(produto);
        }
    }
    
    public interface EstoqueUpdateListener {
    void onEstoqueUpdated();
}
    
    public void carregarCategorias() {
        try {
            // Obter categorias da Controller
            List<String> categorias = estoqueController.getCategorias();
            
            if (categorias != null && !categorias.isEmpty()) {
                // Limpar as ComboBoxes antes de adicionar novos itens
                comboCategoriaCentro.removeAllItems();
                comboCategoriaAdicionaProduto.removeAllItems();
                comboCategoriaExcluir.removeAllItems();
                comboCategoriaDoProdutoExcluir.removeAllItems();
                comboCategoriaAntiga.removeAllItems();
                

                // Adicionar as categorias em cada ComboBox
                for (String categoria : categorias) {
                    atualizarComboCategorias(comboCategoria);
                    atualizarComboCategorias(comboCategoriaExcluir);
                    atualizarComboCategorias(comboCategoriaDoProdutoExcluir);
                    atualizarComboCategorias(comboCategoriaAntiga);
                    atualizarComboCategorias(comboCategoriaCentro);
                    atualizarComboCategorias(comboCategoriaAdicionaProduto);
                }

                // Carregar os produtos para a categoria selecionada automaticamente
                if (!categorias.isEmpty()) {
                    String categoriaSelecionada = categorias.get(0);
                    atualizarComboProdutos(comboNomeAntigoProduto, categoriaSelecionada);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não há categorias no estoque.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar categorias: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**/
    
    
    // Método que será chamado quando a categoria mudar
    public void onCategoriaChanged(String categoria) {
        System.out.println("Categoria alterada para: " + categoria);
        // Implementação para atualizar o estoque com a nova categoria
    }

    // Método que será chamado quando o produto mudar
    public void onProdutoChanged(String produto) {
        System.out.println("Produto alterado para: " + produto);
        // Implementação para atualizar o estoque com o novo produto
    }



    /**/
    
    

}



















