package com.loja.virtual;

import com.loja.virtual.domain.Endereco;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VirtualApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualApplication.class, args);

        System.out.println("BANCO RODANDO :)");

    }

}
