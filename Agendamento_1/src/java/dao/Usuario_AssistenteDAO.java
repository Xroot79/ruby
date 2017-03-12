/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco_de_dados.ConfiguracaoBD;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsávl por realizar operações de "CRUD" no banco de dados no que
 * se refere a parte dos viveiros
 *
 * @author Hernany
 */
public class Usuario_AssistenteDAO {

    Usuario usuario = new Usuario();

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;

    public boolean iserirUsuario(Usuario usuario) throws SQLException {
        boolean retorno;
        
            sql = "INSERT INTO usuario_assistente VALUES(0,?,?,?,?,?,?,?,?)";
            try {
                conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, usuario.getId_entidade());
                pst.setString(2, usuario.getNome_assistente());
                pst.setString(3, usuario.getLogin_assistente());
                pst.setString(4, usuario.getSenha_assistente());
                pst.setString(5, usuario.getEmail_assistente());
                pst.setString(6, usuario.getTelefone_assistente());
                pst.setString(7, usuario.getFunçao_assistente());
                pst.setString(8, usuario.getEntidade_Pertencente());                                      
                pst.execute();
                retorno = true;
            } catch (Exception ex) {
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
   
    public ArrayList<Usuario> listaDeUsuario() throws SQLException {
        ArrayList<Usuario> listaDeUsuario = new ArrayList<>();
        sql = "SELECT * FROM usuario_assistente ;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setNome_assistente(rs.getString(3));
                usuario.setLogin_assistente(rs.getString(4));
                usuario.setSenha_assistente(rs.getString(5));
                usuario.setEmail_assistente(rs.getString(6));
                usuario.setTelefone_assistente(rs.getString(7));
                usuario.setFunçao_assistente(rs.getString(8));
                usuario.setEntidade_Pertencente(rs.getString(9));
             
                listaDeUsuario.add(usuario);
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
        return listaDeUsuario;
    }

}
