package gasogen.com.br.gasogen.modelo;

import java.io.Serializable;

/**
 * Created by Dell on 12/11/2016.
 */

public class Usuario implements Serializable {

    private Long id;
    private String login;
    private String senha;
    private String nome;
    private String email;
    private String telefone;

    public Usuario() {

    }

    public Usuario(String login, String senha, String nome, String email, String telefone) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
