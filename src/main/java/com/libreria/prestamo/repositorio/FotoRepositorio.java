package com.libreria.prestamo.repositorio;

import com.libreria.prestamo.entidad.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String>{

    
    
    
}
