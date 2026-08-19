package org.Deco.dao.impl;

import org.Deco.util.Conexion;
import org.Deco.model.Compras;
import org.Deco.dao.ComprasDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ComprasDAOImpl implements ComprasDAO {

    @Override
    public List<Compras> listarTodos() {
        List<Compras> listaCompras = new ArrayList<>();
        String consulta = "{call sp_listarcompras()}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consulta); 
             ResultSet tablaResultado = consultaCall.executeQuery()) {
            
            while (tablaResultado.next()) {
                Timestamp timestamp = tablaResultado.getTimestamp("fecha_compra");
                LocalDateTime fechaHora = (timestamp != null) ? timestamp.toLocalDateTime() : null;

                listaCompras.add(new Compras(
                        tablaResultado.getInt("no_compra"),
                        fechaHora,
                        tablaResultado.getFloat("total_compra"),
                        tablaResultado.getInt("cui_cliente")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar Compras: " + e.getMessage());
        }
        return listaCompras;
    }

    @Override
    public Compras buscarPorId(int noCompra) {
        Compras compra = null;
        String consultaSQL = "{call sp_buscarcompra(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            
            consultaCall.setInt(1, noCompra);
            try (ResultSet tablaResultado = consultaCall.executeQuery()) {
                if (tablaResultado.next()) {
                    Timestamp timestamp = tablaResultado.getTimestamp("fecha_compra");
                    LocalDateTime fechaHora = (timestamp != null) ? timestamp.toLocalDateTime() : null;

                    compra = new Compras(
                            tablaResultado.getInt("no_compra"),
                            fechaHora,
                            tablaResultado.getFloat("total_compra"),
                            tablaResultado.getInt("cui_cliente")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Compra: " + e.getMessage());
        }
        return compra;
    }

    @Override
    public boolean crear(Compras compra) {
        String consulta = "{call sp_insertarcompra(?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            
            consultaCall.setFloat(1, compra.getTotalCompra());
            consultaCall.setInt(2, compra.getCui());
            return consultaCall.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al crear Compra: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Compras compra) {
        String consulta = "{call sp_actualizarcompra(?, ?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            
            consultaCall.setInt(1, compra.getNoCompra());
            consultaCall.setFloat(2, compra.getTotalCompra());
            consultaCall.setInt(3, compra.getCui());
            return consultaCall.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar Compra: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int noCompra) {
        String consultaSQL = "{call sp_eliminarcompra(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            
            consultaCall.setInt(1, noCompra);
            int filasAfectadas = consultaCall.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Compra eliminada con éxito");
                return true;
            } else {
                System.out.println("No se encontró ninguna compra con ese número");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar Compra: " + e.getMessage());
            return false;
        }
    }
}
