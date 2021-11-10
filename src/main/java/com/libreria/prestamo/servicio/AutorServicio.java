package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Autor;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.repositorio.AutorRepositorio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;
    
 
    @Transactional
   public Autor guardar(String nombre, Boolean alta) throws Exception {

        validar(nombre, alta);

        Autor autor = new Autor();

        autor.setNombre(nombre);
        autor.setAlta(new Date());

        return autorRepositorio.save(autor);
    }

    private void validar(String nombre, Boolean alta) throws Excepciones {

        if (nombre == null || nombre.isEmpty()) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
        if (alta == null || alta.toString().isEmpty()) {

            throw new Excepciones("El nombre no puede ser nulo");

        }
    }
    
//    @Transactional(readOnly = true)
//    public List<Autor> buscarAutor(){
//        return ;
//    }
//    
}
