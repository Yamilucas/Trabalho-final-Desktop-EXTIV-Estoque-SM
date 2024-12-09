package supermercado_marinho.view;

import javax.swing.*;
import java.awt.*;
import supermercado_marinho.util.Conexao;

public class Principal extends JFrame {
    private JButton btnSimularVenda;
    private JButton btnLogin;
    private JButton btnPesquisarEstoque;
    private JPanel mainPanel;
    private EstoqueView estoqueView;
    private VendaView vendaView;
    private LoginView loginView;
    private Registro_TransacoesView registroTransacoesView;
    private PesquisaEstoqueView pesquisaEstoqueView;
    private TelaAdm telaAdm;
    private JLabel lblSM;

    public Principal() {
        Conexao.conectar();
        setTitle("Supermercado Marinho");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new GridBagLayout());
        estoqueView = new EstoqueView();
        vendaView = new VendaView();
        telaAdm = new TelaAdm();
        loginView = new LoginView(this);
        registroTransacoesView = new Registro_TransacoesView();
        pesquisaEstoqueView = new PesquisaEstoqueView(); // Inicializando nova view

        lblSM = new JLabel("Tela Principal");
        lblSM.setFont(new Font("Ubuntu", Font.BOLD, 18));
        lblSM.setHorizontalAlignment(SwingConstants.CENTER);

        btnSimularVenda = new JButton("Simular Venda");
        btnLogin = new JButton("Login");
        btnPesquisarEstoque = new JButton("Pesquisar Estoque"); // Novo botão

        Dimension buttonSize = new Dimension(200, 40);
        btnSimularVenda.setPreferredSize(buttonSize);
        btnLogin.setPreferredSize(buttonSize);
        btnPesquisarEstoque.setPreferredSize(buttonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(lblSM, gbc);

        gbc.gridy = 1;
        mainPanel.add(btnLogin, gbc);

        gbc.gridy = 2;
        mainPanel.add(btnSimularVenda, gbc);

        gbc.gridy = 3;
        mainPanel.add(btnPesquisarEstoque, gbc); // Adiciona o botão ao painel principal

        setLayout(new CardLayout());
        add(mainPanel, "mainPanel");
        add(estoqueView, "estoqueView");
        add(vendaView, "vendaView");
        add(loginView, "loginView");
        add(registroTransacoesView, "registroTransacoesView");
        add(telaAdm, "telaAdm");
        add(pesquisaEstoqueView, "pesquisaEstoqueView"); // Adiciona nova view

        // Ações dos botões
        btnSimularVenda.addActionListener(e -> mostrarVendaView());
        btnLogin.addActionListener(e -> mostrarLoginView());
        btnPesquisarEstoque.addActionListener(e -> mostrarPesquisaEstoqueView());

        estoqueView.getBtnVoltarMain().addActionListener(e -> mostrarMainPanel());
        vendaView.getBtnVoltarMain().addActionListener(e -> mostrarMainPanel());
        loginView.getBtnVoltarMain().addActionListener(e -> mostrarMainPanel());
        registroTransacoesView.getBtnVoltarMain().addActionListener(e -> mostrarMainPanel());

        telaAdm.getBtnVoltarMain().addActionListener(e -> mostrarMainPanel());
        telaAdm.getBtnGerenciarEstoque().addActionListener(e -> mostrarEstoqueView());
        telaAdm.getBtnRegistroTransacoes().addActionListener(e -> mostrarRegistroTransacoesView());
        pesquisaEstoqueView.getBtnVoltarMain().addActionListener(e -> mostrarMainPanel());
    }

    // Métodos para mostrar os diferentes painéis
    public void mostrarMainPanel() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "mainPanel");
    }

    public void mostrarEstoqueView() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "estoqueView");
    }

    public void mostrarVendaView() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "vendaView");
    }

    public void mostrarLoginView() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "loginView");
    }

    public void mostrarRegistroTransacoesView() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "registroTransacoesView");
    }

    public void mostrarTelaAdm() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "telaAdm");
    }

    public void mostrarPesquisaEstoqueView() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "pesquisaEstoqueView");
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.setVisible(true);
    
        
        PesquisaEstoqueView pesquisaEstoqueView = new PesquisaEstoqueView();
        EstoqueView estoqueView = new EstoqueView();

        ComboBoxMediator mediator = new ComboBoxMediator(pesquisaEstoqueView, estoqueView);

        
        //pesquisaEstoqueView.addComboBoxListener(mediator);
        //estoqueView.addComboBoxListener(mediator);

        // Adicione as views ao seu JFrame ou JPanel principal
    }
}

