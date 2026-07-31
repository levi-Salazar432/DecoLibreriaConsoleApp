package org.Deco.dao.impl;

import org.Deco.util.Conexion;
import org.Deco.model.Editorial;
import org.Deco.dao.EditorialDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class EditorialDAOImpl implements EditorialDAO {

    public boolean insertar(Editorial editorial) {
        return false;
    }

    @Override
    public List<Editorial> ListarTodos() {
        List<Editorial> editorial = new ArrayList();
        String consulta = "{call sp_listareditoriales()}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consulta); ResultSet tablaResultado = consultaCall.executeQuery();) {
            while (tablaResultado.next()) {
                editorial.add(new Editorial(
                        tablaResultado.getString("nit"),
                        tablaResultado.getString("nombre_editorial"),
                        tablaResultado.getString("telefono_editorial"),
                        tablaResultado.getString("direccion_editoria")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Editoriales:" + e.getMessage());
        }
        return editorial;
    }

    @Override
    public boolean crear(Editorial editorial) {
        String consulta = "{call sp_insertarEditorial(?, ?, ?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            consultaCall.setString(1, editorial.getNit());
            consultaCall.setString(2, editorial.getNombreEditorial());
            consultaCall.setString(3, editorial.getTelefonoEditorial());
            consultaCall.setString(4, editorial.getDireccionEditorial());
            return consultaCall.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.print("Error al crear Cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Editorial buscarPorId(String nit) {
        Editorial editorial = new Editorial();
        String consultaSQL = "{call sp_buscareditorial(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consultaSQL);) {
            consultaCall.setString(1, nit);
            ResultSet tablaResultado = consultaCall.executeQuery();
            if (tablaResultado.next()) {
                editorial.setNit(tablaResultado.getString("nit"));
                editorial.setNombreEditorial(tablaResultado.getString("nombre_editorial"));
                editorial.setTelefonoEditorial(tablaResultado.getString("telefono_editorial"));
                editorial.setDireccionEditorial(tablaResultado.getString("direccion_editoria"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Editorial:" + e.getMessage());
        }
        return editorial;
    }

    @Override
    public boolean actualizar(Editorial editorial) {
        return false;
    }

    @Override
    public boolean eliminar(String nit) {
        String consultaSQL = "{call sp_eliminar_editorial(?)}";

        try (
                Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            consultaCall.setString(1, nit);

            int filasAfectadas = consultaCall.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Editorial eliminada con exito");
                return true;
            } else {
                System.out.println("No se encontro ninguna editorial con ese nit");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al elminar editorial" + e.getMessage());
            return false;
        }

    }

}
