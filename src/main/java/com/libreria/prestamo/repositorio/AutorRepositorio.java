package com.libreria.prestamo.repositorio;

import com.libreria.prestamo.entidad.Autor;
//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long>{
    @Query("SELECT c FROM Autor c WHERE c.nombre=:nombre")
    public Autor buscarAutor(String nombre);
    
//    @Query("SELECT c FROM Autor c WHERE c.nombre LIKE %:nombre%")
//    public List<Autor> listarAutoresPorNombre(@Param("nombre")String nombre);
    
    

        
        
  

}
