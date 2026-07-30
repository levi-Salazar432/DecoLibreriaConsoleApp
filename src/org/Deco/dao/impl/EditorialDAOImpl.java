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


public class EditorialDAOImpl implements EditorialDAO{

    @Override
    public boolean insertar(Editorial editorial) {
        return false;
    }

    @Override
    public List<Editorial> ListarTodos() {
        //crear  Lista
        List<Editorial> editorial = new ArrayList (); //null
        //crear nuestra consulta
         String consulta = "{call sp_listareditoriales()}";
        //maperar el resultado de la conculta a objeto y lo agregamos a la lista
        //try with resources / intentar con recursos _> cierra el recurso al completar el intento
        //recurso: Conexion, al final se cierra
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consultaCall = conexion.prepareCall(consulta);
                ResultSet tablaResultado = consultaCall.executeQuery();){
                //ciclo para rellenar mi lista
                //verificar cada fila del result set
                //va a guarda cada celda dentro de cada atributo de mi objeto
                while (tablaResultado.next()) {
                    editorial.add(new Editorial(
                            tablaResultado.getString("nit"),
                            tablaResultado.getString("nombre_editorial"),
                            tablaResultado.getString("telefono_editorial"),
                            tablaResultado.getString("direccion_editoria")
                    ));
            }
            
        }catch (SQLException e) {
            System.err.print("Error al listar Editoriales:"+ e.getMessage());
        }      
            
        //retornamos una lista
        return editorial;
    }

    @Override
    public Editorial buscarPorId(String nit) {
        //objeto
        Editorial editorial = new Editorial();
        
        //consulta
        String consultaSQL = "{call sp_buscareditorial(?)}";
        //mapeamos el ResultSEt al Objeto(Cliente) segun sus atrubutos y la fila devuelta
        try (Connection conexion = Conexion.getInstancia().conectar();CallableStatement consultaCall = conexion.prepareCall(consultaSQL);){
            consultaCall.setString(1, nit);
            ResultSet tablaResultado = consultaCall.executeQuery();
            if (tablaResultado.next()) {
                editorial.setNit(tablaResultado.getString("nit"));
                editorial.setNombreEditorial(tablaResultado.getString("nombre_editorial"));          
                editorial.setTelefonoEditorial(tablaResultado.getString("telefono_editorial"));
                editorial.setDireccionEditorial(tablaResultado.getString("direccion_editoria"));   
            }else{
                return null; 
            }
        }catch (SQLException e){
            System.err.print("Error al buscar Editorial:" + e.getMessage());
        }
        //retornamos el objeto
        return editorial;
    }

    @Override
    public boolean actualizar(Editorial editorial){
        return false;
    }

    @Override
    public boolean eliminar(String nit) {
        String consultaSQL = "{call sp_eliminar_editorial(?)}";
        
        try(
            Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consultaCall = conexion.prepareCall(consultaSQL)
                ){
            consultaCall.setString(1, nit);
            
            int filasAfectadas = consultaCall.executeUpdate();
            
            if (filasAfectadas > 0){
                System.out.println("Editorial eliminada con exito");
                return true;
            }else {
                System.out.println("No se encontro ninguna editorial con ese nit");
                return false;
            }
            
            }catch (SQLException e){
                System.out.println("Error al elminar editorial" + e.getMessage());
                return false;
            }
        
 
    }
    
}
