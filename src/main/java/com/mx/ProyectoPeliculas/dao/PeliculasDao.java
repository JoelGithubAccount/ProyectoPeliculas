package com.mx.ProyectoPeliculas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ProyectoPeliculas.model.Peliculas;



public interface PeliculasDao extends JpaRepository<Peliculas, Long>{

}
