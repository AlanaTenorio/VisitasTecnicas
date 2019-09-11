/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Estudante;
import negocio.Servidor;
import negocio.VisitaTecnica;
import org.primefaces.model.DualListModel;
import repositorio.implementacoes.RepositorioVisitaTecnica;
import repositorio.interfaces.InterfaceRepositorioVisitaTecnica;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "controleVisitaTecnica")
@SessionScoped
public class ControleVisitaTecnica implements Serializable {

    private InterfaceRepositorioVisitaTecnica rv = null;
    private VisitaTecnica selected;
    ControleServidor cs = new ControleServidor();
    List<Servidor> servidoresSource;
    List<Servidor> servidoresTarget;
    private DualListModel<Servidor> servidores;
    ControleEstudante ce = new ControleEstudante();
    List<Estudante> estudantesSource;
    List<Estudante> estudantesTarget;
    private DualListModel<Estudante> estudantes;
    
    private static ControleVisitaTecnica mySelf = null;

    public static ControleVisitaTecnica getInstance() {
        if (mySelf == null) {
            mySelf = new ControleVisitaTecnica();
        }
        return mySelf;
    }
    
    public ControleVisitaTecnica() {
        this.rv = new RepositorioVisitaTecnica();
    }
    
    public void inicializarPickList(VisitaTecnica v){
        servidoresTarget = v.getServidores();
        servidoresSource = cs.recuperarTodosServidoresAtivos();        
        servidores = new DualListModel<Servidor>(servidoresSource, servidoresTarget);
        estudantesTarget = v.getEstudantes();
        estudantesSource = ce.recuperarTodosEstudantesAtivos();
        estudantes = new DualListModel<Estudante>(estudantesSource, estudantesTarget);
    }

    public String inserirVisitaTecnica(VisitaTecnica visitaTecnica) {
        if(visitaTecnica.getDataSaida() == null){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data de saída!", ""));
            return "";
        }
        
        if(visitaTecnica.getDataChegada() == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data de chegada!", ""));
            return "";
        }
        
        if(visitaTecnica.getHoraChegada() == null || visitaTecnica.getHoraChegada().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a hora de chegada!", ""));
            return "";
        }
        
        if(!visitaTecnica.getHoraChegada().matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: A data de chegada informada é inválida.", ""));
            return "";
        }
        
        if(visitaTecnica.getDataChegada().getTime() < visitaTecnica.getDataSaida().getTime()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: A data de chegada deve ser maior que a data de saída.", ""));
            return "";
        }
        
        if(visitaTecnica.getDataSaida().getTime() > new Date().getTime()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: A data de saída não pode ser maior que a data atual.", ""));
            return "";
        }
        
        if(visitaTecnica.getHoraSaida() == null || visitaTecnica.getHoraSaida().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a hora de saída!", ""));
            return "";
        }
        
        if(!visitaTecnica.getHoraSaida().matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: A data de saída informada é inválida.", ""));
            return "";
        }
        
        if(visitaTecnica.getLocalVisita() == null || visitaTecnica.getLocalVisita().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o local da visita!", ""));
            return "";
        }
        
        if(visitaTecnica.getDuracao() == null || visitaTecnica.getDuracao() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a duração da visita!", ""));
            return "";
        }
        
        if(visitaTecnica.getCusto() < 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o custo da visita!", ""));
            return "";
        }
        
        if(visitaTecnica.getQuantEstudantes()== null || visitaTecnica.getQuantEstudantes()<= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a a quantidade de estudantes!", ""));
            return "";
        }
        visitaTecnica.setAtivo(true);
        this.rv.inserir(visitaTecnica);
        return "VisualizarVisitas.xhtml";
    }

    public void deletarVisitaTecnica(VisitaTecnica visitaTecnica) {
        this.rv.deletar(visitaTecnica);
    }

    public String atualizarVisitaTecnica(VisitaTecnica visitaTecnica) {
        if(visitaTecnica.getDataSaida() == null){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data de saída!", ""));
            return "";
        }
        
        if(visitaTecnica.getDataChegada() == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data de chegada!", ""));
            return "";
        }
        
        if(visitaTecnica.getHoraChegada() == null || visitaTecnica.getHoraChegada().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a hora de chegada!", ""));
            return "";
        }
        
        if(!visitaTecnica.getHoraChegada().matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: A data de chegada informada é inválida.", ""));
            return "";
        }
        
        if(visitaTecnica.getDataChegada().getTime() < visitaTecnica.getDataSaida().getTime()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: A data de chegada deve ser maior que a data de saída.", ""));
            return "";
        }
        
        if(visitaTecnica.getDataSaida().getTime() > new Date().getTime()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: A data de saída não pode ser maior que a data atual.", ""));
            return "";
        }
        
        if(visitaTecnica.getHoraSaida() == null || visitaTecnica.getHoraSaida().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a hora de saída!", ""));
            return "";
        }
        
        if(!visitaTecnica.getHoraSaida().matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: A data de saída informada é inválida.", ""));
            return "";
        }
        
        if(visitaTecnica.getLocalVisita() == null || visitaTecnica.getLocalVisita().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o local da visita!", ""));
            return "";
        }
        
        if(visitaTecnica.getDuracao() == null || visitaTecnica.getDuracao() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a duração da visita!", ""));
            return "";
        }
        
        if(visitaTecnica.getCusto() < 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o custo da visita!", ""));
            return "";
        }
        
        if(visitaTecnica.getQuantEstudantes()== null || visitaTecnica.getQuantEstudantes()<= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a a quantidade de estudantes!", ""));
            return "";
        }
        
        visitaTecnica.setEstudantes(getEstudantes().getTarget());
        visitaTecnica.setServidores(getServidores().getTarget());
        this.rv.atualizar(visitaTecnica);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Visita Técnica atualizada com sucesso!", ""));
        return "VisualizarVisitas.xhtml";
    }
    
    public List<VisitaTecnica> recuperarTodasVisitasAtivas() {
        List<VisitaTecnica> listaVisitasAtivos = new ArrayList<VisitaTecnica>();
        List<VisitaTecnica> lista = recuperarTodasVisitasTecnicas();
        for (VisitaTecnica visita : lista) {
            if(visita.isAtivo() == true){
                listaVisitasAtivos.add(visita);
            }
        }
        return listaVisitasAtivos;
    }
    
    public void desativar (VisitaTecnica visita){
        visita.setAtivo(false);
    }

    public VisitaTecnica recuperarVisitaTecnica(Integer id) {
        return this.rv.recuperar(id);
    }

    public List<VisitaTecnica> recuperarTodasVisitasTecnicas() {
        return this.rv.recuperarTodos();
    }

    public VisitaTecnica getSelected() {
        return selected;
    }

    public void setSelected(VisitaTecnica selected) {
        this.selected = selected;
    }

    public DualListModel<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(DualListModel<Servidor> servidores) {
        this.servidores = servidores;
    }

    public DualListModel<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(DualListModel<Estudante> estudantes) {
        this.estudantes = estudantes;
    }
    
}
