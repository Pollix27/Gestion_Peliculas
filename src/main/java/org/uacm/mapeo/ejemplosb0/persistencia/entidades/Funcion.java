package org.uacm.mapeo.ejemplosb0.persistencia.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name="funciones")
public class Funcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private int id;

    @ManyToOne
    @JoinColumn(name = "p_id_pelicula")
    private Pelicula p;

    @ManyToOne
    @JoinColumn(name = "s_id")
    private Sala s;

    @NotNull(message = "Se debe indicar la hora")
    @Temporal(TemporalType.TIME)
    private LocalTime horario;

    @NotNull(message = "La fecha es obligatoria")
    @Future(message = "La fecha debe ser hacia adelante")
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

}
