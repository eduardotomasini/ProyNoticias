
package Egg.noticias2.repositorio;

import Egg.noticias2.entidades.Periodista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PeriodistaRepositorio extends JpaRepository<Periodista, String>{
    
}
