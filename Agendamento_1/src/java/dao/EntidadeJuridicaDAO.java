/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import banco_de_dados.ConfiguracaoBD;
import entidades.AtendimentoPessoal;
import entidades.EntidadeFisica;
import entidades.EntidadeJuridica;
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
public class EntidadeJuridicaDAO {

    EntidadeJuridica entidadeJuridica = new EntidadeJuridica();

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;

    public boolean iserirEntJuridica(EntidadeJuridica entidadeJuridica) throws SQLException {
        boolean retorno;
        
            sql = "INSERT INTO entidade_juridica VALUES(0,?,?,?,?,?,?,?,?)";
            try {
                conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
                pst = conexao.prepareStatement(sql);                              
                pst.setString(1, entidadeJuridica.getNome_entidade());
                pst.setString(2, entidadeJuridica.getTipo_entidade());
                pst.setString(3, entidadeJuridica.getLogin_entidade());
                pst.setString(4, entidadeJuridica.getSenha_entidade());
                pst.setString(5, entidadeJuridica.getCnpj_entidade());
                pst.setString(6, entidadeJuridica.getEndereco_entidade());
                pst.setString(7, entidadeJuridica.getTelefone_entidade());
                pst.setString(8, entidadeJuridica.getEmail_entidade());                
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
   
    public ArrayList<EntidadeJuridica> listaDeEntidadesJuridicas() throws SQLException {
        ArrayList<EntidadeJuridica> listaDeEntidadesJuridicas = new ArrayList<>();
        sql = "SELECT * FROM entidade_juridica ;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                entidadeJuridica = new EntidadeJuridica();
                entidadeJuridica.setNome_entidade(rs.getString(2));
                entidadeJuridica.setTipo_entidade(rs.getString(3));
                entidadeJuridica.setLogin_entidade(rs.getString(4));
                entidadeJuridica.setSenha_entidade(rs.getString(5));
                entidadeJuridica.setCnpj_entidade(rs.getString(6));
                entidadeJuridica.setEndereco_entidade(rs.getString(7));
                entidadeJuridica.setTelefone_entidade(rs.getString(8));
                entidadeJuridica.setEmail_entidade(rs.getString(9));
                
                               
                listaDeEntidadesJuridicas.add(entidadeJuridica);
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
        return listaDeEntidadesJuridicas;
    }

}
