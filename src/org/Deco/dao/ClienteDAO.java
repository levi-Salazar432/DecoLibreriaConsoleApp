package org.Deco.dao;

import java.util.List;
import org.Deco.model.Cliente;

public interface ClienteDAO {
    boolean crear(Cliente cliente);
    List<Cliente> listarTodos();
    Cliente buscarPorId(long cui);
    boolean actualizar(Cliente cliente);
    boolean eliminar(long cui);  
}
