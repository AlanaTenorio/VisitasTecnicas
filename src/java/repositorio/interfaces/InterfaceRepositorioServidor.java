/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio.interfaces;

import java.util.List;
import negocio.Servidor;

/**
 *
 * @author Alana Tenório
 */
public interface InterfaceRepositorioServidor {
    
    public void inserir(Servidor servidor);
    public void deletar(Servidor servidor);
    public void atualizar(Servidor servidor);
    public Servidor recuperar(String siape);
    public List<Servidor> recuperarTodos();
    public List<Servidor> recuperarTodosAtivos();
}
