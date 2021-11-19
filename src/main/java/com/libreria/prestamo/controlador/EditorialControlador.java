package com.libreria.prestamo.controlador;

import com.libreria.prestamo.entidad.Autor;
import com.libreria.prestamo.entidad.Editorial;
import com.libreria.prestamo.repositorio.AutorRepositorio;
import com.libreria.prestamo.repositorio.EditorialRepositorio;
import com.libreria.prestamo.servicio.AutorServicio;
import com.libreria.prestamo.servicio.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Componente de TIPO Controlador

@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @GetMapping("/")
    public String indexEditorial(ModelMap modelo) {  //sirve para mostrar modelo y Traer de la base de datos autores saca informasion de java y la injecta en el html

        List<Editorial> editoriales = editorialRepositorio.findAll();

        modelo.put("editoriales", editoriales);

        return "editorial";
    }

    @GetMapping("/registro")
    public String formulario(ModelMap modelo) {

        List<Editorial> editoriales = editorialRepositorio.findAll();

        modelo.put("editoriales", editoriales);

        return "editorial";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo, @RequestParam String nombreEditorial) {

        try {
            editorialServicio.guardar(nombreEditorial);

            modelo.put("exito", "Registro Exitoso¡");

            return "editorial";

        } catch (Exception e) {

            modelo.put("error", "Error. Falta algun dato¡");

            return "editorial";
        }
    }

}
