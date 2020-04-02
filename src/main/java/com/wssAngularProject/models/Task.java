package com.wssAngularProject.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

//	@Column(name = "nimbreee") //se refiere al nombre en la base de datos
	private String nombre;
	private boolean pendiente;
	//private int idCarpet;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn (name = "id_carpet", nullable = true) //cuando buscas tarea, identifica cual es
	private Carpet carpet;

	public Task(String nombre, boolean pendiente, Carpet carpet) {
		this.carpet = carpet;
		this.nombre = nombre;
		this.pendiente = pendiente;
	}

	public Task() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}

	public Carpet getCarpet() {
		return carpet;
	}

	public void setCarpet(Carpet carpet) {
		this.carpet = carpet;
	}

	/*public int getIdCarpet() {
		return idCarpet;
	}

	public void setIdCarpet(int idCarpet) {
		this.idCarpet = idCarpet;
	}*/

}
