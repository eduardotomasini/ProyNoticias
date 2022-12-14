
package Egg.noticias2.servicio;

import Egg.noticias2.entidades.Seccion;
import Egg.noticias2.excepciones.MiException;
import Egg.noticias2.repositorio.SeccionRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SeccionServicio {
    
    @Autowired
    private SeccionRepositorio seccionRepositorio;
    
    @Transactional
    public void crearSeccion (String nombre) throws MiException{
        validar(nombre);
        Seccion seccion = new Seccion();
        seccion.setNombre(nombre);
        seccionRepositorio.save(seccion);
    }

    public List<Seccion> listarSeccion (){
        
        List<Seccion> secciones = new ArrayList();
        
        secciones = seccionRepositorio.findAll();
        
        return secciones;
    }
    
    public void modificarSeccion(String id, String nombre) throws MiException{
        
        validar(nombre);
        
        Optional <Seccion> respuesta = seccionRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Seccion seccion = respuesta.get();
            
            seccion.setNombre(nombre);
            
            seccionRepositorio.save(seccion);
            
        }
        
    }
    
    
    private void validar(String nombre) throws MiException {
        
        if (nombre.isEmpty()|| nombre == null) {
            throw new MiException("el nombre de la Seccion no puede ser nulo o vacio");
        }
    }
    
}
