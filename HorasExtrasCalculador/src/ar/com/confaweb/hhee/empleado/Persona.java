package ar.com.confaweb.hhee.empleado;

import java.time.LocalDate;
import java.util.Objects;

import ar.com.confaweb.hhee.dominio.HoraExtra;

public abstract class Persona {
	protected String nombre;
	protected String apellido;
	protected LocalDate fechaNac;
	protected Integer edad;
	protected Integer dni;

	public Persona(String nombre,String apellido,Integer dni,Integer edad,LocalDate fechaNac ) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni=dni;
		this.edad=edad;
		this.fechaNac=fechaNac;
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public abstract Boolean registrarHhee(HoraExtra hhee);
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}



	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac + ", edad=" + edad
				+ ", dni=" + dni + "]";
	}



	

}
