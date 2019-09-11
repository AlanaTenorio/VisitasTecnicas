/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio.interfaces;

import java.util.List;
import negocio.Estudante;

/**
 *
 * @author Alana Tenório
 */
public interface InterfaceRepositorioEstudante {
    
    public void inserir(Estudante estudante);
    public void deletar(Estudante estudante);
    public void atualizar(Estudante estudante);
    public Estudante recuperar(Integer id);
    public List<Estudante> recuperarTodos();
}
