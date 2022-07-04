package com.exercise.obcex789.controller;

import com.exercise.obcex789.entities.Laptop;
import com.exercise.obcex789.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    // buscar un laptop por id (PathVariable vincula parametro con vb de path)
    @GetMapping("api/laptops/{id}")
    @ApiOperation("buscar un laptop por id Long")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Primary key Long type") @PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if(laptopOpt.isPresent())
           return  ResponseEntity.ok(laptopOpt.get());
        else return ResponseEntity.notFound().build();
    }


    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        if(laptop.getId()!=null){
            log.warn("trying to create a laptop with id");
            System.out.println("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);

    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        if(laptop.getId() == null){
            log.warn("Trying to update a not existing book");
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("Trying to update a not existing book");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    //ApiIgnore: No se muestra en doc
    @ApiIgnore
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a not existing laptop");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @ApiIgnore
    @DeleteMapping("api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        //log.debug;
        log.info("REST Request for delete all books");
        if(!(laptopRepository.count() >0))
            return ResponseEntity.noContent().build();
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}


