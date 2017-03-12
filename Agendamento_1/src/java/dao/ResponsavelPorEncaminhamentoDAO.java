/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco_de_dados.ConfiguracaoBD;
import entidades.Pessoa;
import entidades.ResponsavelPorEncaminhamento;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResponsavelPorEncaminhamentoDAO {
    
    ResponsavelPorEncaminhamento responsavel= new ResponsavelPorEncaminhamento();
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;

    public boolean inserirResponsavel(ResponsavelPorEncaminhamento responsavel) throws SQLException {
        boolean retorno;
        
            sql = "INSERT INTO responsavel_encaminhamento VALUES(0,?,?,?,?,?)";
            try {
                conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, responsavel.getId_entidade());
                pst.setString(2, responsavel.getNome_responsavel());
                pst.setString(3, responsavel.getFuncao_responsavel());
                pst.setString(4, responsavel.getTelefone_responsavel());
                pst.setString(5, responsavel.getEmail_responsavel());                               
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
   
    public ArrayList<ResponsavelPorEncaminhamento> listaDeResponsavel() throws SQLException {
        ArrayList<ResponsavelPorEncaminhamento> listaDeResponsavel = new ArrayList<>();
        sql = "SELECT * FROM responsavel_encaminhamento ;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                responsavel = new ResponsavelPorEncaminhamento();
                responsavel.setNome_responsavel(rs.getString(3));
                responsavel.setFuncao_responsavel(rs.getString(4));
                responsavel.setTelefone_responsavel(rs.getString(5));
                responsavel.setEmail_responsavel(rs.getString(6));                        
                listaDeResponsavel.add(responsavel);
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
        return listaDeResponsavel;
    }

}
