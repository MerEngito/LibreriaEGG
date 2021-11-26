package com.libreria.prestamo.controlador;

import com.libreria.prestamo.entidad.Cliente;
import com.libreria.prestamo.servicio.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/dashboard")
    public String inicioAdmin(ModelMap modelo) {

        List<Cliente> cliente = clienteServicio.todosLosClientes();

        modelo.put("usuarios", cliente);

        return "inicioAdmin";
    }

    @GetMapping("/habilitar/{id}")
    public String habilitar(ModelMap modelo, @PathVariable String id) {
        try {
            clienteServicio.habilitar(id);
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            modelo.put("error", "No fue posible habilitar");
            return "inicioAdmin";
        }
    }

    @GetMapping("/deshabilitar/{id}")
    public String deshabilitar(ModelMap modelo, @PathVariable String id) {
        try {
            clienteServicio.deshabilitar(id);
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            modelo.put("error", "No fue posible deshabilitar");
            return "inicioAdmin";
        }
    }

    @GetMapping("/cambiar_rol/{id}")
    public String cambiarRol(ModelMap modelo, @PathVariable String id) {
        try {
            clienteServicio.cambiarRol(id);
            modelo.put("exito", "Rol modificado con Exito!");
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            modelo.put("error", "No fue posible reasignar el rol");
            return "inicioAdmin";
        }
    }

}
