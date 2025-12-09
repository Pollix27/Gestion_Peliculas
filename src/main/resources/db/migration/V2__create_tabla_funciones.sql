CREATE TABLE funciones
(
    id            INT AUTO_INCREMENT NOT NULL,
    p_id_pelicula INT NULL,
    s_id          INT NULL,
    horario       datetime NULL,
    CONSTRAINT pk_funciones PRIMARY KEY (id)
);

ALTER TABLE funciones
    ADD CONSTRAINT FK_FUNCIONES_ON_P_ID_PELICULA FOREIGN KEY (p_id_pelicula) REFERENCES peliculas (id_pelicula);

ALTER TABLE funciones
    ADD CONSTRAINT FK_FUNCIONES_ON_S FOREIGN KEY (s_id) REFERENCES salas (id);