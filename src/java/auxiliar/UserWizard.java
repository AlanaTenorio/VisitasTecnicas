/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import controladores.ControleVisitaTecnica;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import negocio.VisitaTecnica;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "userWizard")
@ViewScoped
public class UserWizard implements Serializable{

    private VisitaTecnica user = new VisitaTecnica();
     
    private boolean skip;
     
    public VisitaTecnica getUser() {
        return user;
    }
 
    public void setUser(VisitaTecnica user) {
        this.user = user;
    }
     
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    public String save(VisitaTecnica visita) {        
        System.out.println(visita.getCusto());
        return ControleVisitaTecnica.getInstance().inserirVisitaTecnica(visita);
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
}
