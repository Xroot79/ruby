package util;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class ControleSessao implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static HttpSession getSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
        return sessao;
    }

    public static void setParam(String nome, Object valor) {
        getSession().setAttribute(nome, valor);
    }

    public static Object getParam(String nome) {
        return getSession().getAttribute(nome);
    }

    public static void remove(String nome) {
        getSession().removeAttribute(nome);
    }
}
