package org.Deco.dao.impl;

import java.util.ArrayList;
import org.Deco.model.Cliente;
import org.Deco.dao.ClienteDAO;

import org.Deco.util.Conexion;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public List<Cliente> listarTodos() {
        //crear lista
        List<Cliente> clientes = new ArrayList<>();//null
        //crear nustras consulta
        String consulta = "{call sp_listarclientes()}";
        //maperar el resultado de la consulta a objeto y lo agregamos a la lista
        //try with resources / intentar con recursos --> cierra el recurso al completar el intento
        //recurso: Conexion, al final se cierra
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consulta); ResultSet tablaResultado = consultaCall.executeQuery();) {
            //ciclo para rellenar mi lista
            //verificar cada filta del result set
            //va a guarda cada celda dentro de cada atributo de mi objeto
            while (tablaResultado.next()) {
                clientes.add(new Cliente(
                        tablaResultado.getLong("cui"),
                        tablaResultado.getString("nombre_cliente"),
                        tablaResultado.getString("apellido_cliente"),
                        tablaResultado.getString("correo_electronico")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Clientes: " + e.getMessage());
        }

        //retornamos un alista
        return clientes;
    }

    @Override
    public boolean crear(Cliente cliente) {
        String consulta = "{call sp_insertarcliente(?, ?, ?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            consultaCall.setLong(1, cliente.getCui());
            consultaCall.setString(2, cliente.getNombre());
            consultaCall.setString(3, cliente.getApellido());
            consultaCall.setString(4, cliente.getCorreoElectronico());
            return consultaCall.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.print("Error al crear Cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente buscarPorId(long cui) {
        //objeto
        Cliente cliente = new Cliente();

        //consulta
        String consultaSQL = "{call sp_buscarcliente(?)}";
        //mapeamos el ResultSet al Objeto(Cliente) segun sus atributos y la fila devulta
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consultaSQL);) {
            consultaCall.setLong(1, cui);
            ResultSet tablaResultado = consultaCall.executeQuery();
            if (tablaResultado.next()) {
                cliente.setCui(tablaResultado.getLong("cui"));
                cliente.setNombre(tablaResultado.getString("nombre_cliente"));
                cliente.setApellido(tablaResultado.getString("apellido_cliente"));
                cliente.setCorreoElectronico(tablaResultado.getString("correo_electronico"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Cliente: " + e.getMessage());
        }
        //retornamos el objeto
        return cliente;
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminar(long cui) {
        return false;
    }

}
