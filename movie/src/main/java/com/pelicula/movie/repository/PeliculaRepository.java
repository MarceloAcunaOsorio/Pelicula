package com.pelicula.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pelicula.movie.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula,Integer> 
{
  
}
