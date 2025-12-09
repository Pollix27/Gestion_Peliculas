package org.uacm.mapeo.ejemplosb0.persistencia.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="peliculas")
@Setter
@Getter
@ToString
@NamedQuery(name = "selectPeliculaTitulo", query = "SELECT p FROM Pelicula p WHERE p.titulo=:titulo")
    public class Pelicula
    {
        @ToString.Exclude
        @Id
        @Column(name="id_pelicula")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @NotBlank(message="El titulo es obligatorio")
        private String titulo;

        @Column(name="genero")
        @Size(min=4,max=10,message="Debe tener entre 4 y 10 caracteres")
        private String tipo;

        @DecimalMin(value="1.00",message="La duración mínima es de 1.00")
        private float duracion;


}
