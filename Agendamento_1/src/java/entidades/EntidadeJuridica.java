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
public class EntidadeJuridica {
    
    private int id_entidade;
    private String nome_entidade;
    private String tipo_entidade;
    private String login_entidade;
    private String senha_entidade;
    private String cnpj_entidade;
    private String endereco_entidade;
    private String email_entidade  ;  
    private String telefone_entidade;

    public String getCnpj_entidade() {
        return cnpj_entidade;
    }

    public void setCnpj_entidade(String cnpj_entidade) {
        this.cnpj_entidade = cnpj_entidade;
    }

    
    
    public int getId_entidade() {
        return id_entidade;
    }

    public void setId_entidade(int id_entidade) {
        this.id_entidade = id_entidade;
    }

    public String getNome_entidade() {
        return nome_entidade;
    }

    public void setNome_entidade(String nome_entidade) {
        this.nome_entidade = nome_entidade;
    }

    public String getTipo_entidade() {
        return tipo_entidade;
    }

    public void setTipo_entidade(String tipo_entidade) {
        this.tipo_entidade = tipo_entidade;
    }

    public String getLogin_entidade() {
        return login_entidade;
    }

    public void setLogin_entidade(String login_entidade) {
        this.login_entidade = login_entidade;
    }

    public String getSenha_entidade() {
        return senha_entidade;
    }

    public void setSenha_entidade(String senha_entidade) {
        this.senha_entidade = senha_entidade;
    }


    public String getEndereco_entidade() {
        return endereco_entidade;
    }

    public void setEndereco_entidade(String endereco_entidade) {
        this.endereco_entidade = endereco_entidade;
    }

    public String getEmail_entidade() {
        return email_entidade;
    }

    public void setEmail_entidade(String email_entidade) {
        this.email_entidade = email_entidade;
    }

    public String getTelefone_entidade() {
        return telefone_entidade;
    }

    public void setTelefone_entidade(String telefone_entidade) {
        this.telefone_entidade = telefone_entidade;
    }
    
    
    
}
