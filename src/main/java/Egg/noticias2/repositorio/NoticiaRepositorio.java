
package Egg.noticias2.repositorio;

import Egg.noticias2.entidades.Noticia;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {
    
//        @Query("select n from where n.titulo = : titulo")
//    public Noticia buscarPorTitulo (@Param("titulo") String titulo);
//        
//   @Query ("select n from where n.IdNoticia= IdNoticia")
//   public List<Noticia> buscarPorIdNoticia(@Param ("IdNoticia") String IdNoticia);
    
}
