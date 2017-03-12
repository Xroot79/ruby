package managed_beans;

import dao.UsuarioAdmDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import util.ControleSessao;

/**
 *
 * @author Hernany
 */
@ManagedBean
@ViewScoped
public class Login {

    private String login_usuario;
    private String senha_usuario;
    private Object usuarioGenerico;

    public void logar(String login_usuario, String senha_usuario) {
        try {
            if ((usuarioGenerico = new UsuarioAdmDAO().verificaUsuario(login_usuario, senha_usuario)) != null) {
                ControleSessao.setParam("usuario", usuarioGenerico);
                ControleSessao.setParam("tipoUsuario", "adm");
                FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e/ou senha incorreto(s)", ""));
            }
        } catch (SQLException | IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao efetuar login", ""));
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sair() {
        try {
            usuarioGenerico = null;
            ControleSessao.remove("tipoUsuario");
            ControleSessao.remove("usuario");
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

}
