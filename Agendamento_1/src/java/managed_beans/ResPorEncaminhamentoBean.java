/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao.ResponsavelPorEncaminhamentoDAO;
import entidades.ResponsavelPorEncaminhamento;
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
public class ResPorEncaminhamentoBean implements java.io.Serializable {

    private ResponsavelPorEncaminhamentoDAO responsavelDao = new ResponsavelPorEncaminhamentoDAO();
    private ArrayList<ResponsavelPorEncaminhamento> listaDeResponsavel = new ArrayList<>();
    private ResponsavelPorEncaminhamento responsavel = new ResponsavelPorEncaminhamento();

    public ArrayList<ResponsavelPorEncaminhamento> getListaAtendimentos() throws SQLException {
        responsavelDao = new ResponsavelPorEncaminhamentoDAO();
        listaDeResponsavel = responsavelDao.listaDeResponsavel();
        return listaDeResponsavel;
    }

    public void salvarResponsavel(ResponsavelPorEncaminhamento responsavelPorEncaminhamento) {
        try {
            new ResponsavelPorEncaminhamentoDAO().inserirResponsavel(responsavelPorEncaminhamento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado com sucesso", ""));

        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar produto", ""));
            Logger.getLogger(ResPorEncaminhamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResponsavelPorEncaminhamento getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelPorEncaminhamento responsavel) {
        this.responsavel = responsavel;
    }

}
