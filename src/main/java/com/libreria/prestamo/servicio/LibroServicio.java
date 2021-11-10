package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Foto;
import com.libreria.prestamo.entidad.Libro;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.repositorio.LibroRepositorio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private FotoServicio fotoServicio;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Libro guardar(MultipartFile archivo, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws Exception {

        validar(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);

        Libro libro = new Libro();

        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);
        libro.setAlta(new Date());
        
        Foto foto = fotoServicio.guardarFoto(archivo);
        libro.setFoto(foto);
        
        return libroRepositorio.save(libro);
    }
    
    @Transactional (readOnly = true)
    private List<Libro> listarLibros(){
        return  libroRepositorio.findAll();
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

