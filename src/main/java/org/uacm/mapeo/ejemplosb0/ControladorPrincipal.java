package org.uacm.mapeo.ejemplosb0;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.Pelicula;
import org.uacm.mapeo.ejemplosb0.servicios.PeliServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorPrincipal {

    @Autowired
    PeliServicio servicio;

   @RequestMapping( "/" )
    public String index (Model model) {
        //List<Pelicula> datos=servicio.consultaPeliDurancionEntre(1.00f,1.30f);
        //model.addAttribute("listaPeli", datos);
        return "index1";
    }

    @RequestMapping( "/ejemploPag" )
    public String listarPeliculasPag (Model model, @RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="50")int size) {
        Page<Pelicula> pagPeli;

        pagPeli=servicio.consultarPeliculasP(page,size);
        List<Pelicula> datos=pagPeli.getContent();



        model.addAttribute("resultado", pagPeli);
        return "index";
    }

    @PostMapping("/formularioA")
    public String checkFormulario(@Valid Pelicula pelicula, BindingResult bres){

        if(bres.hasErrors()){
            System.out.println(pelicula);
            return "formulario";
        }
        servicio.agregarPelicual(pelicula);
        return "redirect:/ejemploPag";
    }

    @GetMapping("/formularioA")
    public String mostrarForumulario(Pelicula pelicula, Model model){
       //model.addAttribute("valor","algo");
        return "formulario";
    }

    @GetMapping("/editarPelicula/{id}")
    public String editarPelicula(@PathVariable int id, Model model){
        Pelicula pelicula = servicio.buscarPorId(id);
        model.addAttribute("pelicula", pelicula);
        return "formulario";
    }

    @PostMapping("/editarPelicula/{id}")
    public String actualizarPelicula(@PathVariable int id, @Valid Pelicula pelicula, BindingResult bres){
        if(bres.hasErrors()){
            return "formulario";
        }
        pelicula.setId(id);
        servicio.agregarPelicual(pelicula);
        return "redirect:/ejemploPag";
    }

    @GetMapping("/eliminarPelicula/{id}")
    public String eliminarPelicula(@PathVariable int id){
        servicio.eliminarPelicula(id);
        return "redirect:/ejemploPag";
    }
}












