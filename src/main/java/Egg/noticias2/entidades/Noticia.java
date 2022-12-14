
package Egg.noticias2.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity 
public class Noticia {
    
    @Id
    @GeneratedValue (generator = "uuid")
    @GenericGenerator(name= "uuid" , strategy= "uuid2" )
    private String IdNoticia;
    
    private String titulo;
    private String cuerpo;
    private boolean eliminado;
    
    @Temporal (TemporalType.DATE)
    private Date alta;
    
    @ManyToOne
    private Periodista periodista;
    
    
    @ManyToOne
    private Seccion seccion;
    
    @OneToOne
    private Imagen imagen;
    
    

    public Noticia() {
    }

    public Noticia(String IdNoticia, String titulo, String cuerpo, boolean eliminado, Date alta, Periodista periodista, Seccion seccion, Imagen imagen) {
        this.IdNoticia = IdNoticia;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.eliminado = eliminado;
        this.alta = alta;
        this.periodista = periodista;
        this.seccion = seccion;
        this.imagen = imagen;
    }

 

    public String getIdNoticia() {
        return IdNoticia;
    }

    public void setIdNoticia(String IdNoticia) {
        this.IdNoticia = IdNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

}
