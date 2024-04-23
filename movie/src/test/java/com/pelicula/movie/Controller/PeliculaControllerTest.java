package com.pelicula.movie.Controller;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

import com.pelicula.movie.controller.PeliculaController;
import com.pelicula.movie.model.Pelicula;
import com.pelicula.movie.service.PeliculaServiceImpl;



import java.util.Arrays;
import java.util.List;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest 
{
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PeliculaServiceImpl peliculaServicioMock;



    @Test
    public void obtenerTodosTest() throws Exception
    {
        //Arrange
        //creacion de pelicula

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("John");
        pelicula1.setId(1);

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Doe");
        pelicula2.setId(2);

        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);
        when(peliculaServicioMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
               .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].titulo", Matchers.is("John")))
               .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].titulo", Matchers.is("Doe")));
    }

}
