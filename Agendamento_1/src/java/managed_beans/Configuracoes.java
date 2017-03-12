package managed_beans;

import entidades.InformacoesDaContaUsuario;
import entidades.InformacoesPessoaisUsuario;
import entidades.UsuarioAdm;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import util.ControleSessao;

/**
 *
 * @author Hernany
 */
@ManagedBean
public class Configuracoes {

    UsuarioAdm usuarioAdm = new UsuarioAdm();
    InformacoesDaContaUsuario informacoesDaContaUsuario = new InformacoesDaContaUsuario();
    InformacoesPessoaisUsuario informacoesPessoaisUsuario = new InformacoesPessoaisUsuario();

    public ArrayList<InformacoesDaContaUsuario> listaInformacoesDaConta() {
        ArrayList<InformacoesDaContaUsuario> listaInformacoes = new ArrayList<>();
        if (ControleSessao.getParam("tipoUsuario").equals("adm")) {
            informacoesDaContaUsuario.setLogin_usuario(((UsuarioAdm) ControleSessao.getParam("usuario")).getLogin_adm());
            informacoesDaContaUsuario.setSenha_usuario(((UsuarioAdm) ControleSessao.getParam("usuario")).getSenha_adm());
            listaInformacoes.add(informacoesDaContaUsuario);
        }
        return listaInformacoes;
    }

    public ArrayList<InformacoesPessoaisUsuario> listaInformacoesPessoais() {
        ArrayList<InformacoesPessoaisUsuario> listaInformacoes = new ArrayList<>();
        if (ControleSessao.getParam("tipoUsuario").equals("adm")) {
            informacoesPessoaisUsuario.setEndereco_usuario(null);
            informacoesPessoaisUsuario.setEmail_usuario(((UsuarioAdm) ControleSessao.getParam("usuario")).getEmail_adm());
            informacoesPessoaisUsuario.setTelefone_usuario(((UsuarioAdm) ControleSessao.getParam("usuario")).getTelefone_adm());
            listaInformacoes.add(informacoesPessoaisUsuario);
        }
        return listaInformacoes;
    }

    public void edicaoDeDados(CellEditEvent event) {
        Object valorAntigo = event.getOldValue();
        Object novoValor = event.getNewValue();
        if (novoValor != null && !novoValor.equals(valorAntigo)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + valorAntigo + ", New:" + novoValor);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
