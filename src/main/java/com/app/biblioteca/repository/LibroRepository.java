package com.app.biblioteca.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.biblioteca.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

	List<Libro> findByFechaPublicacionAfter(LocalDate sixMonthsAgo);
}
