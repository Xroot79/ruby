/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao.AtendimentoPessoalDAO;
import dao.EntidadeFisicaDAO;
import dao.EntidadeJuridicaDAO;
import dao.Usuario_AssistenteDAO;
import entidades.AtendimentoPessoal;
import entidades.EntidadeFisica;
import entidades.EntidadeJuridica;
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
public class EntidadeFisicaBean implements java.io.Serializable
 {
    private EntidadeFisicaDAO entidadeFisicaDAO = new EntidadeFisicaDAO(); 
     private ArrayList<EntidadeFisica> listaDeEntidadesFisicas = new ArrayList<>();
     private EntidadeFisica entidadeFisica = new EntidadeFisica();

   
     
     public ArrayList<EntidadeFisica> getListaDeEntidadesFis() throws SQLException {
        entidadeFisicaDAO = new EntidadeFisicaDAO();
        listaDeEntidadesFisicas = entidadeFisicaDAO.listaDeEntidadesFisicas();
        return listaDeEntidadesFisicas;
    }
     
     public void salvarEntidadesFis(EntidadeJuridica entidadeJuridica) {
        try {
            new EntidadeFisicaDAO().iserirEntFisica(entidadeFisica);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado com sucesso", ""));

        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar produto", ""));
            Logger.getLogger(EntidadeFisicaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EntidadeFisica getEntidadeFisica() {
        return entidadeFisica;
    }

    public void setEntidadeFisica(EntidadeFisica entidadeFisica) {
        this.entidadeFisica = entidadeFisica;
    }


     
  

   
}
