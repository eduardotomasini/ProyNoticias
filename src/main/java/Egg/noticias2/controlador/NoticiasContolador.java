package Egg.noticias2.controlador;

import Egg.noticias2.entidades.Noticia;
import Egg.noticias2.entidades.Periodista;
import Egg.noticias2.entidades.Seccion;
import Egg.noticias2.excepciones.MiException;
import Egg.noticias2.servicio.NoticiaServicio;
import Egg.noticias2.servicio.PeriodistaServicio;
import Egg.noticias2.servicio.SeccionServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/noticia")
public class NoticiasContolador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @Autowired
    private PeriodistaServicio periodistaServicio;
    
    @Autowired
    private SeccionServicio seccionServicio;


    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {
        
        List <Periodista> periodistas = periodistaServicio.listarPeriodista();
        List <Seccion> secciones =  seccionServicio.listarSeccion();
        
        modelo.addAttribute("periodistas", periodistas);
        modelo.addAttribute("secciones", secciones);
        
        return "noticia_form.html";
    }
    
    @GetMapping ("/listar")
    public String listar(ModelMap modelo) {
        
        List<Noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias);

        return "noticia_list.html";
    }
    
    @PostMapping("/registro")
    public String registro( @RequestParam String titulo, 
            @RequestParam String cuerpo,  @RequestParam String idPeriodista,@RequestParam String idSeccion, ModelMap modelo, MultipartFile archivo) {

        try {
            noticiaServicio.crearNoticia(archivo, titulo, cuerpo, idPeriodista, idSeccion);
    
            modelo.put("exito", "la noticia fue registrada exitosamente");

        } catch (MiException ex) {
            List<Periodista> periodistas = periodistaServicio.listarPeriodista();
            List<Seccion> secciones = seccionServicio.listarSeccion();

            modelo.addAttribute("periodistas", periodistas);
            modelo.addAttribute("secciones", secciones);
            modelo.put("error", ex.getMessage());

            return "noticia_form.html";
        }

        return "index.html";
    }

    @GetMapping("/modificar/{IdNoticia}")
    public String modificar(@PathVariable String IdNoticia, ModelMap modelo) {

        modelo.put("noticia", noticiaServicio.getOne(IdNoticia));

        List<Periodista> periodistas = periodistaServicio.listarPeriodista();
        List<Seccion> secciones = seccionServicio.listarSeccion();

        modelo.addAttribute("periodistas", periodistas);
        modelo.addAttribute("secciones", secciones);

        return "modificar_noticia.html";
    }

    @PostMapping("/modificar/{IdNoticia}")
    public String modificar(@PathVariable String IdNoticia, String titulo, String cuerpo, String idSeccion, String idPeriodista, MultipartFile archivo, ModelMap modelo) {

        try {
            List<Periodista> periodistas = periodistaServicio.listarPeriodista();
            List<Seccion> secciones = seccionServicio.listarSeccion();

            modelo.addAttribute("periodistas", periodistas);
            modelo.addAttribute("secciones", secciones);

            
            noticiaServicio.modificarNoticia(archivo, IdNoticia, titulo, cuerpo, idPeriodista, idSeccion);
            return "redirect:../listar";
        } catch (MiException ex) {
            List<Periodista> periodistas = periodistaServicio.listarPeriodista();
            List<Seccion> secciones = seccionServicio.listarSeccion();

            modelo.addAttribute("periodistas", periodistas);
            modelo.addAttribute("secciones", secciones);
            modelo.put("error", ex.getMessage());
            
            return "modificar_noticia.html";
        }

    }
    
}

