/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio.implementacoes;

import java.util.List;
import negocio.Banco;
import repositorio.interfaces.InterfaceRepositorioBanco;

/**
 *
 * @author Alana Tenório
 */
public class RepositorioBanco implements InterfaceRepositorioBanco{

    @Override
    public void inserir(Banco banco) {
        dao.DaoManagerHiber.persist(banco);
    }

    @Override
    public void deletar(Banco banco) {
        dao.DaoManagerHiber.delete(banco);
    }

    @Override
    public void atualizar(Banco banco) {
        dao.DaoManagerHiber.update(banco);
    }

    @Override
    public Banco recuperar(Integer numero) {
        return (Banco)(dao.DaoManagerHiber.recover("from Banco where numero="+ numero).get(0));
    }

    @Override
    public List<Banco> recuperarTodos() {
        return (List<Banco>) dao.DaoManagerHiber.recover("from Banco");
    }
    
}
