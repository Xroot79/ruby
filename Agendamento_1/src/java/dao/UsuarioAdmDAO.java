package dao;

import banco_de_dados.ConfiguracaoBD;
import java.sql.Connection;
import java.sql.ResultSet;
import entidades.UsuarioAdm;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsávl por realizar operações de "CRUD" no banco de dados no que
 * se refere a parte dos usuários
 *
 * @author Hernany
 */
public class UsuarioAdmDAO {

    UsuarioAdm usuarioAdm;

    ConfiguracaoBD bd = new ConfiguracaoBD();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String sql;

    /**
     * Método responsável por inserir um novo usário na basa de dados
     *
     * @param usuario
     * @return um boolean que indentifica se o usuário foi inserido ou não
     * @throws java.sql.SQLException
     */
    public boolean insereUsuario(UsuarioAdm usuario) throws SQLException {
        boolean retorno;
        sql = "INSERT INTO usuario_admin(id_adm,login_adm,senha_adm) VALUES(0,?,?)";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuarioAdm.getLogin_adm());
            pst.setString(2, usuarioAdm.getSenha_adm());
            pst.execute();
            retorno = true;
        } catch (SQLException ex) {
            retorno = false;
            Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void alterarLoginESenha(int id_adm, String login_usuario, String senha_usuario) throws SQLException {
        sql = "UPDATE  usuario_admin SET nome_adm = ?, senha_adm = ? WHERE id_adm = ?";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login_usuario);
            pst.setString(2, senha_usuario);
            pst.setInt(3, id_adm);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    /**
     * @param login_usuario
     * @param senha_usuario
     * @return um boolean que define se o usuário em questão existe ou não
     * dentro da base de dados
     * @throws java.sql.SQLException
     */
    public UsuarioAdm verificaUsuario(String login_usuario, String senha_usuario) throws SQLException {
        sql = "SELECT * FROM usuario_admin WHERE login_adm = ? AND senha_adm = ?";
        try {
            conexao = bd.pegaconexao();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login_usuario);
            pst.setString(2, senha_usuario);
            rs = pst.executeQuery();
            if (rs.next()) {
                usuarioAdm = new UsuarioAdm();
                usuarioAdm.setId_adm(rs.getInt(1));
                usuarioAdm.setNome_adm(rs.getString(2));
                usuarioAdm.setLogin_adm(rs.getString(3));
                usuarioAdm.setSenha_adm(rs.getString(4));
                usuarioAdm.setEmail_adm(rs.getString(5));
                usuarioAdm.setTelefone_adm(rs.getString(6));
                return usuarioAdm;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return null;
    }

    /**
     * Método responsável por listar os usuários
     *
     * @param id_adm
     * @return um ArrayList(lista) dos usuário existentes na base de dados
     * @throws java.sql.SQLException
     */
    public ArrayList<UsuarioAdm> listaInformacoesUsuarioAdm(int id_adm) throws SQLException {
        ArrayList<UsuarioAdm> listaDeUsuarios = new ArrayList<>();
        sql = "SELECT * FROM usuario_admin WHERE id_adm =  ?";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id_adm);
            rs = pst.executeQuery();
            while (rs.next()) {
                usuarioAdm = new UsuarioAdm();
                usuarioAdm.setId_adm(rs.getInt(1));
                usuarioAdm.setNome_adm(rs.getString(2));
                usuarioAdm.setLogin_adm(rs.getString(3));
                usuarioAdm.setSenha_adm(rs.getString(4));
                usuarioAdm.setEmail_adm(rs.getString(5));
                usuarioAdm.setTelefone_adm(rs.getString(6));
                listaDeUsuarios.add(usuarioAdm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return listaDeUsuarios;
    }
}
