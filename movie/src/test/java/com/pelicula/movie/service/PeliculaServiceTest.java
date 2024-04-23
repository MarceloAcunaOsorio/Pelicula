package com.pelicula.movie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pelicula.movie.model.Pelicula;
import com.pelicula.movie.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest 
{
    @InjectMocks
    private PeliculaServiceImpl peliculaServicio;



    @Mock
    private PeliculaRepository peliculaRepositorioMock;



    @Test
    public void guardarPeliculaTest()
    {
       //Arrange
       Pelicula pelicula = new Pelicula();
       pelicula.setTitulo("Jose Randon");

       when(peliculaRepositorioMock.save(any())).thenReturn(pelicula);



       //Act
       Pelicula resultado = peliculaServicio.createPelicula(pelicula);


       //Assert
       assertEquals("Jose Randon",resultado.getTitulo());
    }


}
