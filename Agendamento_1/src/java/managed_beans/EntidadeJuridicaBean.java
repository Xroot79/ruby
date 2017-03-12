/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao.AtendimentoPessoalDAO;
import dao.EntidadeJuridicaDAO;
import dao.Usuario_AssistenteDAO;
import entidades.AtendimentoPessoal;
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
public class EntidadeJuridicaBean implements java.io.Serializable
 {
    private EntidadeJuridicaDAO entidadeJuridicaDAO = new EntidadeJuridicaDAO(); 
     private ArrayList<EntidadeJuridica> listaDeEntidadesJuridicas = new ArrayList<>();
     private EntidadeJuridica entidadeJuridica = new EntidadeJuridica();

   
     
     public ArrayList<EntidadeJuridica> getListaEntidadesJus() throws SQLException {
        entidadeJuridicaDAO = new EntidadeJuridicaDAO();
        listaDeEntidadesJuridicas = entidadeJuridicaDAO.listaDeEntidadesJuridicas();
        return listaDeEntidadesJuridicas;
    }
     
     public void salvarEntidadesJus(EntidadeJuridica entidadeJuridica) {
        try {
            new EntidadeJuridicaDAO().iserirEntJuridica(entidadeJuridica);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado com sucesso", ""));

        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar produto", ""));
            Logger.getLogger(EntidadeJuridicaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EntidadeJuridica getEntidadeJuridica() {
        return entidadeJuridica;
    }

    public void setEntidadeJuridica(EntidadeJuridica entidadeJuridica) {
        this.entidadeJuridica = entidadeJuridica;
    }
     
  

   
}
