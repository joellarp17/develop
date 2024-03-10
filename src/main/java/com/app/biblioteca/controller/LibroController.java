package com.app.biblioteca.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.biblioteca.entity.Genero;
import com.app.biblioteca.entity.Libro;
import com.app.biblioteca.repository.GeneroRepository;
import com.app.biblioteca.repository.LibroRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class LibroController {
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private GeneroRepository generoRepository; 
	
	@GetMapping("/libros")
	public String listarLibros (Model modelo) {
		List<Libro> listaDeLibros = libroRepository.findAll();
	modelo.addAttribute("listaLibros",listaDeLibros);
		return "libros";
	}
	
	
	@GetMapping("/libro/nuevo")
	public String formularioLibros (Model modelo) {
		modelo.addAttribute("libro", new Libro());
		
		List<Genero> listaDeGeneros = generoRepository.findAll();
		modelo.addAttribute("listaGeneros",listaDeGeneros);
		
		return "libro_formulario";
	}
	
	
	@PostMapping ("/libro/guardar")
	public String guardarLibro (Libro libro) {
		libroRepository.save(libro);
		return "redirect:/libros";
	}
	
	@GetMapping("/libros/editar/{id}")
	public String formularioEditarLibro(@PathVariable("id") Long id, Model modelo) {

		Libro libro = libroRepository.findById(id).get();
		modelo.addAttribute("libro", libro);
		
		List<Genero> listaDeGeneros = generoRepository.findAll();
		modelo.addAttribute("listaGeneros", listaDeGeneros);
		
		
		return "libro_formulario";
	}
	
	@GetMapping("/libros/eliminar/{id}")
	public String eliminarLibro(@PathVariable("id") Long id, Model modelo) {
		
		libroRepository.deleteById(id);
		return "redirect:/libros";
	}
	
	@GetMapping("/libros-recientes")
    public String obtenerLibrosRecientes(Model model) {
        // Filtrar libros de los últimos 6 meses directamente desde el repositorio
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        List<Libro> librosRecientes = libroRepository.findByFechaPublicacionAfter(sixMonthsAgo);

        model.addAttribute("libros", librosRecientes);
        return "librosRecientes";
    }
	
	
	@GetMapping("/report/libros")
	public void report(HttpServletResponse response) throws JRException, IOException {
		
		try {

		// 1. Acceder al reporte 
		InputStream jasperStream = getClass().getResourceAsStream("/reports/libros.jasper");

		
		// 2. Preparar los datos 
		Map<String, Object> params = new HashMap<>();
		params.put("usuario", "Joel  Larico");
		
		List<Libro>listaDeLibros = libroRepository.findAll();
		
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDeLibros);
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		
		// 3. Configuracion 
		
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "filename=reporte_libros.pdf");
		
		// 4. Exportar reporte
		final OutputStream outputStream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	
}



