package org.uacm.mapeo.ejemplosb0.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Pelicula;
import org.uacm.mapeo.ejemplosb0.persistencia.repositorios.PeliculasRepo;


import java.util.List;

@Service
public class PeliculasServicio implements PeliServicio{
    @Autowired
    PeliculasRepo repo;

    @Override
    public List<Pelicula> consultarPeliculas() {

        return repo.findAll();
    }

    @Override
    public List<Pelicula> consultaPeliDurancionEntre(float d1, float d2) {
        return repo.findByDuracionBetween(d1,d2);
    }

    @Override
    public Page<Pelicula> consultarPeliculasP(int p, int s) {
        Pageable pag=PageRequest.of(p,s);
        return repo.findAll(pag);
    }

    @Override
    public Pelicula agregarPelicual(Pelicula p) {
        return repo.save(p);
    }

    @Override
    public Pelicula buscarPorId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminarPelicula(int id) {
        repo.deleteById(id);
    }
}
