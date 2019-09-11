/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Servidor;
import org.hibernate.NonUniqueObjectException;
import repositorio.implementacoes.RepositorioServidor;
import repositorio.interfaces.InterfaceRepositorioServidor;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "controleServidor")
@SessionScoped
public class ControleServidor implements Serializable {

    private InterfaceRepositorioServidor rs;
    private Servidor selected;
    private boolean validaSiape;

    public ControleServidor() {
        this.rs = new RepositorioServidor();
    }

    public String inserirPrimeiroServidor(Servidor servidor) {

        try {
            List<Servidor> listaServidores = recuperarTodosServidoresAtivos();
            for (Servidor s : listaServidores) {
                if (s.getSiape().equals(servidor.getSiape())) {
                    throw new IllegalArgumentException();
                }
            }

            if (servidor.getSiape() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
                return "";
            } else {
                this.validaSiape = servidor.getSiape().matches("[0-9]+");

                if (this.validaSiape == false) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE é composto apenas por números!", ""));
                    return "";

                }
            }
            if (servidor.getNome() == null || servidor.getNome().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
                return "";
            }
            if (servidor.getSenha() == null || servidor.getSenha().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
                return "";
            }
            if (servidor.getPerfil() == null || servidor.getPerfil().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o perfil!", ""));
                return "";
            }
            servidor.setSenha(criptografia.Criptografia.criptografar(servidor.getSenha()));
            servidor.setAtivo(true);
            this.rs.inserir(servidor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor cadastrado com sucesso!", ""));
            return "login.xhtml";

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Servidor já cadastrado!", ""));
        }
        return "";
    }

    public String inserirServidor(Servidor servidor) {

        try {
            List<Servidor> listaServidores = recuperarTodosServidoresAtivos();
            for (Servidor s : listaServidores) {
                if (s.getSiape().equals(servidor.getSiape())) {
                    throw new IllegalArgumentException();
                }
            }

            if (servidor.getSiape() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
                return "";
            } else {
                this.validaSiape = servidor.getSiape().matches("[0-9]+");

                if (this.validaSiape == false) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE é composto apenas por números!", ""));
                    return "";

                }
            }
            if (servidor.getNome() == null || servidor.getNome().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
                return "";
            }
            if (servidor.getSenha() == null || servidor.getSenha().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
                return "";
            }
            if (servidor.getPerfil() == null || servidor.getPerfil().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o perfil!", ""));
                return "";
            }
            servidor.setSenha(criptografia.Criptografia.criptografar(servidor.getSenha()));
            servidor.setAtivo(true);
            this.rs.inserir(servidor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor cadastrado com sucesso!", ""));
            return "VisualizarServidores.xhtml";

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Servidor já cadastrado!", ""));
        }
        return "";
    }

    public String inserirServidorPopUp(Servidor servidor) {

        try {
            List<Servidor> listaServidores = recuperarTodosServidoresAtivos();
            for (Servidor s : listaServidores) {
                if (s.getSiape().equals(servidor.getSiape())) {
                    throw new IllegalArgumentException();
                }
            }

            if (servidor.getSiape() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
                return "";
            } else {
                this.validaSiape = servidor.getSiape().matches("[0-9]+");

                if (this.validaSiape == false) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE é composto apenas por números!", ""));
                    return "";

                }
            }
            if (servidor.getNome() == null || servidor.getNome().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
                return "";
            }
            if (servidor.getSenha() == null || servidor.getSenha().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
                return "";
            }
            if (servidor.getPerfil() == null || servidor.getPerfil().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o perfil!", ""));
                return "";
            }
            servidor.setSenha(criptografia.Criptografia.criptografar(servidor.getSenha()));
            servidor.setAtivo(true);
            this.rs.inserir(servidor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor cadastrado com sucesso!", ""));
            return "";

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Servidor já cadastrado!", ""));
        }
        return "";
    }

    public void deletarServidor(Servidor servidor) {
        this.rs.deletar(servidor);
    }

    public String atualizarServidor(Servidor servidor) {
        if (servidor.getNome() == null || servidor.getNome().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        }
        if (servidor.getSiape() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
            return "";
        }
        if (servidor.getSenha() == null || servidor.getSenha().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
            return "";
        }
        servidor.setSenha(criptografia.Criptografia.criptografar(servidor.getSenha()));
        servidor.setAtivo(true);
        this.rs.atualizar(servidor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor atualizado com sucesso!", ""));
        if(servidor.getPerfil().equals("Administrador")){
            return "VisualizarServidores.xhtml";
        } else if (servidor.getPerfil().equals("Professor")){
            return "AlterarPerfilProf.xhtml";
        }
        return "";
    }

    public List<Servidor> recuperarTodosServidoresAtivos() {
        List<Servidor> listaServidoresAtivos = new ArrayList<Servidor>();
        List<Servidor> lista = recuperarTodosServidores();
        for (Servidor servidor : lista) {
            if (servidor.isAtivo() == true) {
                listaServidoresAtivos.add(servidor);
            }
        }
        return listaServidoresAtivos;
    }

    public void desativar(Servidor servidor) {
        servidor.setAtivo(false);
    }

    public Servidor recuperarServidor(String siape) {
        return this.rs.recuperar(siape);
    }

    public List<Servidor> recuperarTodosServidores() {
        return this.rs.recuperarTodos();
    }

    public Servidor getSelected() {
        return selected;
    }

    public void setSelected(Servidor selected) {
        this.selected = selected;
    }
}
