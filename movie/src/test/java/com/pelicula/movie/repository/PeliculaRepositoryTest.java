package com.pelicula.movie.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pelicula.movie.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest 
{
    @Autowired
    private PeliculaRepository peliculaRepository;


    @Test
    public void guardarPeliculaTest()
    {
        //Arrange
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("John Doe");


        //Act
        Pelicula resultado = peliculaRepository.save(pelicula);


        //Assert
        assertNotNull(resultado.getId());
        assertEquals("John Doe",resultado.getTitulo());
    }

}
