
package Egg.noticias2.servicio;

import Egg.noticias2.entidades.Usuario;
import Egg.noticias2.enumeraciones.Rol;
import Egg.noticias2.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    
    @Transactional
    public void registar (String nombre, String email, String password
    , String password2) throws Exception{
        
        validar(nombre, email, password, password2);
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        
        usuario.setRol(Rol.USER);
        
        usuarioRepositorio.save(usuario);
        
    }
    
    public List <Usuario> listarUsuario(){
        
        List<Usuario> usuarios = new ArrayList();
        usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }
    
    public void validar(String nombre, String email, String password, String password2) throws Exception{
        
        if (nombre.isEmpty()|| nombre==null) {
            throw new Exception("el nombre no puede ser nulo o vacio");
        }
        if (email.isEmpty()|| email==null) {
            throw new Exception("el email no puede ser nulo o vacio");            
        }
        if (password.isEmpty()|| password==null ||password.length()<=5 ) {
               throw new Exception("el password no puede ser nulo o vacio o tener menos de 5 caracteres"); 
        }
        if (!password.equals(password2)) {
            throw new Exception("el password no coincide");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
       
        if (usuario != null) {
                 
            List<GrantedAuthority> permisos = new ArrayList<>();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
            
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
               
            HttpSession session = attr.getRequest().getSession(true);
                                  
            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
            
        } else {
            return null;
        }
        
    }


}
