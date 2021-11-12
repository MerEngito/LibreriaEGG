package com.libreria.prestamo.controlador;

import com.libreria.prestamo.entidad.Libro;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Componente de TIPO Controlador

@RequestMapping("/") // (ANOTACION) Configura cual es la url que va eschuchar este controlador va a escuchar a partir de la barra
public class LibroControlador {
    
//    public String listarLibros(ModelMap model){
//        
//        List<Libro> listadoLibros = ls.listarLibros();
//        
//        model.addAttribute("lista", listadoLibros);
//        return "/lista";
//    }
//    
    @GetMapping("/buscarlibro")
    public String buscarLibro(){
        
        return "index.html";
    }
     
   

    

}
