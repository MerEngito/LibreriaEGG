package com.libreria.prestamo.repositorio;

import com.libreria.prestamo.entidad.Libro;
//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

    @Repository
    public interface LibroRepositorio extends JpaRepository<Libro, Long> {

        @Query("SELECT c FROM Libro c WHERE c.titulo=:titulo")
        public Libro buscarLibro(String nombre);

//        @Query("SELECT c FROM Libro c WHERE c.nombre LIKE %:nombre%")
//        public List<Libro> listaLibroporNombre(@Param("nombre") String nombre);

    }
  
	

