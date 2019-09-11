/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio.implementacoes;

import java.util.List;
import negocio.VisitaTecnica;
import repositorio.interfaces.InterfaceRepositorioVisitaTecnica;

/**
 *
 * @author Alana Tenório
 */
public class RepositorioVisitaTecnica implements InterfaceRepositorioVisitaTecnica {

    @Override
    public void inserir(VisitaTecnica visitaTecnica) {
        dao.DaoManagerHiber.persist(visitaTecnica);
    }

    @Override
    public void deletar(VisitaTecnica visitaTecnica) {
        dao.DaoManagerHiber.delete(visitaTecnica);
    }

    @Override
    public void atualizar(VisitaTecnica visitaTecnica) {
        dao.DaoManagerHiber.update(visitaTecnica);
    }

    @Override
    public VisitaTecnica recuperar(Integer id) {
        return (VisitaTecnica)dao.DaoManagerHiber.recover("from VisitaTecnica where id = " + id).get(0);
    }

    @Override
    public List<VisitaTecnica> recuperarTodos() {
        return (List<VisitaTecnica>) dao.DaoManagerHiber.recover("from VisitaTecnica");
    }
    
}
