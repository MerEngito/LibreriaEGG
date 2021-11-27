package com.libreria.prestamo.servicio;

import com.libreria.prestamo.entidad.Cliente;
import com.libreria.prestamo.entidad.Foto;
import com.libreria.prestamo.enumeracion.Rol;
import com.libreria.prestamo.excepcion.Excepciones;
import com.libreria.prestamo.repositorio.ClienteRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteServicio implements UserDetailsService {//implements UserDetailsService{


    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Cliente registrarCliente(MultipartFile archivo, String documento, String nombreCliente, String apellido, String telefono, String clave, String mail) throws Exception {

        validar1(documento, nombreCliente, apellido, telefono, clave, mail);

        Cliente cliente = new Cliente();

        cliente.setDocumento(documento);
        cliente.setNombreCliente(nombreCliente);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setAlta(new Date());
        cliente.setMail(mail);
        cliente.setClave(clave);

        cliente.setRol(Rol.USUARIO);

        String encriptada = new BCryptPasswordEncoder().encode(clave);
        cliente.setClave(encriptada);

        Foto foto = fotoServicio.guardarFoto(archivo);
        cliente.setFoto(foto);

        return clienteRepositorio.save(cliente);
    }

    @Transactional
    private void modificarCliente(MultipartFile archivo, String id, String documento, String nombreCliente, String apellido, String telefono, String clave, String mail) throws Exception {
        validar1(documento, nombreCliente, apellido, telefono, clave, mail);

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

            Foto foto = fotoServicio.actualizarFoto(idFoto, archivo);
            cliente.setFoto(foto);

            clienteRepositorio.save(cliente);
        } else {
            throw new Excepciones("No se Encontro el Cliente Solicitado. ");
        }
    }

    private void validar1(String documento, String nombreCliente, String apellido, String telefono, String clave, String mail) throws Excepciones {

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

    public void validar(String nombre, String apellido, String mail, String clave, String clave2) throws Excepciones {

        if (nombre == null || nombre.isEmpty()) {
            throw new Excepciones("El nombre del usuario no puede ser nulo");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new Excepciones("El apellido del usuario no puede ser nulo");
        }

        if (mail == null || mail.isEmpty()) {
            throw new Excepciones("El mail no puede ser nulo");
        }

        if (clave == null || clave.isEmpty() || clave.length() <= 6) {
            throw new Excepciones("La clave del usuario no puede ser nula y tiene que tener mas de seis digitos");
        }
        if (!clave.equals(clave2)) {
            throw new Excepciones("Las claves deben ser iguales");
        }
    }

    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String apellido, String mail, String clave, String clave2) throws Excepciones {

        validar(nombre, apellido, mail, clave, clave2);

        Cliente cliente = new Cliente();
        cliente.setNombreCliente(nombre);
        cliente.setApellido(apellido);
        cliente.setMail(mail);
        cliente.setRol(Rol.USUARIO);

        String encriptada = new BCryptPasswordEncoder().encode(clave);
        cliente.setClave(encriptada);

        cliente.setAlta(new Date());

        Foto foto = fotoServicio.guardarFoto(archivo);
        cliente.setFoto(foto);

        clienteRepositorio.save(cliente);

    }

    @Transactional
    public void modificar(MultipartFile archivo, String id, String nombre, String apellido, String mail, String clave, String clave2) throws Excepciones {

        validar(nombre, apellido, mail, clave, clave2);

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();
            cliente.setNombreCliente(nombre);
            cliente.setApellido(apellido);
            cliente.setMail(mail);

            String encriptada = new BCryptPasswordEncoder().encode(clave);
            cliente.setClave(encriptada);

            String idFoto = null;
            if (cliente.getFoto() != null) {
                idFoto = cliente.getFoto().getId();
            }

            Foto foto = fotoServicio.actualizarFoto(idFoto, archivo);
            cliente.setFoto(foto);

            clienteRepositorio.save(cliente);
        } else {

            throw new Excepciones("No se encontró el usuario solicitado");
        }

    }

    @Transactional
    public void deshabilitar(String id) throws Excepciones {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();
            cliente.setBaja(new Date());
            clienteRepositorio.save(cliente);
        } else {

            throw new Excepciones("No se encontró el usuario solicitado");
        }

    }

    @Transactional
    public void habilitar(String id) throws Excepciones {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();
            cliente.setBaja(null);
            clienteRepositorio.save(cliente);
        } else {

            throw new Excepciones("No se encontró el usuario solicitado");
        }

    }

    @Transactional
    public void cambiarRol(String id) throws Excepciones {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();

            if (cliente.getRol().equals(Rol.USUARIO)) {

                cliente.setRol(Rol.ADMIN);

            } else if (cliente.getRol().equals(Rol.ADMIN)) {
                cliente.setRol(Rol.USUARIO);
            }
        }
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(String id) throws Excepciones {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();
            return cliente;
        } else {

            throw new Excepciones("No se encontró el usuario solicitado");
        }

    }

    @Transactional(readOnly = true)
    public List<Cliente> todosLosClientess() {

        return clienteRepositorio.findAll();

    }

    @Transactional(readOnly = true)
    public List<Cliente> todosLosClientes() {

        return clienteRepositorio.findAll();

    }

    private Cliente buscarPorMail(String mail) {

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        Cliente cliente = clienteRepositorio.buscarPorMail(mail);

        if (cliente != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            //Creo una lista de permisos! 
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + cliente.getRol());
            permisos.add(p1);

            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", cliente); // llave + valor

            User user = new User(cliente.getMail(), cliente.getClave(), permisos);

            return user;

        } else {
            return null;
        }

    }
}

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import java.util.List;
//import java.util.ArrayList;
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
