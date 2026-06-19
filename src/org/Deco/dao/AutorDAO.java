package org.Deco.dao;

import java.util.List;
import org.Deco.model.Autor;

public interface AutorDAO {
    
    boolean insertar(Autor autor);
    List<Autor> listar();
    Autor buscar (int id_autor);
    boolean actualizar(Autor autor);
    boolean eliminar (int id_autor);
    
}
