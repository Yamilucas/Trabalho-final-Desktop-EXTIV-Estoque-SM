package supermercado_marinho.view;

import supermercado_marinho.model.Registro_TransacoesModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Registro_TransacoesView extends JPanel {
    private JLabel lblTitulo;
    private JTable tabelaTransacoes;
    private JButton btnVoltarMain;
    private DefaultTableModel tableModel;

    // Lista de transações (armazenada na memória, sem banco de dados)
    private List<Registro_TransacoesModel> transacoes;

    public Registro_TransacoesView() {
        setLayout(new BorderLayout());

        // Inicializa a lista de transações
        transacoes = new ArrayList<>();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        lblTitulo = new JLabel("Registro de Transações", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Ubuntu", Font.BOLD, 24));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // Definição das colunas da tabela
        String[] colunas = {"Cliente", "Categoria do Produto", "Produto", "Quantidade", "Preço"};

        // Cria o modelo de tabela com as colunas
        tableModel = new DefaultTableModel(colunas, 0);  // Inicializa com 0 linhas

        // Cria a JTable com o modelo de dados
        tabelaTransacoes = new JTable(tableModel);
        
        // Criando e configurando o JScrollPane para a tabela
        JScrollPane scrollPane = new JScrollPane(tabelaTransacoes);
       

        
        tabelaTransacoes.setFillsViewportHeight(true);  // Garante que a tabela ocupe todo o espaço disponível

        // Adiciona a tabela em um painel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Criação do painel de navegação com o botão "Voltar ao Painel Principal"
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
    }

    public JButton getBtnVoltarMain() {
        return btnVoltarMain;
    }
    
    // Método público para acessar a lista de transações
public List<Registro_TransacoesModel> getTransacoes() {
    return transacoes;
}


    public DefaultTableModel getTableModel() {
    return tableModel;  // Retorna o modelo de dados da tabela
}


 // Método para adicionar transações a partir de uma lista de strings (listaParaRegistro)
// Método para adicionar transações a partir de uma lista de strings (listaParaRegistro)
public void adicionarTransacoesDeLista(List<String> listaParaRegistro) {
    // Limpa a lista de transações antes de adicionar novos dados
    transacoes.clear();

    // Percorre a lista de strings (listaParaRegistro)
    for (String item : listaParaRegistro) {
        // Divide os dados do item da lista e cria um objeto Registro_TransacoesModel
        String[] partes = item.split(" \\| ");
        String nomeCliente = partes[0].split(": ")[1];
        String categoriaProduto = partes[1].split(": ")[1];
        String nomeProduto = partes[2].split(": ")[1];
        int quantidade = Integer.parseInt(partes[3].split(": ")[1]);
        
        // Limpeza do valor de preço (remover "R$" e substituir a vírgula por ponto)
        String precoString = partes[4].split(": ")[1].replace("R$ ", "").replace(",", ".");
        double preco = Double.parseDouble(precoString);
        
        // Cria o modelo de transação
        Registro_TransacoesModel transacao = new Registro_TransacoesModel(nomeCliente, categoriaProduto, nomeProduto, quantidade, preco);
        
        // Adiciona a transação à lista interna
        transacoes.add(transacao);
    }

    // Atualiza a tabela após adicionar os dados
    atualizarTabela();

    // Atualiza o painel que contém a tabela
    SwingUtilities.invokeLater(() -> {
        this.revalidate(); // Recalcula o layout do painel
        this.repaint(); // Redibuja o painel
    });
}


public void adicionarTransacao(Registro_TransacoesModel transacao) {
    // Adiciona a transação na lista interna
    transacoes.add(transacao);

    // Atualiza a tabela com os dados novos
    atualizarTabela();
}





 // Método para atualizar a tabela com os dados da lista de transações
public void atualizarTabela() {
    // Cria um novo modelo de tabela
    String[] colunas = {"Cliente", "Categoria do Produto", "Produto", "Quantidade", "Preço"};
    DefaultTableModel novoTableModel = new DefaultTableModel(colunas, 0);
    
    // Adiciona as transações ao novo modelo
    for (Registro_TransacoesModel transacao : transacoes) {
        Object[] linha = {
            transacao.getNomeCliente(),
            transacao.getCategoriaProduto(),
            transacao.getNomeProduto(),
            transacao.getQuantidade(),
            transacao.getPreco()
        };
        novoTableModel.addRow(linha); // Adiciona linha ao modelo
    }

    // Remove a tabela antiga do painel
    this.remove(tabelaTransacoes);

    // Cria uma nova JTable com o novo modelo de dados
    tabelaTransacoes = new JTable(novoTableModel);

    // Adiciona a nova JTable ao JScrollPane
    JScrollPane scrollPane = new JScrollPane(tabelaTransacoes);
    tabelaTransacoes.setFillsViewportHeight(true);

    // Adiciona o JScrollPane com a nova JTable ao painel
    this.add(scrollPane, BorderLayout.CENTER); // Coloca no painel

    // Força a interface a ser atualizada (revalida e repinta o painel)
    this.revalidate();
    this.repaint();

    // Exibe uma mensagem no console
    System.out.println("Tabela atualizada com sucesso com " + transacoes.size() + " transações.");
}



    
    
}

