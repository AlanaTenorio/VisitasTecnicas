/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import negocio.Servidor;
import negocio.VisitaTecnica;

/**
 *
 * @author Alana Tenório
 */
public class VisitaRelatorio {
    private VisitaTecnica visita;
    private String servidores;
    private Servidor servidor;

    public VisitaRelatorio() {
    }

    public VisitaTecnica getVisita() {
        return visita;
    }

    public void setVisita(VisitaTecnica visita) {
        this.visita = visita;
    }

    public String getServidores() {
        return servidores;
    }

    public void setServidores(String servidores) {
        this.servidores = servidores;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }
    
}
