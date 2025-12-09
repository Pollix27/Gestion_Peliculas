CREATE TABLE peliculas
(
    id_pelicula INT AUTO_INCREMENT NOT NULL,
    titulo      VARCHAR(255) NULL,
    genero      VARCHAR(10) NULL,
    duracion    FLOAT NOT NULL,
    CONSTRAINT pk_peliculas PRIMARY KEY (id_pelicula)
);

CREATE TABLE salas
(
    id       INT AUTO_INCREMENT NOT NULL,
    nombre   VARCHAR(255) NULL,
    capacidad INT NOT NULL,
    CONSTRAINT pk_salas PRIMARY KEY (id)
);