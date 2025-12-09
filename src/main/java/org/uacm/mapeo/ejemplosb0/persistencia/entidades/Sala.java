package org.uacm.mapeo.ejemplosb0.persistencia.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="salas")
@Data
public class Sala {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="El nombre es obligatorio")
    @Size(min=2,max=10,message="Debe tener entre 2 y 4 caracteres")
    private String nombre;

    @Min(value=20)
    private int capacidad;
}
