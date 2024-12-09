package supermercado_marinho.view;

import javax.swing.*;
import java.awt.*;
import supermercado_marinho.controller.LoginController;
import supermercado_marinho.model.LoginModel;

public class LoginView extends JPanel {
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JTextField txtUsuario; // Correção da variável para a convenção correta
    private JLabel lblSenha;
    private JPasswordField txtSenha; // Correção da variável para a convenção correta
    private JButton btnLogin;
    private JButton btnVoltarMain;

    private Principal principal; 

    public LoginView(Principal principal) {
        this.principal = principal;
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblTitulo = new JLabel("Login", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Ubuntu", Font.BOLD, 24));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        lblUsuario = new JLabel("Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(lblUsuario, gbc);

        txtUsuario = new JTextField(20); // Corrigido para txtUsuario
        gbc.gridx = 1;
        centerPanel.add(txtUsuario, gbc);

        lblSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(lblSenha, gbc);

        txtSenha = new JPasswordField(20); // Corrigido para txtSenha
        gbc.gridx = 1;
        centerPanel.add(txtSenha, gbc);

        btnLogin = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(btnLogin, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

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

        // Ação do botão de login
        btnLogin.addActionListener(e -> validarLogin());
    }

    private void validarLogin() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
        LoginModel loginModel = new LoginModel(usuario, senha, 0); // idLogin pode ser gerenciado conforme necessário

        // Chama o método verificarLogin do LoginController
        boolean sucesso = LoginController.verificarLogin(loginModel);
        if (sucesso) {
            principal.mostrarTelaAdm(); 
        } else {
            JOptionPane.showMessageDialog(this, "Acesso Negado. Erro no Usuário e/ou Senha.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JButton getBtnVoltarMain() {
        return btnVoltarMain;
    }
}