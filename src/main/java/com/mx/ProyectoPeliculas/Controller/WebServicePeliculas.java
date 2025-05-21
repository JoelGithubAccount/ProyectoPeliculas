package com.mx.ProyectoPeliculas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ProyectoPeliculas.model.Peliculas;
import com.mx.ProyectoPeliculas.service.PeliculasImplementacionServicios;



@RestController
@RequestMapping(path = "WebServicePeliculas")
@CrossOrigin
public class WebServicePeliculas {

	//Inyeccion de dependencia
		@Autowired
		PeliculasImplementacionServicios serviciosPeliculas;
		
		@GetMapping(path = "Mostrar")
		public List<Peliculas> mostrar() {
			return  serviciosPeliculas.listar();
		}
		
		//@RequestBody --- te convierte el json a objeto
		@PostMapping(path = "guardar")
		public ResponseEntity<?> guardar(@RequestBody Peliculas pelicula){
			
			try {
				
				String response = serviciosPeliculas.guardar(pelicula);
				
				if (response.equals("ClavePeliculaEnExistencia"))
					return new ResponseEntity<>("Esa Clave De pelicula Ya Existe", HttpStatus.OK);
			else if(response.equals("NombreYaExistente"))
				return new ResponseEntity<>("Ese nombre de pelicula ya existe", HttpStatus.OK);
			else 
				return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<>("Error al guardar", HttpStatus.OK);
			}
			
		}
		
		
		@PostMapping(path = "BuscarXid")
		public Peliculas buscarXid(@RequestBody Peliculas pelicula) {
			
			return serviciosPeliculas.buscarXid(pelicula.getIdPelicula());
			
		}
		
		@PutMapping(path = "editar")
		public ResponseEntity<?> editar(@RequestBody Peliculas pelicula){
			
			boolean response = serviciosPeliculas.editar(pelicula);
			
			if (response == true)
				return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
			else 
				return new ResponseEntity<>("No se edito ese registro no existe", HttpStatus.OK);
		}
		
		@DeleteMapping(path="Eliminar")
		public ResponseEntity<String> eliminar(@RequestBody Peliculas pelicula){
			
			boolean response = serviciosPeliculas.eliminar(pelicula.getIdPelicula());
			
			if (response == true)
				return new ResponseEntity<>("Eliminado Correctamente", HttpStatus.OK);
			else
				return new ResponseEntity<>("No se elimno, ese registro no existe", HttpStatus.OK);
		}
	
}
