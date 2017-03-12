/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao.AtendimentoPessoalDAO;
import dao.PessoaDAO;
import dao.Usuario_AssistenteDAO;
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
public class PessoaBean {
     private PessoaDAO pessoaDAO = new PessoaDAO(); 
     private ArrayList<Pessoa> listaDePessoas = new ArrayList<>();
     private ArrayList<Pessoa> listaDeNomes = new ArrayList<>();
     private Pessoa pessoa = new Pessoa();

     
     public ArrayList<Pessoa> getListaPessoas() throws SQLException {
        pessoaDAO = new PessoaDAO();
        listaDePessoas = pessoaDAO.listaDePessoa();
        return listaDePessoas;
    }
     
       public ArrayList<Pessoa> getListaNomes() throws SQLException {
        pessoaDAO = new PessoaDAO();
        listaDeNomes = pessoaDAO.listaDeNomes();
        return listaDeNomes;
    }
     
     
     public void salvarPessoa(Pessoa pessoa) {
        try {
            new PessoaDAO().inserirPessoa(pessoa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado com sucesso", ""));

        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar produto", ""));
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
     
     
   
}
