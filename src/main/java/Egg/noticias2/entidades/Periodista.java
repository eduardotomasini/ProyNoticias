
package Egg.noticias2.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;



@Entity 
public class Periodista {
    
     @Id
    @GeneratedValue (generator = "uuid")
    @GenericGenerator(name= "uuid" , strategy= "uuid2" )
    private String id;
    
    private String Nombre;

    public Periodista() {
    }

    public Periodista( String Nombre) {
       
        this.Nombre = Nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
  
    
    
    
}
