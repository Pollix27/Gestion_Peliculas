package org.uacm.mapeo.ejemplosb0.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Sala;
import org.uacm.mapeo.ejemplosb0.persistencia.repositorios.SalasRepo;

import java.util.List;

@Service
public class SalaServicioImp implements SalaServicio{

    @Autowired
    SalasRepo repo;

    @Override
    public List<Sala> consultaSalas() {
        return repo.findAll();
    }

    @Override
    public Sala agregaSala(Sala s) {
        return repo.save(s);
    }

}

