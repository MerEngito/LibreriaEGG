package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Libro;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class LibroServicio {

    @Autowired
    LibroRepositorio libroRepositorio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Libro guardar(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws Exception {

        validar(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);

        Libro libro = new Libro();

        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);

        return libroRepositorio.save(libro);
    }

    private void validar(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws Excepciones {

        if (titulo == null || titulo.isEmpty()) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
        if (anio == null || anio.toString().isEmpty()) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
        if (ejemplares == null || ejemplares.equals(0)) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
        if (ejemplaresPrestados == null || ejemplaresPrestados.equals(0)) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
        if (ejemplaresRestantes == null || ejemplaresRestantes.equals(0)) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
    }

}

