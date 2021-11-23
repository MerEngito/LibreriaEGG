package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Autor;
import com.libreria.prestamo.entidad.Editorial;
import com.libreria.prestamo.entidad.Foto;
import com.libreria.prestamo.entidad.Libro;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.repositorio.LibroRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
//    public Libro guardar(MultipartFile archivo, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, String nombreAutor, String nombreEditorial) throws Exception {
//
//        validar(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, nombreAutor, nombreEditorial);
//
//        Libro libro = new Libro();
//
//        libro.setTitulo(titulo);
//        libro.setAnio(anio);
//        libro.setEjemplares(ejemplares);
//        libro.setEjemplaresPrestados(ejemplaresPrestados);
//        libro.setEjemplaresRestantes(ejemplaresRestantes);
//        libro.setAlta(new Date());
//
//        Autor autor = autorServicio.guardar(nombreAutor);
//        Foto foto = fotoServicio.guardarFoto(archivo);
//        Editorial editorial = editorialServicio.guardar(nombreEditorial);
//        libro.setAutor(autor);
//        libro.setFoto(foto);
//        libro.setEditorial(editorial);
//
//        return libroRepositorio.save(libro);
//    }
    
     @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
     public Libro guardar( String titulo, String nombreAutor, String nombreEditorial) throws Exception {

         validar(titulo, nombreAutor, nombreEditorial);
         
        Libro libro = new Libro();

        libro.setTitulo(titulo);
        libro.setAlta(new Date());
        Autor autor = autorServicio.guardar(nombreAutor);
        //Foto foto = fotoServicio.guardarFoto(archivo);
        Editorial editorial = editorialServicio.guardar(nombreEditorial);
        libro.setAutor(autor);
       // libro.setFoto(foto);
        libro.setEditorial(editorial);

        return libroRepositorio.save(libro);
     }
     
     
    @Transactional(readOnly = true)
    private List<Libro> listarLibros() {
        return libroRepositorio.findAll();
    }

    @Transactional
    private Libro buscarLibro(long id) throws Exception {

        Optional<Libro> titulo = libroRepositorio.findById(id);

        if (titulo.isPresent()) {
            Libro libros = titulo.get();

            return libros;

        } else {
            throw new Excepciones("No se encontro el usuario solicitado");
        }
    }

//    public List buscaAutor(){
//        return libroRepositorio.buscaAutor();
//    }
    private void validar(String titulo, String autor, String editorial) throws Excepciones {

        if (titulo == null || titulo.isEmpty()) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
//        if (anio == null || anio.toString().isEmpty()) {
//
//            throw new Excepciones("El nombre no puede ser nulo");
//        }
//        if (ejemplares == null || ejemplares.equals(0)) {
//
//            throw new Excepciones("El nombre no puede ser nulo");
//        }
//        if (ejemplaresPrestados == null || ejemplaresPrestados.equals(0)) {
//
//            throw new Excepciones("El nombre no puede ser nulo");
//        }
//        if (ejemplaresRestantes == null || ejemplaresRestantes.equals(0)) {
//
//            throw new Excepciones("El nombre no puede ser nulo");
//        }
        if (autor == null || autor.isEmpty()) {

            throw new Excepciones("El nombre del autor no puede ser nulo");
        }
        if (editorial == null || editorial.isEmpty()) {

            throw new Excepciones("El nombre de la editorial no puede ser nulo");
        }
    }
}

