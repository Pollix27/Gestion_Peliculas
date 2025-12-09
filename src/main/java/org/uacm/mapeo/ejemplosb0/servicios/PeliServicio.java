package org.uacm.mapeo.ejemplosb0.servicios;

import org.springframework.data.domain.Page;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Pelicula;

import java.util.List;

public interface PeliServicio {

    List<Pelicula> consultarPeliculas();
    List<Pelicula> consultaPeliDurancionEntre(float d1,float d2);
    Page<Pelicula> consultarPeliculasP(int p,int s);
    Pelicula agregarPelicual(Pelicula p);
    Pelicula buscarPorId(int id);
    void eliminarPelicula(int id);
}
