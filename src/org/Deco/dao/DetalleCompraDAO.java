package org.Deco.dao;
import java.util.List;
import org.Deco.model.DetalleCompra;
public interface DetalleCompraDAO {
    List<DetalleCompra> ListarTodos();
    boolean crear(DetalleCompra detalleCompra);
    DetalleCompra buscarPorId(int idDetalleCompra);
    boolean actualizar(DetalleCompra detalleCompra);
    boolean eliminar(int idDetalleCompra);
}