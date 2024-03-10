package com.app.biblioteca.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "generos")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGenero;
	
	@Column(name = "nombre_genero", nullable = false)
	private String nombreGenero;
	
	@OneToMany (mappedBy = "genero", cascade = CascadeType.ALL)
	private List<Libro> libros;
	
	public Genero() {
		// TODO Auto-generated constructor stub
	}

	public Genero(String nombreGenero, List<Libro> libros) {
		super();
		this.nombreGenero = nombreGenero;
		this.libros = libros;
	}

	public Long getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	

}
