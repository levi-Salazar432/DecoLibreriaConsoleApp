package org.Deco.dao.impl;

import org.Deco.util.Conexion;
import org.Deco.model.DetalleCompra;
import org.Deco.dao.DetalleCompraDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class DetalleCompraDAOImpl implements DetalleCompraDAO {

    public boolean insertar(DetalleCompra detalleCompra) {
        return false;
    }

    @Override
    public List<DetalleCompra> ListarTodos() {
        List<DetalleCompra> detalleCompra = new ArrayList();
        String consulta = "{call sp_listadetallecompra()}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consulta); ResultSet tablaResultado = consultaCall.executeQuery();) {
            while (tablaResultado.next()) {
                detalleCompra.add(new DetalleCompra(
                        tablaResultado.getInt("id_detalle_compra"),
                        tablaResultado.getInt("no_compra"),
                        tablaResultado.getString("isbn")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Detalle Compra:" + e.getMessage());
        }
        return detalleCompra;
    }

    @Override
    public boolean crear(DetalleCompra detalleCompra) {
        String consulta = "{call sp_insertardetallecompra(?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            consultaCall.setInt(1, detalleCompra.getNoCompra());
            consultaCall.setString(2, detalleCompra.getIsbn());
            return consultaCall.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.print("Error al crear Detalle Compra: " + e.getMessage());
            return false;
        }
    }

    @Override
    public DetalleCompra buscarPorId(int idDetalleCompra) {
        DetalleCompra detalleCompra = new DetalleCompra();
        String consultaSQL = "{call sp_buscardetallecompra(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consultaSQL);) {
            consultaCall.setInt(1, idDetalleCompra);
            ResultSet tablaResultado = consultaCall.executeQuery();
            if (tablaResultado.next()) {
                detalleCompra.setIdDetalleCompra(tablaResultado.getInt("id_detalle_compra"));
                detalleCompra.setNoCompra(tablaResultado.getInt("no_compra"));
                detalleCompra.setIsbn(tablaResultado.getString("isbn"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Detalle Compra:" + e.getMessage());
        }
        return detalleCompra;
    }

    @Override
    public boolean actualizar(DetalleCompra detalleCompra) {
        return false;
    }

    @Override
    public boolean eliminar(int idDetalleCompra) {
        String consultaSQL = "{call sp_eliminardetallecompra(?)}";

        try (
                Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            consultaCall.setInt(1, idDetalleCompra);

            int filasAfectadas = consultaCall.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Detalle de compra eliminado con exito");
                return true;
            } else {
                System.out.println("No se encontro ningun detalle de compra con ese id");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle de compra" + e.getMessage());
            return false;
        }
    }
}