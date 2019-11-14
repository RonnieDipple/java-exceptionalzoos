package com.rdipple.javazoosassignment.controllers;


import com.rdipple.javazoosassignment.models.Zoo;
import com.rdipple.javazoosassignment.services.ZooService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/zoos")
public class ZooController {

   
    ZooService zooService;

    ///GET /zoos returns all zoos with their phone numbers and animals
    @GetMapping(value = "/zoos", produces = {"application/json"})
    public ResponseEntity<?> listAllZoos() {
        List<Zoo> myZoos = zooService.findAll();
        return new ResponseEntity<>(myZoos, HttpStatus.OK);
    }


    //GET /zoo/{id} returns all information related to a zoo based on its id
    @GetMapping(value = "/zoo/{zooid}", produces = {"application/json"})
    public ResponseEntity<?> getZooById(@PathVariable long zooid) {
        Zoo myZoo = zooService.findZooById(zooid);
        return new ResponseEntity<>(myZoo, HttpStatus.OK);
    }

    //GET /zoo/namelike/{name} returns a list of all the zoos with their information who have the given substring in their name
    @GetMapping(value = "/zoo/namelike/{name}", produces = {"application/json"})
    public ResponseEntity<?> getZooByNamelike(@PathVariable String name) {
        List<Zoo> myZoos = zooService.findByNameContaining(name);
        return new ResponseEntity<>(myZoos, HttpStatus.OK);
    }

    // POST /zoo - add a zoo
    public ResponseEntity<?> addNewZoo(@Valid @RequestBody Zoo newZoo) {
        newZoo = zooService.save(newZoo);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newZooURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{zooid}")
                .buildAndExpand(newZoo.getZooid())
                .toUri();
        responseHeaders.setLocation(newZooURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

   /* //GET http://localhost:2019/animals/count -  that returns a JSON object list listing the animals and a count of how many zoos where they can be found.
    @GetMapping(value = "/animals/count", produces = {"application/json"})
    public ResponseEntity<?> getZooAndAnimalNum(){
        return new ResponseEntity<>(zooService.getCountZooAnimals(), HttpStatus.OK);
    }*/

    //PUT localhost:2019/zoos/zoo/{id}
    @PutMapping(value = "/zoo/{zooid}", consumes = {"application/json"})
    public ResponseEntity<?> updateZoo(@RequestBody Zoo updateZoo, @PathVariable long zooid) {
        zooService.update(updateZoo, zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE localhost:2019/zoos/zoo/{id}
    @DeleteMapping(value = "/zoo/{zooid}")
    public ResponseEntity<?> deleteZooById(@PathVariable long zooid) {
        zooService.delete(zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE localhost:2019/zoos/zoo/{zooid}/animals/{animalid}
    @DeleteMapping(value = "/zoo/{zooid}/animals/{animalid}")
    public ResponseEntity<?> deleteZooAnimalByIds(@PathVariable long zooid, @PathVariable long animalid) {
        zooService.deleteZooAnimal(zooid, animalid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //POST localhost:2019/zoos/zoo/{zooid}/animals/{animalid}
    @PostMapping(value = "/zoo/{zooid}/animals/{animalid}")
    public ResponseEntity postZooAnimalByIds(@PathVariable long zooid, @PathVariable long animalid) {
        zooService.addZooAnimal(zooid, animalid);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ZooController(ZooService zooService) {
        this.zooService = zooService;
    }
}

/*# Project Exceptional Zoos

A student that completes this project shows that they can:

* implement industry standard exception handling including user friendly exception messages
* troubleshoot running services using both system and programmatically generated logs

## Introduction

## Instructions

Starting with your java-zoo application (instructions for that application can be found at https://github.com/LambdaSchool/java-zoos.git)

Add appropriate exception handling routines. Required exceptions to handle are when
  * a resource is not found
  * the wrong data type is used for a path variable
  * a non-handled endpoint is accessed (a URL not found exception)
  * (other standard exceptions can also be handled)
  * include a generic exception handler to trap all exceptions not previously handled

Add appropriate logging routines. Required logging include
  * Activating actuator endpoints
  * Tomcat logging routed to a separate log file
  * Custom logging under each Get endpoint saying the endpoint has been accessed
    * should only go to console
    * for example when a client calls PUT /students/Student log should say "PUT /students/Student accessed"
    * include in log any appropriate parameters sent to the end point
  * Note: put the log files under the directory /tmp/var/logs/lambdajx You may have to create some directories for this to work.
  * Log when each class and method is accessed in the zoo application.

## Stretch Goals
  * for each log, add a date and time stamp.
  * Make the endpoint /zoos/zoos pageable and sortable*/
