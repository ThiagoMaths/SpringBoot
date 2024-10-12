package curso_springboot.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Login extends Pessoa{

   private String login;
   private String senha;
    @Column(nullable = false)
    private String dtype ;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

}

