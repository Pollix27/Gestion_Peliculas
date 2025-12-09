# Diagrama de Clases - Gestión de Películas

```mermaid
classDiagram
    class Pelicula {
        -int id
        -String titulo
        -String tipo
        -float duracion
    }

    class Sala {
        -int id
        -String nombre
        -int capacidad
    }

    class Funcion {
        -int id
        -LocalTime horario
        -LocalDate fecha
    }

    class User {
        -Long id
        -String username
        -String email
        -String password
        -Boolean enabled
        -Boolean acceptedTerms
        -LocalDateTime acceptedTermsDate
        -LocalDateTime createdAt
    }

    class PasswordResetToken {
        -Long id
        -String token
        -LocalDateTime expiryDate
        -Boolean used
    }

    class Reserva {
        -Long id
        -int cantidadBoletos
        -LocalDateTime fechaReserva
    }

    Funcion "*" --> "1" Pelicula : p
    Funcion "*" --> "1" Sala : s
    Reserva "*" --> "1" User : user
    Reserva "*" --> "1" Funcion : funcion
    PasswordResetToken "*" --> "1" User : user
```

## Relaciones

- **Funcion - Pelicula**: Una función pertenece a una película (ManyToOne)
- **Funcion - Sala**: Una función se realiza en una sala (ManyToOne)
- **Reserva - User**: Una reserva pertenece a un usuario (ManyToOne)
- **Reserva - Funcion**: Una reserva es para una función específica (ManyToOne)
- **PasswordResetToken - User**: Un token de reseteo pertenece a un usuario (ManyToOne)
