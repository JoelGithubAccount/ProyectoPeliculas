package com.mx.ProyectoPeliculas.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PELICULAS")
@NoArgsConstructor // Constructor Vacio
@AllArgsConstructor // Constructor con todos los parametros
@Data // Ya tienen el metodo ToString, y encapsulamiento
public class Peliculas {

	@Id //Se agrega por que todas las tablas tienen un identificador unico 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Este estereotipo se agrega cuando tenemos el ID autoincrementable
	
	@Column(name = "ID", columnDefinition = "NUMBER", nullable = false)
	private long idPelicula;
	
	@Column(name = "CLAVE", columnDefinition = "INT", nullable = false)
	private Integer clave;
	
	@Column(name = "NOMBRE", columnDefinition = "String", nullable = false)
	private String nombre;
	
	@Column(name ="GENERO", columnDefinition = "VARCHAR2(80)", nullable = false)
	private String genero;
	
	@Column(name ="PRECIO", columnDefinition = "Float", nullable = false)
	private Float precio;
	
	
}
