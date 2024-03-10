package com.app.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.biblioteca.entity.Genero;
import com.app.biblioteca.repository.GeneroRepository;



@Controller
public class GeneroController {
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@GetMapping("/generos")
	public String listarGeneros (Model modelo) {
		
		List<Genero> listaDeGeneros = generoRepository.findAll();
		modelo.addAttribute("listaGeneros", listaDeGeneros);
		return "generos";
	}
	
	@GetMapping("/genero/nuevo")
	public String formularioGenero (Model modelo) {
		modelo.addAttribute("genero", new Genero());
		return "genero_formulario";
	}
	
	@PostMapping("/genero/guardar")
	public String guardarGenero (Genero genero) {
		generoRepository.save(genero);
		return "redirect:/generos";
	}
	
	@GetMapping("/genero/editar/{id}")
	public String formularioEditarGenero (@PathVariable("id") Long id, Model modelo) {
		
		Genero genero = generoRepository.findById(id).get();
		modelo.addAttribute("genero", genero);
		
		return "genero_formulario";
	}
	
	@GetMapping("/genero/eliminar/{id}")
	public String eliminarGenero(@PathVariable("id") Long id, Model modelo) {
		
		generoRepository.deleteById(id);
		return "redirect:/generos";
	}
	

}
