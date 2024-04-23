package com.pelicula.movie.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;


import com.pelicula.movie.model.Pelicula;
import com.pelicula.movie.service.PeliculaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/peliculas")
public class PeliculaController 
{
    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private PeliculaService peliculaService;




      //mostrar listado de peliculas
    //@GetMapping
    //public List<Pelicula>getAllPeliculas()
    //{
    //    log.info("GET /peliculas");
    //    log.info("Retornando todas las Peliculas");
    //    return peliculaService.getAllPeliculas();
    //}



     //mostrar listado de peliculas
     @GetMapping
     public CollectionModel<EntityModel<Pelicula>> getAllPeliculas()
     {
        log.info("GET /peliculas");
        log.info("Retornando todas las Peliculas");
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();

        List<EntityModel<Pelicula>> peliculaResources = peliculas.stream()
            .map(pelicula -> EntityModel.of(pelicula,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getId())).withSelfRel()
                    ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
        CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculaResources, linkTo.withRel("peliculas")); 

        return resources;
     }


   


    /*//buscar pelicula segun el ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPeliculaById(@PathVariable int id)
    { 
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        if(pelicula.isEmpty())
        {
            log.error("Nose encontro la pelicula con el Id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro la pelicula con Id " + id));
        }
        return ResponseEntity.ok(pelicula);   
        
    }*/

    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable int id)
    {
       Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);

       //verifica si la pelicula existe
       if(pelicula.isPresent())
       {
         return EntityModel.of(pelicula.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("All-peliculas"));
       }
       else
       {
         throw new PeliculaNotFoundException("Pelicula no encontrada con el  id: " + id);
       }
    }







    /*//crear
    @PostMapping
    public ResponseEntity<Object> createPelicula(@Valid @RequestBody Pelicula pelicula)
    {
       Pelicula createPelicula = peliculaService.createPelicula(pelicula);
       if (createPelicula == null) 
       {
         log.error("Error al crear la pelicula {}",pelicula);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al crear pelicula"));
       }
       return ResponseEntity.ok(createPelicula);
    }
    */

    //Crear
    public EntityModel<Pelicula> crearPelicula(@RequestBody Pelicula pelicula)
    {
        Pelicula createdPelicula = peliculaService.createPelicula(pelicula);
        return EntityModel.of(createdPelicula,
               WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(createdPelicula.getId())).withSelfRel(),
               WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    }






    /*//actualizar
    @PutMapping("/{id}")
    public Pelicula updatepePelicula(@PathVariable int id, @RequestBody Pelicula pelicula) 
    {    
        return peliculaService.updatePelicula(id, pelicula);
    }
    */
     

    //Actualizar
    @PutMapping("/{id}")
    public EntityModel<Pelicula> updatePelicula(@PathVariable int id, @RequestBody Pelicula pelicula)
    {
        Pelicula updatePelicula = peliculaService.updatePelicula(id, pelicula);
        return EntityModel.of(updatePelicula,
               WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
               WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all - peliculas"));
    }


    
    //eliminar o borrar
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable int id)
    {
      peliculaService.deletePelicula(id);
    }




    //controlar error
    static class ErrorResponse
    {
        //se declara una variable estatica de tipo string
        private final String message;

        //setter
        public ErrorResponse(String message)
        {
            this.message = message;
        }

        //getter
        public String getMessage()
        {
            return message;
        }
    }

}
