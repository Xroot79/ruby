/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco_de_dados.ConfiguracaoBD;
import entidades.AtendimentoPessoal;
import entidades.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsávl por realizar operações de "CRUD" no banco de dados no que
 * se refere a parte dos viveiros
 *
 * @author Hernany
 */
public class AtendimentoPessoalDAO {

    AtendimentoPessoal atendimentoPessoal = new AtendimentoPessoal();
    Pessoa pessoa = new Pessoa();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;

    public boolean iserirAtendimento(AtendimentoPessoal atendimentoPessoal) throws SQLException {
        boolean retorno;
        
            sql = "INSERT INTO atendimento_pessoal VALUES(0,?,?,?,?,?,?)";
            try {
                conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
                pst = conexao.prepareStatement(sql);
                
                pst.setInt(1, atendimentoPessoal.getId_entidade());
                pst.setString(4, atendimentoPessoal.getEncaminhamento());
                pst.setString(2, atendimentoPessoal.getNome_atendimento());
                pst.setString(6, atendimentoPessoal.getRetorno());
                pst.setString(5, atendimentoPessoal.getSituacao());
                pst.setString(3, atendimentoPessoal.getSolicitacao_atendimento());                                                      
                pst.execute();
                retorno = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                retorno = false;
             
            } finally {

                if (conexao != null) {
                    conexao.close();
                }

                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
        return retorno;

    }

    /**
     * Método responsável por fazer a verificação da existência de viveiros com
     * números repetidos
     *
     * @param numeroDoViveiro
     * @param idDoUsuario
     * @return um boolean que define se já existe ou não um viveiro com a
     * presente numeração
     * @throws java.sql.SQLException
     */
   
    public ArrayList<AtendimentoPessoal> listaDeAtendimentos() throws SQLException {
        ArrayList<AtendimentoPessoal> listaDeAtendimentos = new ArrayList<>();
        sql = "SELECT * FROM atendimento_pessoal ;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                atendimentoPessoal = new AtendimentoPessoal();
                atendimentoPessoal.setEncaminhamento(rs.getString(1));
                atendimentoPessoal.setNome_atendimento(rs.getString(4));
                atendimentoPessoal.setRetorno(rs.getString(5));
                atendimentoPessoal.setSituacao(rs.getString(6));
                atendimentoPessoal.setSolicitacao_atendimento(rs.getString(7));                     
                listaDeAtendimentos.add(atendimentoPessoal);
            }
        } catch (SQLException ex) {
            
        } finally {

            if (conexao != null) {
                conexao.close();
            }

            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return listaDeAtendimentos;
    }
    
     public ArrayList<Pessoa> listaDeNomes() throws SQLException {
        ArrayList<Pessoa> listaDeNome = new ArrayList<>();
        sql = "SELECT nome_completo_pessoa FROM pessoa ;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setNome_pessoa_completo(rs.getString(3));
                listaDeNome.add(pessoa);
            }
        } catch (SQLException ex) {
            
        } finally {

            if (conexao != null) {
                conexao.close();
            }

            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return listaDeNome;
    }

}
