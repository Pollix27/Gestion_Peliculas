package org.uacm.mapeo.ejemplosb0.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Funcion;
import org.uacm.mapeo.ejemplosb0.persistencia.repositorios.FuncionRepo;

import java.util.List;
@Service
public class FuncionServicioImp implements FuncionServicio{
   @Autowired
    FuncionRepo repo;

    @Override
    public Funcion programarFuncion(Funcion f) {
        return repo.save(f);
    }

    @Override
    public List<Funcion> consultarFunciones() {
        return repo.findAll();
    }

    @Override
    public Funcion buscarPorId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminarFuncion(int id) {
        repo.deleteById(id);
    }
}
