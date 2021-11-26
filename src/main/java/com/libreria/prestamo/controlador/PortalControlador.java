package com.libreria.prestamo.controlador;

import com.libreria.prestamo.entidad.Cliente;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.servicio.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



//@RequestMapping("/") // (ANOTACION) Configura cual es la url que va eschuchar este controlador va a escuchar a partir de la barra
@Controller // Componente de TIPO Controlador
public class PortalControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Cliente> usuariosActivos = clienteServicio.todosLosClientes();
        //Recordar que utilizo el modelo,para viajar con la llave cliente al HTML la lista usuariosactivos
        modelo.addAttribute("usuarios", usuariosActivos);
        return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) {
    	
    	List<Cliente> cliente = clienteServicio.todosLosClientes();
    	
    	modelo.addAttribute("cliente", cliente);
    	
        return "inicio.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente.");
        }
        return "login.html";
    }


    @PostMapping("/registrar")
    public String registrarCliente(ModelMap modelo, MultipartFile archivo, @RequestParam String documento, @RequestParam String nombreCliente, @RequestParam String apellido, @RequestParam String telefono, @RequestParam String clave, @RequestParam String mail) throws Exception {

       try {
            clienteServicio.registrarCliente(archivo, documento, nombreCliente, apellido, telefono, clave, mail);
            
        } catch (Excepciones ex) {
           
            modelo.put("error", ex.getMessage());
            
            modelo.put("documento", documento);
            modelo.put("nombreCliente", nombreCliente);
            modelo.put("apellido", apellido);
            modelo.put("telefono", telefono);
            modelo.put("clave", clave);
            modelo.put("mail", mail);
            return "registro.html";
        }
        modelo.put("titulo", "Bienvenido a Libreria");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito.html";
    }
    
    
    
}



// @GetMapping("/")
//    public String index() { // Metodo index 
//        return "index.html";
//    }
    
//    @GetMapping("/registro")
//    public String registro(ModelMap modelo) {
//        List<Zona> zonas = zonaRepositorio.findAll();
//        modelo.put("zonas", zonas);
//        return "registro.html";
//    }