package org.Deco.dao;

import java.util.List;
import org.Deco.model.Editorial;

public interface EditorialDAO {

    List<Editorial> ListarTodos();

    boolean crear(Editorial editorial);

    Editorial buscarPorId(String nit);

    boolean actualizar(Editorial editorial);

    boolean eliminar(String nit);
}
