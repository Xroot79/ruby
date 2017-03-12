/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Ronaldo
 */
public class Usuario {
    private int id_assistente;
    private int id_entidade;
    private String Nome_assistente;
    private String Login_assistente;
    private String Senha_assistente;
    private String Email_assistente;
    private String Telefone_assistente;
    private String Funçao_assistente;
    private String Entidade_Pertencente;

    public int getId_assistente() {
        return id_assistente;
    }

    public void setId_assistente(int id_assistente) {
        this.id_assistente = id_assistente;
    }

    public int getId_entidade() {
        return id_entidade;
    }

    public void setId_entidade(int id_entidade) {
        this.id_entidade = id_entidade;
    }

    public String getNome_assistente() {
        return Nome_assistente;
    }

    public void setNome_assistente(String Nome_assistente) {
        this.Nome_assistente = Nome_assistente;
    }

    public String getLogin_assistente() {
        return Login_assistente;
    }

    public void setLogin_assistente(String Login_assistente) {
        this.Login_assistente = Login_assistente;
    }

    public String getSenha_assistente() {
        return Senha_assistente;
    }

    public void setSenha_assistente(String Senha_assistente) {
        this.Senha_assistente = Senha_assistente;
    }

    public String getEmail_assistente() {
        return Email_assistente;
    }

    public void setEmail_assistente(String Email_assistente) {
        this.Email_assistente = Email_assistente;
    }

    public String getTelefone_assistente() {
        return Telefone_assistente;
    }

    public void setTelefone_assistente(String Telefone_assistente) {
        this.Telefone_assistente = Telefone_assistente;
    }

    public String getFunçao_assistente() {
        return Funçao_assistente;
    }

    public void setFunçao_assistente(String Funçao_assistente) {
        this.Funçao_assistente = Funçao_assistente;
    }

    public String getEntidade_Pertencente() {
        return Entidade_Pertencente;
    }

    public void setEntidade_Pertencente(String Entidade_Pertencente) {
        this.Entidade_Pertencente = Entidade_Pertencente;
    }

    
    

    
}
