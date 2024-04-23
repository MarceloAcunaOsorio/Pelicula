package com.pelicula.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pelicula.movie.model.Pelicula;
import com.pelicula.movie.repository.PeliculaRepository;


import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService
{
    @Autowired
    private PeliculaRepository peliculaRepository;


    @Override
    public List<Pelicula> getAllPeliculas()
    {
        return peliculaRepository.findAll();
    }

    
    @Override
    public Optional<Pelicula>getPeliculaById(int id)
    {
        return peliculaRepository.findById(id);
    }


    //crear
    @Override
    public Pelicula createPelicula(Pelicula pelicula)
    {
      return peliculaRepository.save(pelicula);
    }


    //Actualizar
    public Pelicula updatePelicula(int id,Pelicula pelicula)
    {
        if (peliculaRepository.existsById(id)) 
        {
           pelicula.setId(id);
           return peliculaRepository.save(pelicula);    
        }
        else
        {
            return null;
        }
    }

    @Override
    public void deletePelicula(int id)
    {
        peliculaRepository.deleteById(id);
    }

}
