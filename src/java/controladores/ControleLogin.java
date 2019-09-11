/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Servidor;
import org.primefaces.context.RequestContext;
import repositorio.implementacoes.RepositorioServidor;
import repositorio.interfaces.InterfaceRepositorioServidor;

/**
 *
 * @author Alana
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private InterfaceRepositorioServidor rs;
    private boolean loggedIn = false;
    private Servidor servidor;
    private String siape;
    private boolean professor = false;
    private boolean administrador = false;

    private static ControleLogin mySelf = null;

    public static ControleLogin getInstance() {
        if (mySelf == null) {
            mySelf = new ControleLogin();
        }
        return mySelf;
    }

    public ControleLogin() {
        rs = new RepositorioServidor();
    }

    public String login(Servidor servidor) {
        
        
        RequestContext context = RequestContext.getCurrentInstance();

        if (servidor.getSiape() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
        }
        if (servidor.getSenha().equals("") || servidor.getSenha().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
        }
        if (servidor.getSiape() == null || servidor.getSenha().equals("") || servidor.getSenha().isEmpty()) {
            return "";
        }
         
        servidor.setSenha(criptografia.Criptografia.criptografar(servidor.getSenha()));
        Servidor s = null;
        List<Servidor> ls = this.rs.recuperarTodosAtivos();

        for (int i = 0; i < ls.size(); i++) {
            if (servidor.getSiape().equals(ls.get(i).getSiape())) {
                s = ls.get(i);
                break;
            }
        }

        if (s == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Servidor não encontrado!", ""));
            return "";
        }
        if (servidor.getSiape().equals(s.getSiape())) {
            
            if (servidor.getSenha().equals(s.getSenha())) {
                
                loggedIn = true;
                this.siape = s.getSiape();
                
                if (s.getPerfil().equals("Administrador")) {
                    this.administrador = true;
                }
                
                if (s.getPerfil().equals("Professor")) {
                    this.professor = true;
                }
                
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha inválida!", ""));
                return "";
            }
        }

        this.setServidor(s);
        context.addCallbackParam("loggedIn", loggedIn);
        return "index.xhtml";
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void sair() {
        this.loggedIn = false;
        this.professor = false;
        this.administrador = false;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isProfessor() {
        return professor;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }
}
