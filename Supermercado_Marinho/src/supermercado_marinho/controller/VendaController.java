package supermercado_marinho.controller;

import supermercado_marinho.model.EstoqueModel;
import supermercado_marinho.model.VendaModel;
import supermercado_marinho.util.Conexao;
import supermercado_marinho.view.VendaView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import supermercado_marinho.model.Registro_TransacoesModel;
import supermercado_marinho.view.Registro_TransacoesView;


public class VendaController {
    private VendaView vendaView;
    private VendaModel vendaModel;
    private Registro_TransacoesView registroTransacoesView;
    private EstoqueController estoqueController; // Adicionei a instância de EstoqueController

    // Construtor
    public VendaController(VendaView vendaView, EstoqueController estoqueController) {
        this.vendaView = vendaView;
        this.vendaModel = new VendaModel();
        this.registroTransacoesView = new Registro_TransacoesView();
        this.estoqueController = estoqueController; // Inicializando o EstoqueController
    }

    // Método para adicionar o produto ao carrinho
    public void adicionarProdutoAoCarrinho() {
        String nomeCliente = vendaView.getClienteNome();
        String categoria = vendaView.getCategoriaSelecionada();
        String nomeProduto = vendaView.getProdutoSelecionado();
        int quantidadeVendida;

        try {
            quantidadeVendida = vendaView.getQuantidade();
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida!");
            return;
        }

        if (nomeCliente.isEmpty() || nomeProduto == null || nomeProduto.isEmpty()) {
            System.out.println("Por favor, preencha o nome do cliente e selecione um produto.");
            return;
        }

        // Aqui, agora, a busca do produto é feita no estoqueController
        EstoqueModel produto = estoqueController.getProdutoPorCategoriaENome(categoria, nomeProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado no estoque.");
            return;
        }

        // Configura os dados da venda no modelo
        vendaModel.setNomeCliente(nomeCliente);
        vendaModel.setProduto(produto);
        vendaModel.setQuantidadeVendida(quantidadeVendida);

        // Verifica a disponibilidade no estoque e realiza a venda
        if (vendaModel.verificarDisponibilidadeEstoque()) {
            vendaModel.realizarVenda();
            vendaView.adicionarProdutoListaCompras(vendaModel.obterDetalhesVenda());
        } else {
            System.out.println("Estoque insuficiente para a quantidade solicitada.");
        }
    }

    // Método para atualizar o estoque no banco de dados
    private void atualizarEstoqueNoBanco(String nomeProduto, int novaQuantidade) {
        Connection con = Conexao.con;
        String sql = "UPDATE Produto SET quantidadeProduto = ? WHERE nomeProduto = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setString(2, nomeProduto);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Estoque atualizado no banco de dados para o produto: " + nomeProduto);
            } else {
                System.out.println("Erro: Produto não encontrado no banco de dados.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o estoque no banco de dados: " + e.getMessage());
        }
    }

    // Método para finalizar a compra
    public void finalizarCompra() {
        String nomeCliente = vendaModel.getNomeCliente();
        EstoqueModel produto = vendaModel.getProduto();
        int quantidadeVendida = vendaModel.getQuantidadeVendida();

        if (produto == null) {
            System.out.println("Nenhum produto adicionado ao carrinho para o cliente " + nomeCliente);
            return;
        }

        int estoqueAtual = produto.getQuantidadeProduto();
        int estoqueRestante = estoqueAtual - quantidadeVendida;

        if (estoqueRestante < 0) {
            System.out.println("Ordem é superior à quantia de produtos no estoque atual. Operação cancelada.");
            return;
        }

        atualizarEstoqueNoBanco(produto.getNomeProduto(), estoqueRestante);

        // Cria o registro da transação e adiciona ao banco de dados
        //Registro_TransacoesModel transacao = new Registro_TransacoesModel();
        //salvarTransacaoNoBanco(transacao);

        // Atualiza a view de transações
        //adicionarTransacaoNaTabela(transacao);
        vendaView.limparCarrinho();
        vendaModel = new VendaModel();
    }

    // Método para salvar a transação no banco de dados
    private void salvarTransacaoNoBanco(Registro_TransacoesModel transacao) {
        Connection con = Conexao.con;
        String sql = "INSERT INTO Transacao (nomeCliente, categoriaProduto, nomeProduto, quantidade, preco) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, transacao.getNomeCliente());
            stmt.setString(2, transacao.getCategoriaProduto());
            stmt.setString(3, transacao.getNomeProduto());
            stmt.setInt(4, transacao.getQuantidade());
            stmt.setDouble(5, transacao.getPreco());

            stmt.executeUpdate();
            System.out.println("Transação registrada no banco de dados.");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar transação no banco de dados: " + e.getMessage());
        }
    }

    // Método para adicionar uma transação diretamente à tabela na view de registros
    private void adicionarTransacaoNaTabela(Registro_TransacoesModel transacao) {
        Object[] linha = {
            transacao.getNomeCliente(),
            transacao.getCategoriaProduto(),
            transacao.getNomeProduto(),
            transacao.getQuantidade(),
            transacao.getPreco()
        };
        registroTransacoesView.getTableModel().addRow(linha); // Adiciona a linha à tabela
    }
}

