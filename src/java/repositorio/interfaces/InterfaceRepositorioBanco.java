/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio.interfaces;

import java.util.List;
import negocio.Banco;

/**
 *
 * @author Alana Tenório
 */
public interface InterfaceRepositorioBanco {
    
    public void inserir(Banco banco);
    public void deletar(Banco banco);
    public void atualizar(Banco banco);
    public Banco recuperar(Integer numero);
    public List<Banco> recuperarTodos();
    
}
