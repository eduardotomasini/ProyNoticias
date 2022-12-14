package Egg.noticias2.servicio;

import Egg.noticias2.entidades.Periodista;
import Egg.noticias2.excepciones.MiException;
import Egg.noticias2.repositorio.PeriodistaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodistaServicio {

    @Autowired

    private PeriodistaRepositorio periodistaRepositorio;

    @Transactional
    public void crearPeriodista(String nombre) throws MiException {

        validar(nombre);

        Periodista periodista = new Periodista();

        periodista.setNombre(nombre);

        periodistaRepositorio.save(periodista);

    }

    public List<Periodista> listarPeriodista() {

        List<Periodista> periodistas = new ArrayList();

        periodistas = periodistaRepositorio.findAll();

        return periodistas;

    }

    public void modificarPeriodista(String nombre, String id) throws MiException {

        validar(nombre);

        Optional<Periodista> respuesta = periodistaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Periodista periodista = respuesta.get();

            periodista.setNombre(nombre);

            periodistaRepositorio.save(periodista);
        }
    }


    
    public Periodista getOne (String id){
        return periodistaRepositorio.getOne(id);
    }
    
    
    public void validar (String nombre) throws MiException{
        
        if (nombre.isEmpty() || nombre==null) {
            throw new MiException("el nombre del Periodista no puede nulo o vacio"); 
        }
    }
    
}
