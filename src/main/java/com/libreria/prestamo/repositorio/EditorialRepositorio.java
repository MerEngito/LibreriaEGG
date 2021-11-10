package com.libreria.prestamo.repositorio;

import com.libreria.prestamo.entidad.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, Long> {
    
    
    

}
