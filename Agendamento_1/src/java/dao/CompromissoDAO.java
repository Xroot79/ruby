/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco_de_dados.ConfiguracaoBD;
import entidades.Compromisso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author root
 */
public class CompromissoDAO {

    Compromisso compromisso = new Compromisso();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;

    public boolean inserirCompromisso(Compromisso compromisso) throws SQLException {
        boolean retorno;
        sql = "INSERT INTO compromisso VALUES(0,?,?,?,?,?,?,?,?)";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setString(2, compromisso.getNome());
            pst.setString(3, compromisso.getTipo());
            pst.setString(4, compromisso.getDescricao());
            pst.setString(5, compromisso.getDataEHora());
            pst.setString(6, compromisso.getLocal());
            pst.setString(7, compromisso.getSituacao());
            pst.setString(8, compromisso.getJustificativa());
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

    public ArrayList<Compromisso> getDetalhesCompromisso(String data) throws SQLException, ParseException {
        ArrayList<Compromisso> compromissos = new ArrayList<>();
        Compromisso comp;
        sql = "SELECT * FROM compromisso WHERE data_compromisso = ?;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            pst.setString(1, data);
            rs = pst.executeQuery();
            if (rs.next()) {
                comp = new Compromisso();
                comp.setIdCompromisso(rs.getInt(1));
                comp.setIdEntidade(rs.getInt(2));
                comp.setNome(rs.getString(3));
                comp.setTipo(rs.getString(4));
                comp.setDescricao(rs.getString(5));
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                Date parsedDate = sdf.parse(rs.getString(6));
                SimpleDateFormat print = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                comp.setDataEHora(print.format(parsedDate).concat(" h"));
                comp.setLocal(rs.getString(7));
                comp.setSituacao(rs.getString(8));
                comp.setJustificativa(rs.getString(9));
                compromissos.add(comp);
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
        return compromissos;
    }

    public ArrayList<Compromisso> listaDeCompromissos() throws SQLException {
        ArrayList<Compromisso> compromissos = new ArrayList<>();
        Compromisso comp;
        sql = "SELECT * FROM compromisso;";
        try {
            conexao = DriverManager.getConnection(ConfiguracaoBD.url, ConfiguracaoBD.user, ConfiguracaoBD.password);
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                comp = new Compromisso();
                comp.setIdCompromisso(rs.getInt(1));
                comp.setIdEntidade(rs.getInt(2));
                comp.setNome(rs.getString(3));
                comp.setTipo(rs.getString(4));
                comp.setDescricao(rs.getString(5));
                comp.setDataEHora(rs.getString(6));
                comp.setLocal(rs.getString(7));
                comp.setSituacao(rs.getString(8));
                comp.setJustificativa(rs.getString(9));
                compromissos.add(comp);
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
        return compromissos;
    }

}
