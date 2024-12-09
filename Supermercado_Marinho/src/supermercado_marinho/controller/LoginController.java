package supermercado_marinho.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import supermercado_marinho.model.LoginModel;
import supermercado_marinho.util.Conexao;

public class LoginController {

    // Método para verificar o login do usuário no sistema
    public static boolean verificarLogin(LoginModel login) {
        String sql = "SELECT * FROM login WHERE usuario = ? AND senha = ?";
        Connection connection = Conexao.con;
        try (
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // Definindo os parâmetros do PreparedStatement com os atributos do LoginModel
            statement.setString(1, login.getUsuario());
            statement.setString(2, login.getSenha());

            try (ResultSet resultSet = statement.executeQuery()) {
                // Retorna true se as credenciais forem encontradas
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para alterar a senha do usuário
    public static boolean alterarSenha(LoginModel login, String novaSenha) {
        String sql = "UPDATE login SET senha = ? WHERE usuario = ?";

        try (Connection connection = Conexao.con;
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // Define os parâmetros com o novo valor de senha e o usuário do LoginModel
            statement.setString(1, novaSenha);
            statement.setString(2, login.getUsuario());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;  // Retorna true se a senha foi alterada com sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}