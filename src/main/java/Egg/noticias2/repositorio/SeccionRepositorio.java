
package Egg.noticias2.repositorio;

import Egg.noticias2.entidades.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeccionRepositorio extends JpaRepository<Seccion, String>{
    
}
