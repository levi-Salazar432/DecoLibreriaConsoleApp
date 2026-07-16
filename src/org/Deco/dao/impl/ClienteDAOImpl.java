package org.Deco.dao.impl;

import org.Deco.util.Conexion; 
import org.Deco.model.Cliente; 
import org.Deco.dao.ClienteDAO; 

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;  
import java.sql.ResultSet; 

public class ClienteDAOImpl implements ClienteDAO{

    @Override
    public boolean insertar(Cliente cliente) {
        return false; 
    }

    @Override
    public List<Cliente> listarTodos() {
        //crear lista
        List<Cliente> clientes = new ArrayList<>(); 
        //crear nuestra consulta 
        String consulta = "{call sp_listarclientes()}"; 
        //mapear el resultado de la consulta a objeto y lo agregamos a lista 
        //try with resources  / intentar con recursos -> cierra el recurso al completar el intento 
         try  (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consultaCall = conexion.prepareCall(consulta); 
                ResultSet tablaResultado = consultaCall.executeQuery(); ) {
             //ciclo para rellenar mi lista 
             //verificar cada fila del resultado set 
             //va a guardar cada celda dentro de cada atributo de mi objeto 
             while(tablaResultado.next()) {
                  clientes.add(new Cliente(
                                tablaResultado.getLong("cui"),
                                tablaResultado.getString("nombre_cliente"),
                                tablaResultado.getString("apellido_cliente"),
                                tablaResultado.getString("correo_electronico")
                  )); 
                  }  
        }  catch (SQLException e ) {
              System.err.print("Error al listar Clientes: " + e.getMessage()); 
        }
            
        //retornamos una lista 
        return clientes; 
    }

    @Override
    public Cliente buscar(long cui) {
        //objeto
        Cliente cliente = new Cliente();
        
        //consulta 
        String consultaSQL = "{call sp_buscarcliente(?)}"; 
        //mapeamos el ResultSet al objeto(Cliente) segun sus atributos y la fila devuelta 
        try (Connection conexion = Conexion.getInstancia().conectar(); 
                    CallableStatement consultaCall = conexion.prepareCall(consultaSQL);){
              consultaCall.setLong(1,cui); 
              ResultSet tablaResultado = consultaCall.executeQuery(); 
              if (tablaResultado.next()) {
                  cliente.setCui(tablaResultado.getLong("cui")); 
                  cliente.setNombreCliente(tablaResultado.getString("nombre_cliente")); 
                  cliente.setApellidoCliente(tablaResultado.getString("apellido_cliente")); 
                  cliente.setCorreoElectronico(tablaResultado.getString("correo_electronico")); 
              }
              else {
                  return null; 
              }
        }catch (SQLException e) {
            System.err.print("Error al buscar Clientes: " + e.getMessage()); 
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
     String consultaSQL = "{call sp_eliminarcliente(?)}";
    try ( Connection conexion = Conexion.getInstancia().conectar();
                     CallableStatement consultaCall = conexion.prepareCall(consultaSQL)
         ){
       consultaCall.setLong(1,cui); 
       int filasAfectadas = consultaCall.executeUpdate(); 
       if (filasAfectadas > 0) { 
           System.out.println("Cliente eliminado con exito");
           return true; 
       } else {
           System.out.println("No se encontro ningun cliente con ese ID");
         return false;   
       }
       }catch (SQLException e) { 
             System.out.println("Error al elminiar cliente " + e.getMessage());
             return false; 
       }
    }
}

