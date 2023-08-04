package com.example.demo.controllers;

import com.example.demo.models.Products;
import com.example.demo.models.ResponseObject;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {

//    DI
    @Autowired
    private ProductRepository repository;
    @GetMapping("")
    List<Products> getALlProduct(){
        return repository.findAll();
    }
//Get Method - Using to retreive data from Database
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Products> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
        ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("OK", "Added Successfully", foundProduct)
        ):
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Failed", "Can't find product with ID = " + id, "null"));
    }
//Post Method - Using to Insert - Send Data to Database
    @PostMapping("/{insert}")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Products newProduct){
//        Check if 2 product name have the same name
        List<Products> foundProducts = repository.findByProductName(newProduct.getProductName().trim());

        if(!foundProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "Product Name already taken", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Product Inserted", repository.save(newProduct))
        );
    }
//Put Method - Using to Update - Insert a new Product to Database kkk
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @RequestBody Products newProduct){
        Products updatedProduct = repository.findById(id)
                .map(Products -> {
                    Products.setProductName(newProduct.getProductName());
                    Products.setYears(newProduct.getYear());
                    Products.setPrice(newProduct.getPrice());
                    Products.setUrl(newProduct.getUrl());
                    return repository.save(Products);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Product Updated", updatedProduct)
        );
    }
}
