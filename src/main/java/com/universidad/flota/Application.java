//Clase de arranque de Sring Boot


package com.universidad.flota;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println("\n\n\n\n salida tester:\n\n\n\n\n");
        BCryptTester tester = new BCryptTester();
        tester.run();
        SpringApplication.run(Application.class, args);
    }
}