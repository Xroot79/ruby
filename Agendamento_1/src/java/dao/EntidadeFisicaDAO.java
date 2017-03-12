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
public class EntidadeFisicaDAO {

    EntidadeFisica entidadeFisica = new EntidadeFisica();

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;

    public boolean iserirEntFisica(EntidadeFisica entidadeFisica) throws SQLException {
        boolean retorno;
        
            sql = "INSERT INTO entidade_fisica VALUES(0,?,?,?,?,?,?,?,?)";
            try {
                conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
                pst = conexao.prepareStatement(sql);                              
                pst.setString(1, entidadeFisica.getNome_entidade());
                pst.setString(2, entidadeFisica.getTipo_entidade());
                pst.setString(3, entidadeFisica.getLogin_entidade());
                pst.setString(4, entidadeFisica.getSenha_entidade());
                pst.setString(5, entidadeFisica.getCpf_entidade());
                pst.setString(6, entidadeFisica.getEndereco_entidade());
                pst.setString(7, entidadeFisica.getEmail_entidade());  
                pst.setString(8, entidadeFisica.getTelefone_entidade());                           
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
   
    public ArrayList<EntidadeFisica> listaDeEntidadesFisicas() throws SQLException {
        ArrayList<EntidadeFisica> listaDeEntidadesFisicas = new ArrayList<>();
        sql = "SELECT * FROM entidade_fisica ;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                entidadeFisica = new EntidadeFisica();
                entidadeFisica.setNome_entidade(rs.getString(2));
                entidadeFisica.setTipo_entidade(rs.getString(3));
                entidadeFisica.setLogin_entidade(rs.getString(4));
                entidadeFisica.setSenha_entidade(rs.getString(5));
                entidadeFisica.setCpf_entidade(rs.getString(6));
                entidadeFisica.setEndereco_entidade(rs.getString(7));
                entidadeFisica.setEmail_entidade(rs.getString(8));
                entidadeFisica.setTelefone_entidade(rs.getString(9));                                           
                listaDeEntidadesFisicas.add(entidadeFisica);
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
        return listaDeEntidadesFisicas;
    }

}
