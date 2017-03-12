/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco_de_dados.ConfiguracaoBD;
import entidades.Pessoa;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaDAO {
    
    Pessoa pessoa= new Pessoa();
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;

    public boolean inserirPessoa(Pessoa pessoa) throws SQLException {
        boolean retorno;
        
            sql = "INSERT INTO pessoa VALUES(0,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, pessoa.getId_entidade());
                pst.setString(2, pessoa.getNome_pessoa_completo());
                pst.setBytes(3, pessoa.getFoto_pessoa());
                pst.setString(4, pessoa.getApelido_pessoa());
                pst.setString(5, pessoa.getCpf_pessoa());
                pst.setString(6, pessoa.getRg_pessoa());
                pst.setString(7, pessoa.getNome_pai_pessoa());
                pst.setString(8, pessoa.getNome_mae_pessoa());
                pst.setString(9, pessoa.getData_nascimento_pessoa());
                pst.setString(10, pessoa.getTelefone_pessoa());
                pst.setString(11, pessoa.getEmail_pessoa());
                pst.setString(12, pessoa.getEndereco_pessoa());
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
   
     public ArrayList<Pessoa> listaDeNomes() throws SQLException {
        ArrayList<Pessoa> listaDeNome = new ArrayList<>();
        sql = "SELECT nome_completo_pessoa FROM pessoa ;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setNome_pessoa_completo(rs.getString(1));
                listaDeNome.add(pessoa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
    
    public ArrayList<Pessoa> listaDePessoa() throws SQLException {
        ArrayList<Pessoa> listaDePessoa = new ArrayList<>();
        sql = "SELECT * FROM pessoa ;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setNome_pessoa_completo(rs.getString(3));
                pessoa.setApelido_pessoa(rs.getString(5));
                pessoa.setCpf_pessoa( rs.getString(6));
                pessoa.setData_nascimento_pessoa(rs.getString(10));
                pessoa.setTelefone_pessoa(rs.getString(11));
                pessoa.setEmail_pessoa(rs.getString(12));
                pessoa.setEndereco_pessoa(rs.getString(13));
             
                listaDePessoa.add(pessoa);
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
        return listaDePessoa;
    }

}
