package com.libreria.prestamo.repositorio;

import com.libreria.prestamo.entidad.Libro;
//import java.util.List;
//import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
}



//    @Query("SELECT c FROM Libro c WHERE c.titulo.id =:id")
//    public List<Libro> buscarLibro(@Param("id") Long id);
//
//    @Query("SELECT a from Libro a WHERE a.activo = true ORDER BY a.titulo")
//    public List<Libro> buscarLibro(@Param("activo") Boolean True);
//
//    @Query("SELECT a FROM Autor a")
//    public List<Libro> buscaAutor();
//
//    public Optional<Libro> buscarLibro(Optional<Libro> titulo);