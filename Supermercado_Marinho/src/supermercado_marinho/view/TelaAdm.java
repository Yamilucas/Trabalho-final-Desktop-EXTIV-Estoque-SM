package supermercado_marinho.view;

import javax.swing.*;
import java.awt.*;

public class TelaAdm extends JPanel {
    private JButton btnGerenciarEstoque;
    private JButton btnRegistroTransacoes;
    private JButton btnVoltarMain;
    private JLabel lblAdm;

    public TelaAdm() {
   
        
        setLayout(new GridBagLayout());

        lblAdm = new JLabel("Tela do Administrador");
        lblAdm.setFont(new Font("Ubuntu", Font.BOLD, 18));
        lblAdm.setHorizontalAlignment(SwingConstants.CENTER);

        btnGerenciarEstoque = new JButton("Gerenciar Estoque");
        btnRegistroTransacoes = new JButton("Registro de Transações");
        btnVoltarMain = new JButton("Voltar ao Principal");

        Dimension buttonSize = new Dimension(200, 40);
        btnGerenciarEstoque.setPreferredSize(buttonSize);
        btnRegistroTransacoes.setPreferredSize(buttonSize);
        btnVoltarMain.setPreferredSize(buttonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); 
        gbc.gridx = 0; 
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(lblAdm, gbc);

        gbc.gridy = 1;
        add(btnGerenciarEstoque, gbc);

        gbc.gridy = 2;
        add(btnRegistroTransacoes, gbc);

        gbc.gridy = 3;
        add(btnVoltarMain, gbc);
    }

    public JButton getBtnVoltarMain() {
        return btnVoltarMain;
    }

    public JButton getBtnGerenciarEstoque() {
        return btnGerenciarEstoque;
    }

    public JButton getBtnRegistroTransacoes() {
        return btnRegistroTransacoes;
    }
}
