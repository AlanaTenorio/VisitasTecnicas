/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Alana Tenório
 */
@Entity
@ManagedBean(name = "visitaTecnica")
public class VisitaTecnica implements Serializable{
    
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToMany
    private List<Servidor> servidores;
    
    private String localVisita;
    private Integer duracao; 
    private double custo;
    private Integer quantEstudantes;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataSaida;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataChegada;
    
    private String horaSaida;
    private String horaChegada;
    private String objetivos;
    private String justificativa;
    private String publicoAlvo;
    private String compCurriculares;
    private String informacoes;
    private String requisitos;
    private String referencia;
    private boolean ativo;
    
    @ManyToMany
    private List<Estudante> estudantes;

    public VisitaTecnica() {
    }

    public List<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(List<Servidor> servidores) {
        this.servidores = servidores;
    }

    public String getLocalVisita() {
        return localVisita;
    }

    public void setLocalVisita(String localVisita) {
        this.localVisita = localVisita;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public Integer getQuantEstudantes() {
        return quantEstudantes;
    }

    public void setQuantEstudantes(Integer quantEstudantes) {
        this.quantEstudantes = quantEstudantes;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public String getCompCurriculares() {
        return compCurriculares;
    }

    public void setCompCurriculares(String compCurriculares) {
        this.compCurriculares = compCurriculares;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

    public Integer getId() {
        return id;
    }
    
    public void addEstudante(Estudante estudante){
        this.estudantes.add(estudante);
    }
    
    public void addServidor(Servidor servidor){
        this.servidores.add(servidor);
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
