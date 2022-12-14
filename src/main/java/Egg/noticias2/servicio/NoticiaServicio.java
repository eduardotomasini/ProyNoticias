
package Egg.noticias2.servicio;

import Egg.noticias2.entidades.Imagen;
import Egg.noticias2.entidades.Noticia;
import Egg.noticias2.entidades.Periodista;
import Egg.noticias2.entidades.Seccion;
import Egg.noticias2.repositorio.NoticiaRepositorio;
import Egg.noticias2.excepciones.MiException;
import Egg.noticias2.repositorio.ImagenRepositorio;
import Egg.noticias2.repositorio.PeriodistaRepositorio;
import Egg.noticias2.repositorio.SeccionRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Autowired
    private PeriodistaRepositorio periodistaRepositorio;
    
    @Autowired
    private SeccionRepositorio seccionRepositorio;
    
    @Autowired
    private ImagenServicio imagenServicio;
    
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    
    @Transactional
    public void crearNoticia(MultipartFile archivo, String titulo, String cuerpo, String idPeriodista, String idSeccion) throws MiException {

        validar(titulo, cuerpo, idPeriodista, idSeccion, archivo);
        
        Optional<Periodista> respuestaPeriodista = periodistaRepositorio.findById(idPeriodista);
        Optional<Seccion> respuestaSeccion = seccionRepositorio.findById(idSeccion);
        
        Periodista periodista = new Periodista();
        Seccion seccion = new Seccion();
         
        if (respuestaPeriodista.isPresent()) {
            
            periodista = respuestaPeriodista.get();
        }
        
        if (respuestaSeccion.isPresent()) {
            
            seccion = respuestaSeccion.get();
        }
        
        
        Noticia noticia = new Noticia();
        Imagen imagen = imagenServicio.guardar(archivo);
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(new Date());
        noticia.setEliminado(true);
        noticia.setPeriodista(periodista);
        noticia.setSeccion(seccion);
        noticia.setImagen(imagen);

        noticiaRepositorio.save(noticia);
    }

    public List<Noticia> listarNoticias() {

        List<Noticia> noticias = new ArrayList();

        noticias = noticiaRepositorio.findAll();

        return noticias;

    }

    @Transactional
    public void modificarNoticia(MultipartFile archivo,  String IdNoticia, String titulo, String cuerpo, String idPeriodista, String idSeccion) throws MiException {

        validar(titulo, cuerpo,  idPeriodista,  idSeccion, archivo);

        Optional<Noticia> respuesta = noticiaRepositorio.findById(IdNoticia);
        Optional<Periodista> respuestaPeriodista = periodistaRepositorio.findById(idPeriodista);
        Optional<Seccion> respuestaSeccion = seccionRepositorio.findById(idSeccion);
        Optional<Imagen> respuestaImagen = imagenRepositorio.findById(archivo);
        
        Periodista periodista = new Periodista();
        Seccion seccion = new Seccion();
        
        if (respuestaPeriodista.isPresent()) {
            
            periodista = respuestaPeriodista.get();
        }
        
        if (respuestaSeccion.isPresent()) {
            
            seccion = respuestaSeccion.get();
        }
        
       
        
        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            
            String idImagen = null;
            
            if (noticia.getImagen() != null) {
                idImagen = noticia.getImagen().getId();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setImagen(imagen);
            
            noticia.setPeriodista(periodista);
            noticia.setSeccion(seccion);

            noticiaRepositorio.save(noticia);
        }

    }

    public Noticia getOne (String IdNoticia){
    
        return noticiaRepositorio.getOne(IdNoticia);
    }
    
    public void eliminar (String IdNoticia){
        noticiaRepositorio.deleteById(IdNoticia);
                
    }
    
    public void validar( String titulo, String cuerpo,  String idPeriodista, String idSeccion, MultipartFile archivo) throws MiException {

//        if (IdNoticia == null) {
//            throw new MiException("el IdNoticia de la noticia no puede ser nulo");
//        }
//         tambien tuve que corregir este metodo xq no me corria el M.contralodor
        if (idSeccion == null || idSeccion.isEmpty()) {
            throw new MiException("la Seccion de la noticia no puede ser nula o vacia");
        }

        if (idPeriodista == null || idPeriodista.isEmpty()) {
            throw new MiException("el Periodista de la noticia no puede ser nula o vacia");
        }

        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("el titulo de la noticia no puede ser nula o vacia");
        }
        if (cuerpo == null || cuerpo.isEmpty()) {
            throw new MiException("el cuerpo de la noticia no puede ser nula o vacia");
        }
        
        if (archivo == null || archivo.isEmpty()) {
            throw new MiException("la imagen  de la noticia no puede ser nula o vacia");
        }

    }

    
    
}
