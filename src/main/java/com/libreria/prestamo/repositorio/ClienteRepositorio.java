package com.libreria.prestamo.repositorio;

import com.libreria.prestamo.entidad.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    
@Query("SELECT c FROM Cliente c WHERE c.mail = :mail")
public Cliente buscarPorMail(@Param("mail") String mail);




//    @Query("SELECT c FROM Autor c WHERE c.nombre LIKE %:nombre%")
//    public List<Autor> listarAutoresPorNombre(@Param("nombre")String nombre);
//  
//    
    

}
