package Egg.noticias2.controlador;

import Egg.noticias2.entidades.Periodista;
import Egg.noticias2.excepciones.MiException;
import Egg.noticias2.servicio.PeriodistaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller       
@RequestMapping("/periodista")
public class PeriodistaControlador {

    @Autowired
    private PeriodistaServicio periodistaServicio;

    @GetMapping("/registrar")
    public String registar() {
        return "periodista_form";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {
        List<Periodista> periodistas = periodistaServicio.listarPeriodista();
        modelo.addAttribute("periodistas", periodistas);
        return "listar_periodista";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        
        try {
            periodistaServicio.crearPeriodista(nombre);
            
            modelo.put("exito", "Periodista cargado exitosamente");
        } catch (MiException ex) {
            
            modelo.put("error", ex.getMessage());
            
            return "periodista_form";
        }
        
        return "index";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("periodista", periodistaServicio.getOne(id));
        return "modificar_periodista";
    }

    @PostMapping("/modificar/{id}")
    public String modificar (@PathVariable String id, String nombre, ModelMap modelo){
        
        try {
            periodistaServicio.modificarPeriodista(nombre, id);
            return "redirect:../{id}";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "modificar_periodista";
        }
    }
    
    
}
