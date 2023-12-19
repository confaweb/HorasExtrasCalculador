package ar.com.confaweb.hhee.empleado;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import ar.com.confaweb.hhee.cuentasDeTiempo.Licencia;
import ar.com.confaweb.hhee.dominio.HoraExtra;
import ar.com.confaweb.hhee.enums.Categoria;
import ar.com.confaweb.hhee.enums.HoraTipo;
import ar.com.confaweb.hhee.exceptions.FaltaINgresarDatosDElEmpleadoException;
import ar.com.confaweb.hhee.exceptions.NoSeRegistranHorasExtrasEnLaFechaException;

public class Empleado extends Persona {

	private Integer registroEmpl;
	private Categoria categoria;
	private Double valorHora;
	private LocalDate fechaIngreso;
	private List<HoraExtra> registroDeHoras;
	private List<Licencia> registroLicencias;

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
		this.registroLicencias = new ArrayList<Licencia>();

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

	public List<Licencia> getRegistroLicencias() {
		return registroLicencias;
	}

	public void setRegistroLicencias(List<Licencia> registroLicencias) {
		this.registroLicencias = registroLicencias;
	}

	public Integer calcularHorasExtrasDelDiaPorFecha(LocalDate dia) {
		Integer cantidadHoras = 0;

		cantidadHoras = this.getRegistroDeHoras().get(dia.getDayOfMonth() - 1).getHoraFin().getHour()
				- this.getRegistroDeHoras().get(dia.getDayOfMonth() - 1).getHoraInicio().getHour();
		return cantidadHoras;
	}

	public Integer calcularHorasExtrasDelDia(LocalTime horaFin, LocalTime horaInicio) {
		Integer cantidadHoras = 0;
		cantidadHoras = horaFin.getHour() - horaInicio.getHour();
		return cantidadHoras;

	}

	public boolean datosValidados() throws FaltaINgresarDatosDElEmpleadoException {
		Boolean datosValidados = false;
		if ( this.edadValidada() &&  this.fechaIngreso.isBefore(LocalDate.now())) {
			datosValidados = true;
			return datosValidados;

		} else

			throw new FaltaINgresarDatosDElEmpleadoException("Datos del empleado sin ingresar");
	}


	private boolean edadValidada() {
		Boolean esMayor = false;
		if (this.edad>=18)
			esMayor = true;
		else
			esMayor = false;
		return esMayor;
	}

	public Integer consultarHHEEPorFecha(LocalDate fecha) throws NoSeRegistranHorasExtrasEnLaFechaException {
		Integer sumatoria = 0;
		Integer cantidadHoras = 0;
		for (HoraExtra hhee : this.registroDeHoras) {
			if (hhee.getFechaHhee().equals(fecha)) {
				cantidadHoras = (hhee.getHoraFin().getHour()) - (hhee.getHoraInicio().getHour());
				sumatoria += cantidadHoras;
			}
		}
		if (sumatoria != 0)
			return sumatoria;
		else
			throw new NoSeRegistranHorasExtrasEnLaFechaException("Fecha sin Registro de HHEE");

	}

	public Integer sumarHhee() {
		Integer sumatoria = 0;
		Integer cantidadHoras = 0;
		for (HoraExtra hhee : this.registroDeHoras) {
			cantidadHoras = (hhee.getHoraFin().getHour()) - (hhee.getHoraInicio().getHour());
			sumatoria += cantidadHoras;
		}
		return sumatoria;
	}

	public Boolean registrarLicencia(Licencia licencia) {
		Boolean licenciaRegistrada = false;
		licenciaRegistrada = registroLicencias.add(licencia);

		return licenciaRegistrada;

	}

	public Integer consultarHheePorFecha(LocalDate fechaConsulta) {
		return 10;

	}

	@Override

	public Boolean registrarHhee(HoraExtra hhee) {
		Boolean horaRegistrada = false;
		horaRegistrada = registroDeHoras.add(hhee);

		return horaRegistrada;

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

}
