package supermercado_marinho.model;




public class LoginModel {
    
    private String usuario;
    private String senha;
    private int idLogin;


    public LoginModel() {
    }


    public LoginModel(String usuario, String senha, int idLogin) {
        this.usuario = usuario;
        this.senha = senha;
        this.idLogin = idLogin;
    }


    public String getUsuario() {
       return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

  
}

