/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorio.interfaces;

import java.util.List;
import negocio.VisitaTecnica;

/**
 *
 * @author Alana Tenório
 */
public interface InterfaceRepositorioVisitaTecnica {
    public void inserir(VisitaTecnica visitaTecnica);
    public void deletar(VisitaTecnica visitaTecnica);
    public void atualizar(VisitaTecnica visitaTecnica);
    public VisitaTecnica recuperar(Integer id);
    public List<VisitaTecnica> recuperarTodos();
}
