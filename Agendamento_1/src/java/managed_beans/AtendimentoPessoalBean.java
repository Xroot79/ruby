/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao.AtendimentoPessoalDAO;
import dao.Usuario_AssistenteDAO;
import entidades.AtendimentoPessoal;
import entidades.Pessoa;
import entidades.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class AtendimentoPessoalBean implements java.io.Serializable
 {
     private AtendimentoPessoalDAO AtendimentoDao = new AtendimentoPessoalDAO(); 
     private ArrayList<AtendimentoPessoal> listaDeAtendimentos = new ArrayList<>();
     private ArrayList<Pessoa> listaNomes = new ArrayList<>();
     private AtendimentoPessoal atendimentoPessoal = new AtendimentoPessoal();
     private Pessoa pessoa= new Pessoa();
   
     
     public ArrayList<AtendimentoPessoal> getListaAtendimentos() throws SQLException {
        AtendimentoDao = new AtendimentoPessoalDAO();
        listaDeAtendimentos = AtendimentoDao.listaDeAtendimentos();
        return listaDeAtendimentos;
    }
     public ArrayList<Pessoa> getListaNomes() throws SQLException {
        AtendimentoDao = new AtendimentoPessoalDAO();
        listaNomes = AtendimentoDao.listaDeNomes();
        return listaNomes;
    }
     
      
     
     public void salvarAtendimento(AtendimentoPessoal atendimentoPessoal) {
        try {
            new AtendimentoPessoalDAO().iserirAtendimento(atendimentoPessoal);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado com sucesso", ""));

        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar produto", ""));
            Logger.getLogger(AtendimentoPessoalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public AtendimentoPessoal getAtendimentoPessoal() {
        return atendimentoPessoal;
    }

    public void setAtendimentoPessoal(AtendimentoPessoal atendimentoPessoal) {
        this.atendimentoPessoal = atendimentoPessoal;
    }

   
}
