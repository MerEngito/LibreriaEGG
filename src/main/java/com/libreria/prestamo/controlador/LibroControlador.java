package com.libreria.prestamo.controlador;


import com.libreria.prestamo.entidad.Libro;
import com.libreria.prestamo.repositorio.LibroRepositorio;
import com.libreria.prestamo.servicio.LibroServicio;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller // Componente de TIPO Controlador

@RequestMapping("/libro") // (ANOTACION) Configura cual es la url que va eschuchar este controlador va a escuchar a partir de la barra
public class LibroControlador {

    
    
    @Autowired
    private LibroServicio libroServicio;
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @GetMapping("/")
    public String indexAutor(ModelMap modelo) {  //sirve para mostrar modelo y Traer de la base de datos autores saca informasion de java y la injecta en el html

          List<Libro> libros = libroRepositorio.findAll();
       
        modelo.put("libros", libros);

        return "libro";
    }
    
     @GetMapping("/registro")
     public String formulario(ModelMap modelo) {

        List<Libro> libros = libroRepositorio.findAll();

        modelo.put("libros", libros);

        return "libro";
    }
        
        @PostMapping("/registro")
    public String guardar(ModelMap modelo, @RequestParam String titulo,@RequestParam Integer anio) {

        try {
            libroServicio.guardar2(titulo, anio);

            modelo.put("exito", "Registro Exitoso¡");

            return "libro";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelo.put("error", "Error. Falta algun dato¡");

            return "libro";
        }
    }

//    @PostMapping("/registro")
//    public String guardar(ModelMap modelo, @RequestParam String nombreAutor) {
//
//        try {
//            libroServicio.guardar();
//
//            modelo.put("exito", "Registro Exitoso¡");
//
//            return "libro";
//
//        } catch (Exception e) {
//
//            modelo.put("error", "Error. Falta algun dato¡");
//
//            return "autor";
//        }
//    }
//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    
//    @GetMapping("/")
//    public String indexAutor(ModelMap modelo) {  //sirve para mostrar modelo y Traer de la base de datos autores saca informasion de java y la injecta en el html
//
//          List<Libro> libros = LibroRepositorio.
//       
//        //modelo.put("libros", libros);
//
//        return "libro";
//    }
    
    
//
//    @GetMapping("/registro")
//    public String formulario() {
//
//        return "form-libro";
//    }
//    
//    @PostMapping("/registro")
//    public String guardar(ModelMap modelo,@RequestParam MultipartFile archivo, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes, @RequestParam String nombreAutor, @RequestParam String nombreEditorial){
//        
//        try {
//            libroServicio.guardar(archivo, titulo, anio,ejemplares , ejemplaresPrestados, ejemplaresPrestados, nombreAutor, nombreEditorial);
//        
//            modelo.put("exito", "Registro Exitoso¡" );
//            
//            return "form-libro";
//       
//        } catch (Exception e){
//            
//            modelo.put("error", "Error. Falta algun dato¡" );
//           
//            return "form-libro";
//        }
//    }
    
     


//    public String listarLibros(ModelMap model){
//        
//        List<Libro> listadoLibros = ls.listarLibros();
//        
//        model.addAttribute("lista", listadoLibros);
//        return "/lista";
//    }
//    
//    @GetMapping("/buscarlibro")
//    public String buscarLibro(){
//        
//        return "index.html";
//    }
//   
