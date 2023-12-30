package ar.com.confaweb.hhee.dominio;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import ar.com.confaweb.hhee.empleado.Empleado;
import ar.com.confaweb.hhee.empleado.Persona;
import ar.com.confaweb.hhee.exceptions.FaltaINgresarDatosDElEmpleadoException;

public class Empresa {

	private String razonSocial;
	private long cuit;
	private Set<Empleado> listadoEmpleados;

	public Empresa(String razonSocial, long cuit) {
		this.razonSocial=razonSocial;
		this.cuit=cuit;
		this.listadoEmpleados = new TreeSet<Empleado>();
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}
	public Set<Empleado> getListadoEmpleados() {
		return listadoEmpleados;
	}

	public void setListadoEmpleados(Set<Empleado> listadoEmpleados) {
		this.listadoEmpleados = listadoEmpleados;
	}
	public Boolean registrarEmpleado(Persona empleado1) throws FaltaINgresarDatosDElEmpleadoException {
		Boolean empleadoAgregado = false;
		if (((Empleado)empleado1).datosValidados()) {
			empleadoAgregado = listadoEmpleados.add((Empleado) empleado1);
		}

		return empleadoAgregado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cuit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return cuit == other.cuit;
	}

	@Override
	public String toString() {
		return "Empresa [razonSocial=" + razonSocial + ", cuit=" + cuit + "]";
	}

}
