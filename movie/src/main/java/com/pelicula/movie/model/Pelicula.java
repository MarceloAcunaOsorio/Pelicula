package com.pelicula.movie.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelProcessorHandlerMethodReturnValueHandler;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;



@Entity
@Table(name = "Pelicula")
public class Pelicula extends RepresentationModel<Pelicula>
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @NotBlank(message = "No puede ingresar un Titulo Vacio")
  @Column(name = "Titulo")
  private String titulo;

  @NotNull
  @Min(1)
  @Column(name = "año")
  private int año;

  @NotBlank(message = "No puede ingresar un Director Vacio")
  @Column(name = "Director")
  private String director;

  @NotBlank(message = "No puede ingresar un Genero Vacio")
  @Column(name = "Genero")
  private String genero;

  @NotBlank(message = "No puede ingresar un Sinopsis Vacio")
  @Column(name = "Sinopsis")
  private String sinopsis;


  //GETTER
  public int getId()
  {
    return id;
  }

  public String getTitulo()
  {
    return titulo;
  }

  public int getAño()
  {
    return año;
  }

  public String getDirector()
  {
    return director;
  }

  public String getGenero()
  {
    return genero;
  }

  public String getSinopsis()
  {
    return sinopsis;
  }

  //Setter

  public void setId(int id)
  {
    this.id = id;
  }

  public void setTitulo(String titulo)
  {
    this.titulo = titulo;
  }

  public void setAño(int año)
  {
    this.año = año;
  }

  public void setDirector(String director)
  {
    this.director = director;
  }

  public void setGenero(String genero)
  {
    this.genero = genero;
  }

  public void setSinopsis(String sinopsis)
  {
    this.sinopsis = sinopsis;
  }

}
