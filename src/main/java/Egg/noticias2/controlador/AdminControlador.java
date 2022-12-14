
package Egg.noticias2.controlador;

import Egg.noticias2.entidades.Usuario;
import Egg.noticias2.servicio.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/dashboard")
    public String panelAdministrador(){
        return "panel.html";
    }
    
    @GetMapping("/usuarios")
    public String listarUsuario(ModelMap modelo){
        List<Usuario> usuarios = usuarioServicio.listarUsuario();
        
        modelo.addAttribute("usuarios", usuarios);
        
        return "listar_usuarios.html";
    }
    
}
