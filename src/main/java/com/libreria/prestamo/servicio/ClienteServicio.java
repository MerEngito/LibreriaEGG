package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Cliente;
import com.libreria.prestamo.entidad.Foto;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.repositorio.ClienteRepositorio;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteServicio {//implements UserDetailsService{

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private FotoServicio fotoServicio;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Cliente registrarCliente(MultipartFile archivo, String documento, String nombreCliente, String apellido, String telefono, String clave, String mail) throws Exception {

        validar(documento, nombreCliente, apellido, telefono, clave, mail);

        Cliente cliente = new Cliente();

        cliente.setDocumento(documento);
        cliente.setNombreCliente(nombreCliente);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setAlta(new Date());
        cliente.setMail(mail);
        cliente.setClave(clave);

        Foto foto = fotoServicio.guardarFoto(archivo);
        cliente.setFoto(foto);

        return clienteRepositorio.save(cliente);
    }

    @Transactional
    private void modificarCliente(MultipartFile archivo, Long id, String documento, String nombreCliente, String apellido, String telefono, String clave, String mail) throws Exception {
        validar(documento, nombreCliente, apellido, telefono, clave, mail);

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setDocumento(documento);
            cliente.setNombreCliente(nombreCliente);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            cliente.setClave(clave);
            cliente.setMail(mail);
            cliente.setAlta(new Date());

            String idFoto = null;
            if (cliente.getFoto() != null) {
                idFoto = cliente.getFoto().getId().toString();
            }

            Foto foto = fotoServicio.actualizarFoto(id, archivo);
            cliente.setFoto(foto);

            clienteRepositorio.save(cliente);
        } else {
            throw new Excepciones("No se Encontro el Cliente Solicitado. ");
        }
    }


    @Transactional
    private void deshabilitar(Long id) throws Excepciones {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setBaja(new Date());
            clienteRepositorio.save(cliente);
        } else {
            throw new Excepciones("No se encontro el cliente solicitado");
        }
    }

    @Transactional
    private void habilitar(Long id) throws Excepciones {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setBaja(null);
            clienteRepositorio.save(cliente);
        } else {
            throw new Excepciones("No se encontro el cliente solicitado");
        }
    }

    private void validar(String documento, String nombreCliente, String apellido, String telefono, String clave, String mail) throws Excepciones {

        if (documento == null || documento.isEmpty()) {

            throw new Excepciones("El Documento no puede ser nulo");
        }
        if (nombreCliente == null || nombreCliente.isEmpty()) {

            throw new Excepciones("El nombre no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {

            throw new Excepciones("El Apellido no puede ser nulo");
        }
        if (telefono == null || telefono.isEmpty()) {

            throw new Excepciones("El Telefono no puede ser nulo");
        }
        if (mail == null || mail.isEmpty()) {

            throw new Excepciones("El Mail no puede ser nulo");
        }
        if (clave == null || clave.isEmpty() || clave.length() <= 6) {

            throw new Excepciones("La Clave no puede ser nulo y tiene que tener mas de 6 digitos");
        }

    }

    private Cliente buscarPorMail(String mail){
        
        
        
        return null;
    }
    
    
    
//    @Override
//    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
//            
//        
//             Cliente cliente = clienteRepositorio.buscarPorMail(mail);
//             if(cliente != null){
//           
//            List<GrantedAuthority> permisos = new ArrayList<>();
//                    
//            GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_PRESTAMO");
//            permisos.add(p1);
//            
//            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_PRESTAMO");
//            permisos.add(p2);
//            
//            GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_PRESTAMO");
//            permisos.add(p3);
//            
//           User user = new User(cliente.getMail(), cliente.getClave(), permisos);
//           return user;
//        }else{
//            return null;
//        }
//    }
}