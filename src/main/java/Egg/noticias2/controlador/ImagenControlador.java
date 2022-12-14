
package Egg.noticias2.controlador;

import Egg.noticias2.entidades.Noticia;
import Egg.noticias2.servicio.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/imagen")   
public class ImagenControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/noticia/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) {

        Noticia noticia = noticiaServicio.getOne(id);

        byte[] imagen = noticia.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

}
