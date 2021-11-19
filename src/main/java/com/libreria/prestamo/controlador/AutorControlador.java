package com.libreria.prestamo.controlador;

import com.libreria.prestamo.entidad.Autor;
import com.libreria.prestamo.repositorio.AutorRepositorio;
import com.libreria.prestamo.servicio.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Componente de TIPO Controlador

@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @GetMapping("/")
    public String indexAutor(ModelMap modelo) {  //sirve para mostrar modelo y Traer de la base de datos autores saca informasion de java y la injecta en el html

        List<Autor> autores = autorRepositorio.findAll();

        modelo.put("autores", autores);

        return "autor";
    }

    @GetMapping("/registro")
    public String formulario(ModelMap modelo) {

        List<Autor> autores = autorRepositorio.findAll();

        modelo.put("autores", autores);

        return "autor";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo, @RequestParam String nombreAutor) {

        try {
            autorServicio.guardar(nombreAutor);

            modelo.put("exito", "Registro Exitoso¡");

            return "autor";

        } catch (Exception e) {

            modelo.put("error", "Error. Falta algun dato¡");

            return "autor";
        }
    }

}
