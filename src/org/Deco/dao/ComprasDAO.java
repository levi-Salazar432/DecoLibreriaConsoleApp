package org.Deco.dao;

import java.util.List;
import org.Deco.model.Compras;

public interface ComprasDAO {  
    List<Compras> listarTodos();
    Compras buscarPorId(int noCompra);
    boolean crear(Compras compra);
    boolean actualizar(Compras compra);
    boolean eliminar(int noCompra);
    
}
