package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Autor;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.repositorio.AutorRepositorio;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;
    
 
    @Transactional
   public Autor guardar(String nombreAutor) throws Exception {

        System.out.println("Estamos dentro del metodo guardar Autor");
       
        validar(nombreAutor);

        Autor autor = new Autor();

        autor.setNombreAutor(nombreAutor);
        autor.setAlta(new Date());
        
        autorRepositorio.save(autor);
        
        System.out.println("Que paso con el AUTOR");
        
        return autor;
    }

    private void validar(String nombreAutor) throws Excepciones {

        if (nombreAutor == null || nombreAutor.isEmpty()) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
//        if (alta == null || alta.toString().isEmpty()) {
//
//            throw new Excepciones("El nombre no puede ser nulo");
//
//        }
    }
    
//    @Transactional(readOnly = true)
//    public List<Autor> buscarAutor(){
//        return ;
//    }
//    
}
