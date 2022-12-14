
package Egg.noticias2.controlador;

import Egg.noticias2.entidades.Seccion;
import Egg.noticias2.excepciones.MiException;
import Egg.noticias2.servicio.SeccionServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller 
@RequestMapping("/seccion")
public class SeccionControlador {
    
    @Autowired
    private SeccionServicio seccionServicio;

    @GetMapping("/registrar")
    public String crearSeccion() {
        return "seccion_form";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {
        List<Seccion> secciones = seccionServicio.listarSeccion();
        modelo.addAttribute("secciones", secciones);
        return "listar_seccion";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        
        try {
            seccionServicio.crearSeccion(nombre);
            modelo.put("exito", "Seccion cargada exitosamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "seccion_form";
        }
        return "index";
    }


    
}
