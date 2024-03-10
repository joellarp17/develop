package com.app.biblioteca.entity;


import java.util.Date;

import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "libros")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLibro;
	
	@Size(min = 4, max = 60)
	@Column(name = "titulo_libro", nullable = false)
	private String titulo;
	
	@Size(min = 4, max = 60)
	@Column(name = "nombre_autor", nullable = false)
	private String autor;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_publicacion", nullable = false)
	private Date fechaPublicacion;
	
	@ManyToOne
	@JoinColumn(name = "genero_id", nullable = false)
	private Genero genero;
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}

	public Libro(@Size(min = 4, max = 60) String titulo, @Size(min = 4, max = 60) String autor, Date fechaPublicacion,
			Genero genero) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.genero = genero;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long id) {
		this.idLibro = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	

}
