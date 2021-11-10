package com.libreria.prestamo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Componente de TIPO Controlador

@RequestMapping("/") // (ANOTACION) Configura cual es la url que va eschuchar este controlador va a escuchar a partir de la barra
public class PortalControlador {

    @GetMapping("/")
    public String index() { // Metodo index 
        return "index.html";
    }
}
