package com.pelicula.movie.service;

import com.pelicula.movie.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService 
{
   /*obtiene un listado de la clase peliculas*/ 
   List<Pelicula>getAllPeliculas();

   /*obtiene el id de los datos que  estan en la clase peliculas*/
   Optional<Pelicula>getPeliculaById(int id);

   /*Crear*/
   Pelicula createPelicula(Pelicula pelicula);

   /*actualizar*/
   Pelicula updatePelicula(int id,Pelicula pelicula);

   /*Eliminar*/
   void deletePelicula(int id);
}
