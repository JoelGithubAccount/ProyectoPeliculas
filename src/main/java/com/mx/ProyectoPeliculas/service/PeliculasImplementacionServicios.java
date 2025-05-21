package com.mx.ProyectoPeliculas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ProyectoPeliculas.dao.PeliculasDao;
import com.mx.ProyectoPeliculas.model.Peliculas;

@Service //Le indicamos que va a ser un servicio
public class PeliculasImplementacionServicios {

	//Inyectamos las dependencias extraidas de PeliculasDao
	@Autowired 
	PeliculasDao peliculasDao;
	
	//Esto quiere decir que no va a realizar cambios en la base de datos
		@Transactional(readOnly = true)
		public List<Peliculas> listar(){
			
			List<Peliculas> registrosBd = peliculasDao.findAll();
			
			return registrosBd;
		
			
		}
		
		@Transactional
		public String guardar(Peliculas pelicula) {
			
			//Desarrollo de la logica
			
			//1.- Obtener los registros de la tabla
			//2.- Ciclo
			//3.- Condicional
			//4.- Banderas
			
			String respuesta = "guardado";
			
			boolean bandera = false;
			
			for(Peliculas p: peliculasDao.findAll()) {
				
				if(p.getClave().equals(pelicula.getClave())) {
					
					respuesta = "ClavePeliculaEnExistencia";
					bandera = true;
					break;
					
				} else if (p.getNombre().equals(pelicula.getNombre())){
					
					respuesta = "NombreYaExistente";
					bandera = true;
							break;
					
				}
				
			}
			
			if (!bandera)
				peliculasDao.save(pelicula);
			return respuesta;
			
		}
		
		@Transactional(readOnly = true)
		public Peliculas buscarXid(long id) {
			
			Peliculas peliculaEncon = peliculasDao.findById(id).orElse(null);
			return peliculaEncon;
		}
		
		//Validar: que el id exista
		public boolean editar(Peliculas pelicula) {
			
			Peliculas peliculaEncon = peliculasDao.findById(pelicula.getIdPelicula()).orElse(null);
			
			if (peliculaEncon != null) {
				
				peliculasDao.save(pelicula);
				return true;
				
			} else
			
			return false;
		}
		
		//Validar que el id exista, mandar mensaje, no se puede eliminar
		@Transactional
		public boolean eliminar(Long id) {
			
			Peliculas peliculaEncon = peliculasDao.findById(id).orElse(null);
			if(peliculaEncon!= null) {
				
				peliculasDao.deleteById(id);
				return true;
				
			}else
				return false;
			
		}
		
}