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
import negocio.Banco;
import repositorio.implementacoes.RepositorioBanco;
import repositorio.interfaces.InterfaceRepositorioBanco;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "controleBanco")
@SessionScoped
public class ControleBanco implements Serializable {

    private InterfaceRepositorioBanco rp;
    private Banco selected;

    public ControleBanco() {
        this.rp = new RepositorioBanco();
    }

    public String inserirBanco(Banco banco) {

        try {
            List<Banco> listaBancos = recuperarTodosBancosAtivos();
            for (Banco b : listaBancos) {
                if (b.getNumero() == banco.getNumero()) {
                    throw new IllegalArgumentException();
                }
            }
            if (banco.getNome() == null || banco.getNome().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
                return "";
            }

            if (banco.getNumero() == null || banco.getNumero() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o número!", ""));
                return "";
            }
            banco.setAtivo(true);
            this.rp.inserir(banco);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Banco cadastrado com sucesso!", ""));
            return "VisualizarBancos.xhtml";
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Banco já cadastrado!", ""));
        }

        return "";
    }

    public void deletarBanco(Banco banco) {
        this.rp.deletar(banco);
    }

    public String atualizarBanco(Banco banco) {
        if (banco.getNome() == null || banco.getNome().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        }

        if (banco.getNumero() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o número!", ""));
            return "";
        }

        this.rp.atualizar(banco);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Banco atualizado com sucesso!", ""));
        return "VisualizarBancos.xhtml";
    }

    public Banco recuperarBanco(Integer numero) {
        return this.rp.recuperar(numero);
    }

    public List<Banco> recuperarTodosBancos() {
        return this.rp.recuperarTodos();
    }
    
    public List<Banco> recuperarTodosBancosAtivos() {
        List<Banco> listaBancosAtivos = new ArrayList<Banco>();
        List<Banco> lista = recuperarTodosBancos();
        for (Banco banco : lista) {
            if(banco.isAtivo() == true){
                listaBancosAtivos.add(banco);
            }
        }
        return listaBancosAtivos;
    }
    
    public void desativar (Banco banco){
        banco.setAtivo(false);
    }

    public Banco getSelected() {
        return selected;
    }

    public void setSelected(Banco selected) {
        this.selected = selected;
    }

}
