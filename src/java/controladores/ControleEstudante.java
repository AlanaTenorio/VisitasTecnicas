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
import negocio.Estudante;
import repositorio.implementacoes.RepositorioEstudante;
import repositorio.interfaces.InterfaceRepositorioEstudante;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "controleEstudante")
@SessionScoped
public class ControleEstudante implements Serializable {

    private InterfaceRepositorioEstudante re;
    private Estudante selected;

    public ControleEstudante() {
        this.re = new RepositorioEstudante();
    }

    public String inserirEstudante(Estudante estudante) {
        try {
            List<Estudante> listaEstudantes = recuperarTodosEstudantesAtivos();
            for (Estudante e : listaEstudantes) {
                if (estudante.getCpf().equals(e.getCpf())) {
                    throw new IllegalArgumentException();
                }
            }
            if (estudante.getNome() == null || estudante.getNome().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
                return "";
            }
            if (estudante.getCpf() == null || estudante.getCpf().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o cpf!", ""));
                return "";
            }
            if (estudante.getDataNascimento() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data de nascimento!", ""));
                return "";
            }
            estudante.setAtivo(true);
            this.re.inserir(estudante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudante cadastrado com sucesso!", ""));
            return "VisualizarEstudantes.xhtml";
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Estudante já cadastrado!", ""));

        }
        return "";
    }
    public void inserrirEstudantePopUp(Estudante estudante){
        try {
            List<Estudante> listaEstudantes = recuperarTodosEstudantesAtivos();
            for (Estudante e : listaEstudantes) {
                if (estudante.getCpf().equals(e.getCpf())) {
                    throw new IllegalArgumentException();
                }
            }
            if (estudante.getNome() == null || estudante.getNome().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            }
            if (estudante.getCpf() == null || estudante.getCpf().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o cpf!", ""));
            }
            if (estudante.getDataNascimento() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data de nascimento!", ""));
            }
            estudante.setAtivo(true);
            this.re.inserir(estudante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudante cadastrado com sucesso!", ""));
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Estudante já cadastrado!", ""));

        }
    }

    public void deletarEstudante(Estudante estudante) {
        this.re.deletar(estudante);
    }

    public String atualizarEstudante(Estudante estudante) {

        if (estudante.getNome() == null || estudante.getNome().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        }
        if (estudante.getCpf() == null || estudante.getCpf().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o cpf!", ""));
            return "";
        }
        if (estudante.getDataNascimento() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data de nascimento!", ""));
            return "";
        }
        this.re.atualizar(estudante);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudante atualizado com sucesso!", ""));
        return "VisualizarEstudantes.xhtml";
    }
    
    public List<Estudante> recuperarTodosEstudantesAtivos() {
        List<Estudante> listaEstudantesAtivos = new ArrayList<Estudante>();
        List<Estudante> lista = recuperarTodosEstudantes();
        for (Estudante estudante : lista) {
            if(estudante.isAtivo() == true){
                listaEstudantesAtivos.add(estudante);
            }
        }
        return listaEstudantesAtivos;
    }
    
    public void desativar (Estudante estudante){
        estudante.setAtivo(false);
    }

    public Estudante recuperarEstudante(Integer id) {
        return this.re.recuperar(id);
    }

    public List<Estudante> recuperarTodosEstudantes() {
        return this.re.recuperarTodos();
    }

    public Estudante getSelected() {
        return selected;
    }

    public void setSelected(Estudante selected) {
        this.selected = selected;
    }

}
