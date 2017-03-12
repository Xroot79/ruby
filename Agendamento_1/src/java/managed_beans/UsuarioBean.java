/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao.Usuario_AssistenteDAO;
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
public class UsuarioBean {
    private Usuario_AssistenteDAO usuarioDao = new Usuario_AssistenteDAO(); 
     private ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();
     private Usuario usuario = new Usuario();

     
     public ArrayList<Usuario> getListaUsuarios() throws SQLException {
        usuarioDao = new Usuario_AssistenteDAO();
        listaDeUsuarios = usuarioDao.listaDeUsuario();
        return listaDeUsuarios;
    }
     
     public void salvarProduto(Usuario usuario) {
        try {
            new Usuario_AssistenteDAO().iserirUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado com sucesso", ""));

        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar produto", ""));
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
