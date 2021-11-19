package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Editorial;
import com.libreria.prestamo.repositorio.EditorialRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public Editorial guardar(String nombreEditorial) {
        Editorial editorial = new Editorial();

        editorial.setNombreEditorial(nombreEditorial);
        editorial.setAlta(new Date());
        editorialRepositorio.save(editorial);
        
        return editorial;
    
    }
}
