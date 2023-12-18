package ar.com.confaweb.hhee.empleado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import ar.com.confaweb.hhee.dominio.HoraExtra;
import ar.com.confaweb.hhee.enums.Categoria;

public class Empleado extends Persona {

	private Integer registroEmpl;
	private Categoria categoria;
	private Double valorHora;
	private LocalDate fechaIngreso;
	private List<HoraExtra> registroDeHoras;

	public Empleado(String nombre, String apellido, Integer dni, Integer edad, Integer registroEmpl, LocalDate fechaNac,
			Categoria categoria, Double valorHora, LocalDate fechaIngreso) {
		super(nombre, apellido, dni, edad, fechaNac);
		super.nombre = nombre;
		super.apellido = apellido;
		super.dni = dni;
		super.edad = edad;
		super.fechaNac = this.fechaNac;
		this.registroEmpl = registroEmpl;
		this.categoria = categoria;
		this.valorHora = valorHora;
		this.fechaIngreso = fechaIngreso;
		this.registroDeHoras = new ArrayList<HoraExtra>();

	}

	public Integer getRegistroEmpl() {
		return registroEmpl;
	}

	public void setRegistroEmpl(Integer registroEmpl) {
		this.registroEmpl = registroEmpl;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public List<HoraExtra> getRegistroDeHoras() {
		return registroDeHoras;
	}

	public void setRegistroDeHoras(List<HoraExtra> registroDeHoras) {
		this.registroDeHoras = registroDeHoras;
	}

	public Integer sumarHhee( ) {
		Integer sumatoria = 0;
		Integer cantidadHoras=0;
		for (HoraExtra hhee : this.registroDeHoras) {

			cantidadHoras = (hhee.getHoraFin().getHour()) - (hhee.getHoraInicio().getHour());
			sumatoria += cantidadHoras;
		}
		return sumatoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(registroEmpl);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(registroEmpl, other.registroEmpl);
	}

	@Override

	public Boolean registrarHhee(HoraExtra hhee) {
		Boolean horaRegistrada = false;
		horaRegistrada = registroDeHoras.add(hhee);
		return horaRegistrada;

	}

}
