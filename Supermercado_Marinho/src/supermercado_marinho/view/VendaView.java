package supermercado_marinho.view;

import supermercado_marinho.controller.VendaController;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import supermercado_marinho.controller.EstoqueController;
import supermercado_marinho.model.EstoqueModel;
import supermercado_marinho.model.Registro_TransacoesModel;
import supermercado_marinho.view.Registro_TransacoesView;
import javax.swing.table.DefaultTableModel;


public class VendaView extends JPanel {
    private JButton btnVoltarMain;
    private JLabel lblCliente;
    private JTextField txtCliente;
    private JLabel lblCategoriaProduto;
    private JComboBox<String> comboBoxCategoriaProduto;
    private JLabel lblProduto;
    private JComboBox<String> comboBoxProduto;
    private JLabel lblQuantidade;
    private JTextField txtQuantidade;
    private JButton btnSimularVenda;
    private JButton btnTerminarCompra;
    private JLabel lblSistemaDeVendas;
    private JLabel lblPreco;
    private JLabel valor_Preco;

    private DefaultListModel<String> listModel;
    private JList<String> listaCompras;

    private JLabel lblPrecoTotal;
    private JLabel valorPrecoTotal;

    private EstoqueController estoqueController;
    private VendaController vendaController;
    
    private ComboBoxMediator comboBoxMediator;
    private Registro_TransacoesView registroTransacoesView;

    // Lista para registro, que armazena as informações dos produtos vendidos
    private ArrayList<String> listaParaRegistro;

