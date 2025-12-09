package org.uacm.mapeo.ejemplosb0.servicios;

import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Sala;

import java.util.List;

public interface SalaServicio {

List<Sala> consultaSalas();
Sala agregaSala(Sala s);
}
