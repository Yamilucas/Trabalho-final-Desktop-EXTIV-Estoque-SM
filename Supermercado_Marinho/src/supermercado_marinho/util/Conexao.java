package supermercado_marinho.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    
    public static Connection con;
    
    public static void conectar() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String password = "dadinho";
            String url = "jdbc:mysql://localhost:3306/MercadoMysql";
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
        }catch(ClassNotFoundException e){
            System.out.println("Driver não encontrado! "+ e.getMessage());
        }catch(SQLException e){
            System.out.println("Erro na conexão com banco! "+ e.getMessage());
        }
    }
    
    
    public static void desconectar(){
        try{
            con.close();
        }catch(SQLException e){
            System.out.println("Erro ao desconectar! "+ e.getMessage());
        }
    }
    
}