package org.uacm.mapeo.ejemplosb0.persistencia.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Pelicula;

import java.util.List;

public interface PeliculasRepo extends JpaRepository<Pelicula,Integer> {

      List<Pelicula> findByDuracionBetween(float duracionMin, float duracionMax);
      List<Pelicula> searchByTituloEndingWith(String terminacion);
      //Page<Pelicula> findAll(Pageable pageable);
}
