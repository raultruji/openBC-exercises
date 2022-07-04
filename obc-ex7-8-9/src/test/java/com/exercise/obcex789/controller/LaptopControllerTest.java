package com.exercise.obcex789.controller;

import com.exercise.obcex789.entities.Laptop;
import com.exercise.obcex789.repository.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private LaptopRepository repo;
    private	Laptop prueba = new Laptop(null,"prueba",8,800.50,false );

    //Spring inyecta el puerto http con esta anotacion
    @LocalServerPort
    private int port;

    // inicializa antes de cada método
    @BeforeEach
    void setUp() {
        restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("http://localhost:8081/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println("list size = "+laptops.size());
    }

    @Test
    void findOneById() {
        ResponseEntity response1 =
                testRestTemplate.getForEntity("http://localhost:8081/api/laptops/1", Laptop.class);
        ResponseEntity response2 =
                testRestTemplate.getForEntity("http://localhost:8081/api/laptops/1321321313131", Laptop.class);
        ResponseEntity response3 =
                testRestTemplate.getForEntity("http://localhost:8081/api/laptops/s", Laptop.class);

        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, response3.getStatusCode());
    }

    @DisplayName("Comprobar creacion de laptops desde laptopController test")
    @Test
    void create() {

        //Hay que indicar que vamos a enviar json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "processor": "Laptop creado desde Spring Test",
                    "ram": 8,
                    "price": 1250.5,
                    "ssd": true
                  }
                """;

        // Se crea una request desde java indicando json y cabeceras
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        // ejecutando la peticion mediante post y que retorna Laptop
        ResponseEntity<Laptop> response = testRestTemplate.exchange("http://localhost:8081/api/laptops",HttpMethod.POST,request,Laptop.class);
        Laptop result = response.getBody();

        assertEquals("Laptop creado desde Spring Test",result.getProcessor());

    }

    @DisplayName("Comprobar actualizar Laptop desde LaptopController test")
    @Test
    void update() {
        //Hay que indicar que vamos a enviar json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": 1,
                    "processor": "Laptop actualizado desde Spring Test",
                    "ram": 32,
                    "price": 2000.5,
                    "ssd": true
                  }
                """;
        // Se crea una request desde java indicando json y cabeceras
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        // ejecutando la peticion mediante PUT y que retorna Laptop
        ResponseEntity<Laptop> response =
                testRestTemplate.exchange("http://localhost:8081/api/laptops",HttpMethod.PUT,request,Laptop.class);
        Laptop result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void deleteById() {

        repo.save(prueba);
        repo.deleteById(prueba.getId());
        assertEquals(false,repo.existsById(prueba.getId()));
    }

    @Test
    void deleteAll() {

        repo.deleteAll();
        assertEquals(repo.count(),0);

    }
}