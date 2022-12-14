
package Egg.noticias2.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;


@Entity 
public class Seccion {
    
    @Id
    @GeneratedValue (generator = "uuid")
    @GenericGenerator(name= "uuid" , strategy= "uuid2" )
    private String id;
    
    private String nombre;

    public Seccion() {
    }

    public Seccion( String nombre) {
   
        this.nombre = nombre;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
