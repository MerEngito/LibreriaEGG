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
    public Editorial guardar(String nombre) {
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);
        editorial.setAlta(new Date());
        return editorialRepositorio.save(editorial);
    }
}
