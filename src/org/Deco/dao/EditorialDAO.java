
package org.Deco.dao;

import java.util.List;
import org.Deco.model.Editorial;

public interface EditorialDAO {
    //firmas de metodos
    //CRUD
    boolean insertar(Editorial editorial);
    List<Editorial> listar ();
    Editorial buscar(String nit);
    boolean actualizar(Editorial editorial);
    boolean eliminar(String nit);
}
