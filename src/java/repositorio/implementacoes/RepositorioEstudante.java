/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio.implementacoes;

import java.util.List;
import negocio.Estudante;
import repositorio.interfaces.InterfaceRepositorioEstudante;

/**
 *
 * @author Alana Tenório
 */
public class RepositorioEstudante implements InterfaceRepositorioEstudante{
    
    @Override
    public void inserir(Estudante estudante) {
        dao.DaoManagerHiber.persist(estudante);
    }

    @Override
    public void deletar(Estudante estudante) {
        dao.DaoManagerHiber.delete(estudante);
    }

    @Override
    public void atualizar(Estudante estudante) {
        dao.DaoManagerHiber.update(estudante);
    }

    @Override
    public Estudante recuperar(Integer id) {
        return (Estudante)(dao.DaoManagerHiber.recover("from Estudante where id="+ id).get(0));
    }

    @Override
    public List<Estudante> recuperarTodos() {
        return (List<Estudante>) dao.DaoManagerHiber.recover("from Estudante");
    }
}
