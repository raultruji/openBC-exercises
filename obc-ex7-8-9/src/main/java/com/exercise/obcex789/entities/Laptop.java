package com.exercise.obcex789.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
@ApiModel("#Laptop inventado de ejemplo para ver como se ve en postman y swagger")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Primary key autoincrement Long")
    private Long id;
    private String processor;
    private Integer ram;
    private Double price;
    @ApiModelProperty("Contiene SSD Boolean")
    private Boolean ssd;

    // constructores

    public Laptop() {
    }

    public Laptop(Long id, String processor, Integer ram, Double price, Boolean ssd) {
        this.id = id;
        this.processor = processor;
        this.ram = ram;
        this.price = price;
        this.ssd = ssd;
    }
// getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getSsd() {
        return ssd;
    }

    public void setSsd(Boolean ssd) {
        this.ssd = ssd;
    }
}
