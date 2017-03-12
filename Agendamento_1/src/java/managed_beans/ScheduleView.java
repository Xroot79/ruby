/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao.CompromissoDAO;
import entidades.Compromisso;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private Compromisso compromisso = new Compromisso();
    private CompromissoDAO compromissoDAO = new CompromissoDAO();

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        compromissoDAO = new CompromissoDAO();
        try {
            ArrayList<Compromisso> listaDeCompromissos = compromissoDAO.listaDeCompromissos();
            if (listaDeCompromissos != null) {
                for (Compromisso listaDeCompromisso : listaDeCompromissos) {
                    SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
                    Date data = parserSDF.parse(listaDeCompromisso.getDataEHora());
                    eventModel.addEvent(new DefaultScheduleEvent(listaDeCompromisso.getNome(), data, data));
                }
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ScheduleView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public ArrayList<Compromisso> listaCompromisso() throws SQLException, ParseException {
        ArrayList<Compromisso> comp;
        if (event.getStartDate() != null) {
            comp = compromissoDAO.getDetalhesCompromisso((String.valueOf(event.getStartDate())));
            return comp;
        } else {
            return new ArrayList<>();
        }
    }

    public void addEvent(ActionEvent actionEvent) throws IOException {
        if (event.getId() == null) {
            compromissoDAO = new CompromissoDAO();
            try {
                boolean retorno = compromissoDAO.inserirCompromisso(compromisso);
                if (retorno) {
                   FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não foi possível salvar os dados.", "Atualize a página e tente novamente."));
                    RequestContext.getCurrentInstance().execute("PF('eventDialog').hide();");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onEventSelect(SelectEvent selectEvent) throws SQLException {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {  
        //faz varios nadas 
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
