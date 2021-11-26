package com.libreria.prestamo.controlador;

import com.libreria.prestamo.entidad.Cliente;
import com.libreria.prestamo.servicio.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/")
    public String index(ModelMap modelo){
        
        List<Cliente> clienteActivo = clienteServicio.todosLosClientes();
        //bRecordar que utilizo el modelo, para viajar con la llave cliente al HTML la lista 
        modelo.addAttribute("cliente", clienteActivo);
        
        return "index.html";
        
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo){
        
        List<Cliente> cliente = clienteServicio.todosLosClientes();
        
        modelo.addAttribute("cliente", cliente);
        
        return "inicio.html";
    }
    
    
    

}
