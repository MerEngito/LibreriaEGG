
//
//import com.libreria.prestamo.servicio.ClienteServicio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


package com.libreria.prestamo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrestamoApplication {

    
	public static void main(String[] args) {
		SpringApplication.run(PrestamoApplication.class, args);
	}

}


//@SpringBootApplication
//public class PrestamoApplication {
//
//    @Autowired
//    private ClienteServicio clienteServicio;
//
//    public static void main(String[] args) {
//        SpringApplication.run(PrestamoApplication.class, args);
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(clienteServicio)
//                .passwordEncoder(new BCryptPasswordEncoder());
//
//    }

//package com.libreria.prestamo;
