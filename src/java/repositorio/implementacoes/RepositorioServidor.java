/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio.implementacoes;

import java.util.List;
import negocio.Servidor;
import repositorio.interfaces.InterfaceRepositorioServidor;

/**
 *
 * @author Alana Tenório
 */
public class RepositorioServidor implements InterfaceRepositorioServidor{
    
    @Override
    public void inserir(Servidor servidor) {
        dao.DaoManagerHiber.persist(servidor);
    }

    @Override
    public void deletar(Servidor servidor) {
        dao.DaoManagerHiber.delete(servidor);
    }

    @Override
    public void atualizar(Servidor servidor) {
        dao.DaoManagerHiber.update(servidor);
    }

    @Override
    public Servidor recuperar(String siape) {
        return (Servidor)(dao.DaoManagerHiber.recover("from Servidor where siape='"+ siape + "'").get(0));
    }

    @Override
    public List<Servidor> recuperarTodos() {
        return (List<Servidor>) dao.DaoManagerHiber.recover("from Servidor");
    }

    @Override
    public List<Servidor> recuperarTodosAtivos() {
        return (List<Servidor>) dao.DaoManagerHiber.recover("from Servidor where ativo = true");
    }
}
