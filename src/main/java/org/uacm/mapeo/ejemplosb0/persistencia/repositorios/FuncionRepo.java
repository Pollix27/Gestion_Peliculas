package org.uacm.mapeo.ejemplosb0.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Funcion;

public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

}
