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
public class AtendimentoPessoal implements java.io.Serializable
{
    private int id_atendimento;
    private int id_entidade;
    private String nome_atendimento;
    private String encaminhamento;
    private String retorno;
    private String situacao;
    private String solicitacao_atendimento;

    public int getId_atendimento() {
        return id_atendimento;
    }

    public void setId_atendimento(int id_atendimento) {
        this.id_atendimento = id_atendimento;
    }

    public int getId_entidade() {
        return id_entidade;
    }

    public void setId_entidade(int id_entidade) {
        this.id_entidade = id_entidade;
    }

    public String getNome_atendimento() {
        return nome_atendimento;
    }

    public void setNome_atendimento(String nome_atendimento) {
        this.nome_atendimento = nome_atendimento;
    }

    public String getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(String encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSolicitacao_atendimento() {
        return solicitacao_atendimento;
    }

    public void setSolicitacao_atendimento(String solicitacao_atendimento) {
        this.solicitacao_atendimento = solicitacao_atendimento;
    }
   
    

 
}
