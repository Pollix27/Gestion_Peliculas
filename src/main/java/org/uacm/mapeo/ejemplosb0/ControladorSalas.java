package org.uacm.mapeo.ejemplosb0;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Pelicula;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Sala;
import org.uacm.mapeo.ejemplosb0.servicios.SalaServicio;

@Controller
public class ControladorSalas {
    @Autowired
    SalaServicio servicio;

    @PostMapping("/formularioS")
    public String checkFormulario(@Valid Sala sala, BindingResult bres){

        if(bres.hasErrors()){
            System.out.println(sala);
            return "formulario_salas";
        }
        servicio.agregaSala(sala);
        return "redirect:/";
    }

    @GetMapping("/formularioS")
    public String mostrarForumulario(Sala sala){

        return "formulario_salas";
    }
}
