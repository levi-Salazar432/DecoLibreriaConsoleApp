package org.Deco.model;

public class Categoria {
    /*
    create table categorias(
	id_categoria int primary key auto_increment,
    nombre_categoria varchar(100)
);
    */    
    //atributos de clase 
    private int idCategoria ;
    private String nombreCategoria;
    
    //construcctores 
    //vacio
public Categoria() {
          
}
//lleno o con parametros 
 public Categoria(int idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }
 //personalizado 
      
    //getter and setter 

    public int getIdCategoria() {
      return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
         this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        //Formatear a Mayuscula 
        String nombreCategoriaMayuscula = nombreCategoria.toUpperCase();
        //Formatear a Iniciar con Mayuscula
        this.nombreCategoria = nombreCategoria;
    }
 
}
