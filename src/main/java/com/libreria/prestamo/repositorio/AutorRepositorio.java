package com.libreria.prestamo.repositorio;

import com.libreria.prestamo.entidad.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {

    @Query("SELECT c FROM Autor c WHERE c.nombre=:nombre")
    public Autor buscarAutor(String nombre);
}
