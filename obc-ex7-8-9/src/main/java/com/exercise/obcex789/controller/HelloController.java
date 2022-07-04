package com.exercise.obcex789.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ejercicio 1
 * Crear una clase HelloController que sea un controlador REST.
 * Dentro de la clase crear un método que retorne un saludo.
 * Probar que retorna el saludo desde el navegador y desde Postman.
 */
@RestController
public class HelloController {
    @GetMapping("/api/ex04")
    public String saludar(){
        return "Hola buenas!!";

    }
}