    public VendaView() {
        // Inicialize o EstoqueController antes de passar para o VendaController
        this.estoqueController = new EstoqueController();
        this.vendaController = new VendaController(this, estoqueController);
        this.registroTransacoesView = new Registro_TransacoesView();

        // Instancia as views
        PesquisaEstoqueView pesquisaEstoqueView = new PesquisaEstoqueView();
        EstoqueView estoqueView = new EstoqueView();

        // Instancia o ComboBoxMediator
        comboBoxMediator = new ComboBoxMediator(pesquisaEstoqueView, estoqueView);

        // Inicializa a lista de registro
        listaParaRegistro = new ArrayList<>();

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        lblSistemaDeVendas = new JLabel("Sistema de Vendas");
        lblSistemaDeVendas.setFont(new Font("Ubuntu", Font.BOLD, 18));
        lblSistemaDeVendas.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblSistemaDeVendas, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblCliente = new JLabel("Cliente:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(lblCliente, gbc);

        txtCliente = new JTextField();
        txtCliente.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        centerPanel.add(txtCliente, gbc);

        lblCategoriaProduto = new JLabel("Categoria do Produto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(lblCategoriaProduto, gbc);

        comboBoxCategoriaProduto = new JComboBox<>();
        comboBoxCategoriaProduto.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        centerPanel.add(comboBoxCategoriaProduto, gbc);

        lblProduto = new JLabel("Produto:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(lblProduto, gbc);

        comboBoxProduto = new JComboBox<>();
        comboBoxProduto.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        centerPanel.add(comboBoxProduto, gbc);

        lblQuantidade = new JLabel("Quantidade:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(lblQuantidade, gbc);

        txtQuantidade = new JTextField();
        txtQuantidade.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        centerPanel.add(txtQuantidade, gbc);

        lblPreco = new JLabel("Preço:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        centerPanel.add(lblPreco, gbc);

        valor_Preco = new JLabel("R$ 0,00");
        gbc.gridx = 1;
        centerPanel.add(valor_Preco, gbc);

        btnSimularVenda = new JButton("Comprar Produto");
        gbc.gridx = 1;
        gbc.gridy = 5;
        centerPanel.add(btnSimularVenda, gbc);

        btnTerminarCompra = new JButton("Finalizar Compra");
        gbc.gridy = 6;
        centerPanel.add(btnTerminarCompra, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        btnVoltarMain = new JButton("Voltar ao Painel Principal");
        navigationPanel.add(btnVoltarMain);

        mainPanel.add(navigationPanel, BorderLayout.SOUTH);

        listModel = new DefaultListModel<>();
        listaCompras = new JList<>(listModel);

        JPanel carrinhoPanel = new JPanel(new BorderLayout());
        carrinhoPanel.setBorder(BorderFactory.createTitledBorder("Produtos Comprados"));
        carrinhoPanel.add(new JScrollPane(listaCompras), BorderLayout.CENTER);

        mainPanel.add(carrinhoPanel, BorderLayout.EAST);

        lblPrecoTotal = new JLabel("Preço Total: ");
        valorPrecoTotal = new JLabel("0,00");

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalPanel.add(lblPrecoTotal);
        totalPanel.add(valorPrecoTotal);

        carrinhoPanel.add(totalPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // Adicionando a lógica para simular a venda e finalizar a compra
        btnSimularVenda.addActionListener(e -> {
            // Obtém as informações do produto e da quantidade
            String produtoSelecionado = getProdutoSelecionado();
            int quantidade = getQuantidade();

            // Obtém o produto selecionado do estoqueController
            EstoqueModel produto = estoqueController.getProdutoPorNome(produtoSelecionado);
            if (produto != null) {
                // Calcula o preço conjunto (quantidade * preço unitário)
                double precoUnitario = produto.getPreco();
                double precoConjunto = precoUnitario * quantidade;

                // Formata o item a ser adicionado na lista
                String item = "Cliente: " + getClienteNome() +
                              " | Categoria: " + getCategoriaSelecionada() +
                              " | Produto: " + produtoSelecionado +
                              " | Quantidade: " + quantidade +
                              " | Preço: R$ " + String.format("%.2f", precoConjunto);

                // Adiciona o item à lista de compras
                adicionarProdutoListaCompras(item);

                // Adiciona o item à lista para registro (não visível)
                listaParaRegistro.add(item);

                // Atualiza o preço total após adicionar o produto ao carrinho
                atualizarPrecoTotal();
            }
        });

        // Dentro do evento do botão "Finalizar Compra" na VendaView:
btnTerminarCompra.addActionListener(e -> {
    // Primeiro, cria um modelo de transações para adicionar no registro
    List<Registro_TransacoesModel> transacoesAdicionadas = new ArrayList<>();
    
    // Passa os dados da listaParaRegistro para o Registro_TransacoesView
    for (String item : listaParaRegistro) {
        String[] partes = item.split(" \\| ");
        
        // Valida se há pelo menos 5 partes (cliente, categoria, produto, quantidade e preço)
        if (partes.length >= 5) {
            try {
                // Extrai os dados do item
                String nomeCliente = partes[0].split(": ")[1];
                String categoriaProduto = partes[1].split(": ")[1];
                String nomeProduto = partes[2].split(": ")[1];
                int quantidade = Integer.parseInt(partes[3].split(": ")[1]);
                
                // Limpeza do valor de preço (remover "R$" e substituir a vírgula por ponto)
                String precoString = partes[4].split(": ")[1].replace("R$ ", "").replace(",", ".");
                double preco = Double.parseDouble(precoString);
                
                // Verifica se os dados extraídos não são nulos ou inválidos
                if (nomeCliente != null && categoriaProduto != null && nomeProduto != null && quantidade > 0 && preco > 0) {
                    // Cria o modelo de transação
                    Registro_TransacoesModel transacao = new Registro_TransacoesModel(nomeCliente, categoriaProduto, nomeProduto, quantidade, preco);
                    
                    // Adiciona a transação à lista temporária
                    transacoesAdicionadas.add(transacao);
                } else {
                    System.out.println("Erro: Dados inválidos para o item: " + item);
                }
            } catch (Exception ex) {
                // Caso haja um erro na conversão ou no formato dos dados, exibe uma mensagem de erro
                System.out.println("Erro ao processar transação: " + item);
            }
        } else {
            System.out.println("Dados incompletos ou mal formatados: " + item);
        }
    }

    // Exibe a mensagem da lista para registro antes de qualquer atualização
    System.out.println("Lista para Registro antes de limpar: " + listaParaRegistro);

    // Agora, adicionamos as transações à view e à tabela
    if (registroTransacoesView != null) {
        // Adiciona as transações na view
        for (Registro_TransacoesModel transacao : transacoesAdicionadas) {
            registroTransacoesView.adicionarTransacao(transacao);
        }

        // Atualiza o modelo da tabela no Registro_TransacoesView
        DefaultTableModel tableModel = registroTransacoesView.getTableModel();

        // Limpa as linhas existentes no modelo
        tableModel.setRowCount(0);

        // Adiciona as novas transações ao modelo da tabela
        for (Registro_TransacoesModel transacao : registroTransacoesView.getTransacoes()) {
            Object[] linha = {
                transacao.getNomeCliente(),
                transacao.getCategoriaProduto(),
                transacao.getNomeProduto(),
                transacao.getQuantidade(),
                transacao.getPreco()
            };
            tableModel.addRow(linha); // Adiciona linha ao modelo
        }

        // Força a interface a ser atualizada (revalida e repinta o painel)
        registroTransacoesView.revalidate();
        registroTransacoesView.repaint();
        
        // Mensagem indicando que a tabela foi atualizada
        System.out.println("Tabela atualizada com sucesso com " + transacoesAdicionadas.size() + " transações.");
    }

    // Limpa a listaParaRegistro após adicionar os dados na tabela
    listaParaRegistro.clear();
    
    // Exibe a mensagem no console indicando que a lista foi limpa
    System.out.println("A lista 'listaParaRegistro' foi limpa.");

    // Limpa a listaCompra para preparar para uma nova compra
    listModel.clear();

    // Exibe uma mensagem de confirmação
    JOptionPane.showMessageDialog(null, 
        "As transações foram registradas com sucesso!", 
        "Confirmação", 
        JOptionPane.INFORMATION_MESSAGE);
});

















        // Carregar categorias ao inicializar a view
        atualizarCategoriasComboBox();

        // Adicionar um listener para atualizar os produtos quando a categoria for alterada
        comboBoxCategoriaProduto.addActionListener(e -> atualizarProdutosComboBox());

        // No método de inicialização da sua view ou onde você configura o comboBoxProduto:
        comboBoxProduto.addActionListener(e -> atualizarPrecoProduto());
        
        // Enviar as informações da listaParaRegistro para a tabela
        enviarParaTabela();

        // Limpar a listaParaRegistro
        listaParaRegistro.clear();
        
        
        // Finalizar a compra com o VendaController
        vendaController.finalizarCompra();
        
    }

    // Método para atualizar o preço do produto selecionado
    private void atualizarPrecoProduto() {
        String produtoSelecionado = (String) comboBoxProduto.getSelectedItem();

        if (produtoSelecionado != null && estoqueController != null) {
            // Obtém o produto através do nome selecionado no comboBox
            EstoqueModel produto = estoqueController.getProdutoPorNome(produtoSelecionado);

            if (produto != null) {
                // Atualiza o JLabel do preço com o preço do produto selecionado
                setPrecoProduto(produto.getPreco());
            }
        }
    }

    // Método para atualizar o JLabel com o preço do produto
    public void setPrecoProduto(double preco) {
        // Atualiza o valor do JLabel com o novo preço (valor_Preco)
        valor_Preco.setText(String.format("R$ %.2f", preco));
    }

    // Método para adicionar um produto à lista de compras
    public void adicionarProdutoListaCompras(String item) {
        listModel.addElement(item); // Adiciona o item ao carrinho

        // Atualiza o preço total após a adição
        atualizarPrecoTotal();  // Chama a função para atualizar o total
    }

    // Método para atualizar o preço total no carrinho
    public void atualizarPrecoTotal() {
        double precoTotal = 0.0;

        // Itera sobre os itens no listModel (cada item contém as informações de preço e quantidade)
        for (int i = 0; i < listModel.size(); i++) {
            String item = listModel.getElementAt(i);

            // Expressão regular para capturar o preço (parte do item no formato "Preço: R$ x.xx")
            Pattern pattern = Pattern.compile("Preço: R\\$ ([\\d,.]+)");
            Matcher matcher = pattern.matcher(item);

            // Se a string for formatada corretamente, extrai o preço do conjunto
            if (matcher.find()) {
                try {
                    // Extrai o preço do item (convertido de string para double)
                    double precoConjunto = Double.parseDouble(matcher.group(1).replace(",", "."));

                    // Soma o preço do conjunto ao preço total acumulado
                    precoTotal += precoConjunto;
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao calcular preço total: " + e.getMessage());
                }
            }
        }

        // Atualiza o valor exibido no label de preço total
        valorPrecoTotal.setText(String.format("R$ %.2f", precoTotal));
    }

    // Método para obter o nome do cliente
    public String getClienteNome() {
        return txtCliente.getText();
    }

    // Método para obter a categoria selecionada
    public String getCategoriaSelecionada() {
        return (String) comboBoxCategoriaProduto.getSelectedItem();
    }

    // Método para obter o produto selecionado
    public String getProdutoSelecionado() {
        return (String) comboBoxProduto.getSelectedItem();
    }

    // Método para obter a quantidade informada
    public int getQuantidade() {
        try {
            return Integer.parseInt(txtQuantidade.getText());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Quantidade inválida.");
            return 0;
        }
    }

    // Método para limpar o carrinho de compras
    public void limparCarrinho() {
        listModel.clear();
        listaParaRegistro.clear();  // Limpa a lista de registro
        atualizarPrecoTotal();
    }

    // Método para atualizar as ComboBoxes de categoria
    private void atualizarCategoriasComboBox() {
        java.util.List<String> categorias = new ArrayList<>(); // Inicializa uma lista vazia por padrão

        try {
            // Obtém as categorias do estoqueController
            if (estoqueController != null) {
                categorias = estoqueController.getCategorias(); // Obtém as categorias do estoqueController
            }
        } catch (Exception e) {
            // Loga o erro caso ocorra uma exceção
            System.err.println("Erro ao obter categorias: " + e.getMessage());
        }

        // Limpa as ComboBoxes antes de adicionar as novas categorias
        limparComboBox();

        // Adiciona as categorias às ComboBox
        for (String categoria : categorias) {
            comboBoxCategoriaProduto.addItem(categoria);
        }

        // Se houver pelo menos uma categoria, atualiza os produtos da primeira categoria
        if (!categorias.isEmpty()) {
            atualizarProdutosComboBox();
        }
    }

    // Método para limpar a ComboBox de categorias
    private void limparComboBox() {
        comboBoxCategoriaProduto.removeAllItems();
    }

    // Método para atualizar os produtos da ComboBox com base na categoria selecionada
    private void atualizarProdutosComboBox() {
        java.util.List<String> produtos = new ArrayList<>(); // Lista para armazenar os produtos

        try {
            String categoriaSelecionada = (String) comboBoxCategoriaProduto.getSelectedItem();
            if (categoriaSelecionada != null && estoqueController != null) {
                java.util.List<EstoqueModel> produtosObtidos = estoqueController.getProdutosPorCategoria(categoriaSelecionada);
                for (EstoqueModel produto : produtosObtidos) {
                    produtos.add(produto.getNomeProduto());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter produtos: " + e.getMessage());
        }

        // Limpa a ComboBox de produtos antes de adicionar os novos produtos
        limparComboBoxProduto();

        // Adiciona os produtos à ComboBox
        for (String produto : produtos) {
            comboBoxProduto.addItem(produto);
        }
    }

    // Método para limpar a ComboBox de produtos
    private void limparComboBoxProduto() {
        comboBoxProduto.removeAllItems();
    }

    public JButton getBtnVoltarMain() {
        return btnVoltarMain;
    }
    
    
    // Método para enviar as informações da listaParaRegistro para a tabela
private void enviarParaTabela() {
    // Cria a lista de transações
    List<Registro_TransacoesModel> transacoes = new ArrayList<>();

    // Converte cada item da listaParaRegistro para um objeto Registro_TransacoesModel
    for (String item : listaParaRegistro) {
        Pattern pattern = Pattern.compile("Cliente: (.+?) \\| Categoria: (.+?) \\| Produto: (.+?) \\| Quantidade: (\\d+) \\| Preço: R\\$ ([\\d,\\.]+)");
        Matcher matcher = pattern.matcher(item);

        if (matcher.find()) {
            String nomeCliente = matcher.group(1);
            String categoriaProduto = matcher.group(2);
            String nomeProduto = matcher.group(3);
            int quantidade = Integer.parseInt(matcher.group(4));
            double preco = Double.parseDouble(matcher.group(5).replace(",", "."));

            // Cria o objeto 'Registro_TransacoesModel' e adiciona à lista
            Registro_TransacoesModel transacao = new Registro_TransacoesModel(nomeCliente, categoriaProduto, nomeProduto, quantidade, preco);
            transacoes.add(transacao);
        }
    }

    // Atualiza a tabela com as transações
    //registroTransacoesView.atualizarTabela(transacoes);
    
    
}





    
    
} 

