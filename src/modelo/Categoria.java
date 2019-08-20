package modelo;
// Generated 18/08/2019 04:31:36 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Categoria generated by hbm2java
 */
public class Categoria implements java.io.Serializable {


     private int catId;
     private String descripcion;
     private Set productos = new HashSet(0);

    public Categoria() {
    }

	
    public Categoria(int catId) {
        this.catId = catId;
    }
    public Categoria(int catId, String descripcion, Set productos) {
       this.catId = catId;
       this.descripcion = descripcion;
       this.productos = productos;
    }
   
    public int getCatId() {
        return this.catId;
    }
    
    public void setCatId(int catId) {
        this.catId = catId;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return descripcion ;
    }

    


}


