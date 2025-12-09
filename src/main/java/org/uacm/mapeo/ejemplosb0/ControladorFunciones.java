package org.uacm.mapeo.ejemplosb0;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Funcion;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Pelicula;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Sala;
import org.uacm.mapeo.ejemplosb0.servicios.FuncionServicio;
import org.uacm.mapeo.ejemplosb0.servicios.PeliServicio;
import org.uacm.mapeo.ejemplosb0.servicios.SalaServicio;

import java.util.List;

@Controller
public class ControladorFunciones {
    @Autowired
    SalaServicio servicioS;

    @Autowired
    PeliServicio servicioP;
    @Autowired
    FuncionServicio servicioF;


    @PostMapping("/formularioF")
    public String checkFormulario(@Valid Funcion funcion, BindingResult bres, Model model){


        if(bres.hasErrors()){
            List<Sala> salas=servicioS.consultaSalas();
            model.addAttribute("salas",salas);
            List<Pelicula> peliculas=servicioP.consultarPeliculas();
            model.addAttribute("peliculas",peliculas);
            return "formulario_funciones";
        }


        servicioF.programarFuncion(funcion);
        return "redirect:/";
    }

    @GetMapping("/formularioF")
    public String mostrarForumulario(Funcion funcion, Model model){
        List<Sala> salas=servicioS.consultaSalas();
        model.addAttribute("salas",salas);
        List<Pelicula> peliculas=servicioP.consultarPeliculas();
        model.addAttribute("peliculas",peliculas);

        return "formulario_funciones";
    }

    @GetMapping("/verFunciones")
    public String verFunciones(Model model){
        List<Funcion> funciones = servicioF.consultarFunciones();
        model.addAttribute("funciones", funciones);
        return "lista_funciones";
    }

    @GetMapping("/editarFuncion/{id}")
    public String editarFuncion(@PathVariable int id, Model model){
        Funcion funcion = servicioF.buscarPorId(id);
        List<Sala> salas = servicioS.consultaSalas();
        List<Pelicula> peliculas = servicioP.consultarPeliculas();
        model.addAttribute("funcion", funcion);
        model.addAttribute("salas", salas);
        model.addAttribute("peliculas", peliculas);
        return "formulario_funciones";
    }

    @PostMapping("/editarFuncion/{id}")
    public String actualizarFuncion(@PathVariable int id, @Valid Funcion funcion, BindingResult bres, Model model){
        if(bres.hasErrors()){
            List<Sala> salas = servicioS.consultaSalas();
            List<Pelicula> peliculas = servicioP.consultarPeliculas();
            model.addAttribute("salas", salas);
            model.addAttribute("peliculas", peliculas);
            return "formulario_funciones";
        }
        funcion.setId(id);
        servicioF.programarFuncion(funcion);
        return "redirect:/verFunciones";
    }

    @GetMapping("/eliminarFuncion/{id}")
    public String eliminarFuncion(@PathVariable int id){
        servicioF.eliminarFuncion(id);
        return "redirect:/verFunciones";
    }
}
