package com.wssAngularProject.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carpets")
public class Carpet{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nombre;
	
	@OneToMany(mappedBy = "carpet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Task>tasks;
	

//	@Column(name = "nimbreee") //se refiere al nombre en la base de datos
	

	public Carpet(String nombre) {
		this.nombre=nombre;
	}

	public Carpet() {
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

}
