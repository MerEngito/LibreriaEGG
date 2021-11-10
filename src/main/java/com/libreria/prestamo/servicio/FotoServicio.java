package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Foto;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.repositorio.FotoRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional
    public Foto guardarFoto(MultipartFile archivo) throws Excepciones {

        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombreFoto(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    
    @Transactional
    public Foto actualizarFoto(Long idFoto, MultipartFile archivo) throws Excepciones {

        if (archivo != null) {
            try {
                Foto foto = new Foto();

                if (idFoto != null || idFoto.toString().isEmpty()) {

                    Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);

                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                }

                foto.setMime(archivo.getContentType());
                foto.setNombreFoto(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
