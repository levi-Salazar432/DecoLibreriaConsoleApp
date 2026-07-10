package org.Deco.dao.impl;

import org.Deco.util.Conexion; 
import org.Deco.model.Categoria;
import org.Deco.dao.CategoriaDAO;

import java.util.List; 
import java.sql.CallableStatement; 
import java.util.ArrayList; 
import java.sql.PreparedStatement; 
import java.sql.Connection;
import java.sql.CallableStatement; 
import java.sql.SQLException; 
import java.sql.ResultSet;

public class CategoriaDAOImpl implements CategoriaDAO{
    
     @Override
    public List<Categoria> ListarTodos() {
        //crear lista 
        List<Categoria>  categoria = new ArrayList<>();//null 
        //crear nuestras consultas 
        String consulta = "{ call sp_listar_categoria()}";
        //maperar el resultado de la consulta a objeto y lo agregaamos a la lista 
        // Try with resources / intentar con recursos -----> cierra el recurso al completar el intetno
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consultaCall = conexion.prepareCall(consulta);
                ResultSet tablaResultado = consultaCall.executeQuery(); )  {
                //ResultSet tabla = Conexion.getInstancia().conectar().prepareCall(consulta).executeQuery();
         //ciclo para rellenar mi lista 
                //verificar cada fila del result set 
                //va a guardar cada celda dentro de cada atributo de objeto
           while (tablaResultado.next()) {
               categoria.add(new Categoria(
                               tablaResultado.getInt("ID"),
                               tablaResultado.getString("CATEGORIA")          
               ));
               
           }
            
        }catch (SQLException e ) {   
            System.err.print("Error al listar Categoria: " + e.getMessage());
                    
        } 
               
        return categoria; 
    }

    @Override
    public boolean crear(Categoria categoria) {
            return false; 
    }

    @Override
    public Categoria buscarPorId(int idCategoria) {
        //objeto 
        Categoria  categoria= new Categoria(); 
        
        //consultas 
        String consultaSQL = "{Call sp_buscar_categorias(?)}"; 
        //mapeamos el ResulSet al objeto(categoria) segun sus atributos y la fila devuelta
        try ( 
             Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consultaSQL) 
        ) {
            consultaCall .setInt(1,idCategoria);
            ResultSet tablaResultado = consultaCall.executeQuery();
            if (tablaResultado.next()) {
                categoria.setIdCategoria(tablaResultado.getInt("ID"));
                categoria.setNombreCategoria(tablaResultado.getString("CATEGORIA"));
                return categoria; 
                }
        else {
                System.out.println("No existe la Categoria con ese ID");
                return null; 
                
        }
                
            } catch (SQLException e ) {
                  System.err.print("Error al buscar Categoria: " + e.getMessage());
                  
                    
                    }                                 
              return null; 
          }
       
        @Override
    public boolean actualizar(Categoria categoria) {
        return false; 
    }

    @Override
    public boolean eliminar(int idCategoria) {
        return false; 
    }
    
}


