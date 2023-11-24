package com.backend.alquicancha;

import com.backend.alquicancha.entity.Usuario;
import com.backend.alquicancha.repository.IUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.sql.SQLException;
import java.time.LocalDate;

@SpringBootApplication

public class AlquiCanchaApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlquiCanchaApplication.class);


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        SpringApplication.run(AlquiCanchaApplication.class, args);

        LOGGER.info("Running ...");
    }

    @Bean
    public CommandLineRunner initData(IUsuarioRepository usuarioRepository) {
        return (args) -> {

            Usuario usuario = new Usuario("nacho", "martinez", "martinezignacio@hotmail.com", "123456", 325847, LocalDate.now(), true, "calle", 125, "Miramar");
            usuario.setPassword("123456");
            usuarioRepository.save(usuario);


        };
    }
}
