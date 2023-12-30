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
import ar.com.confaweb.hhee.enums.Motivo;
import ar.com.confaweb.hhee.exceptions.CantidadLicenciaSolicitadaMayorASaldoException;
import ar.com.confaweb.hhee.exceptions.FaltaINgresarDatosDElEmpleadoException;
import ar.com.confaweb.hhee.exceptions.LicenciaSinSaldoException;
import ar.com.confaweb.hhee.exceptions.NoSeRegistranHorasExtrasEnElMesException;
import ar.com.confaweb.hhee.exceptions.NoSeRegistranHorasExtrasEnLaFechaException;
import ar.com.confaweb.hhee.interfaces.HorasYLicencias;

public class Empleado extends Persona implements HorasYLicencias{
	private final Double VALOR_HORA_BASICO = 1000.00;
	private Integer registroEmpl;
	private Categoria categoria;
	private Double valorHora;
	private LocalDate fechaIngreso;
	private List<HoraExtra> registroDeHoras;
	private List<Licencia> registroLicencias;

	public Empleado(String nombre, String apellido, Integer dni, Integer registroEmpl, LocalDate fechaNac,
			Categoria categoria, Double valorHora, LocalDate fechaIngreso) {
		super(nombre, apellido, dni, fechaNac);
		super.nombre = nombre;
		super.apellido = apellido;
		super.dni = dni;
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

	public Double getVALOR_HORA_BASICO() {
		return VALOR_HORA_BASICO;
	}

	public void setRegistroLicencias(List<Licencia> registroLicencias) {
		this.registroLicencias = registroLicencias;
	}

	public Integer calcularHorasExtrasDelDia(LocalTime horaFin, LocalTime horaInicio) {
		Integer cantidadHoras = 0;
		cantidadHoras = horaFin.getHour() - horaInicio.getHour();
		return cantidadHoras;

	}

	public boolean datosValidados() throws FaltaINgresarDatosDElEmpleadoException {
		if (edadValidada() && fechaIngresoValida()) {
			return true;
		} else {
			throw new FaltaINgresarDatosDElEmpleadoException("Datos del empleado sin ingresar");
		}
	}

	private boolean edadValidada() {
		Boolean mayor = false;
		if (calcularEdad() >= 18)
			mayor = true;
		return mayor;
	}

	private boolean fechaIngresoValida() {
		return fechaIngreso.isBefore(LocalDate.now());
	}

	private int calcularEdad() {
		Integer edad = 0;
		edad = LocalDate.now().getYear() - fechaNac.getYear();
		return edad;
	}

	// ************CONSULTA DE HORAS EXTRA SEGUN DISTINTAS
	// REFERENCIAS***********************

	public Integer consultarHHEEPorFecha(LocalDate fecha) throws NoSeRegistranHorasExtrasEnLaFechaException {
		Integer sumatoria = 0;
		Integer cantidadHoras = 0;
		for (HoraExtra hhee : this.registroDeHoras) {
			if (hhee.getFechaHhee().equals(fecha)) {
				cantidadHoras = hhee.getCantidadHhee();
				sumatoria += cantidadHoras;
			}
		}
		if (sumatoria != 0)
			return sumatoria;
		else
			throw new NoSeRegistranHorasExtrasEnLaFechaException("Fecha sin Registro de HHEE");

	}

	public Integer consultarHHEEPorMesNumero(Integer mesNumero) throws NoSeRegistranHorasExtrasEnElMesException {
		Integer sumatoria = 0;
		Integer cantidadHoras = 0;
		for (HoraExtra hhee : this.registroDeHoras) {
			if (hhee.getFechaHhee().getMonthValue() == mesNumero) {
				cantidadHoras = hhee.getCantidadHhee();
				sumatoria += cantidadHoras;
			}
		}
		if (sumatoria != 0)
			return sumatoria;
		else
			throw new NoSeRegistranHorasExtrasEnElMesException("Mes sin Registro de HHEE");
	}

	public Integer consultarHHEEPorMesNombreEnglish(String mesNombre) throws NoSeRegistranHorasExtrasEnElMesException {
		Integer sumatoria = 0;
		Integer cantidadHoras = 0;
		for (HoraExtra hhee : this.registroDeHoras) {
			if (hhee.getFechaHhee().getMonth().toString().equalsIgnoreCase(mesNombre)) {
				cantidadHoras = hhee.getCantidadHhee();
				sumatoria += cantidadHoras;
			}
		}
		if (sumatoria != 0)
			return sumatoria;
		else
			throw new NoSeRegistranHorasExtrasEnElMesException("Mes sin Registro de HHEE");
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

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

	public Boolean registrarHhee(HoraExtra hhee) {
		Boolean horaRegistrada = false;
		horaRegistrada = registroDeHoras.add(hhee);

		return horaRegistrada;

	}

	public void consumirLicencia(Motivo motivo, LocalDate fechaInicioLicencia, LocalDate fechaFinLicencia)
			throws LicenciaSinSaldoException, CantidadLicenciaSolicitadaMayorASaldoException {
		Integer cantidad_Dias_Disponiibles = 0, cantidad_DiasLicenciaATomar = 0, saldo = 0;
		cantidad_Dias_Disponiibles = consultarSaldoLicencia(motivo);
		cantidad_DiasLicenciaATomar = calcularCantidadDiasLicenciaPorFecha(fechaInicioLicencia, fechaFinLicencia);
		saldo = cantidad_Dias_Disponiibles - cantidad_DiasLicenciaATomar;
		if (cantidad_Dias_Disponiibles == 0)
			throw new LicenciaSinSaldoException("sin saldo de Licencia");
		if (cantidad_DiasLicenciaATomar < cantidad_Dias_Disponiibles)

			for (Licencia licencia : registroLicencias) {
				if (licencia.getMotivo().equals(motivo))
					licencia.setCantidad(saldo);
			}
		else
			throw new CantidadLicenciaSolicitadaMayorASaldoException(
					"Se ha solicitado cantidad de licencia mayor a saldo disponible");

	}

	private Integer consultarSaldoLicencia(Motivo motivo) {
		Integer cantidad_Dias = 0;
		for (Licencia licencia : registroLicencias) {
			if (licencia.getMotivo().equals(motivo))
				cantidad_Dias = licencia.getCantidad();
		}

		return cantidad_Dias;
	}

	private Integer calcularCantidadDiasLicenciaPorFecha(LocalDate fechaInicioLicencia, LocalDate fechaFinLicencia) {
		Integer cantidad_Dias_Licencia = 0;
		cantidad_Dias_Licencia = fechaFinLicencia.getDayOfYear() - fechaInicioLicencia.getDayOfYear();

		return cantidad_Dias_Licencia;
	}

	@Override
	public Integer calcularTotalHorasPorMes(LocalDate mes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cargarLicCompensatoriaPorHoraSobrefranco() {
		for (HoraExtra hhee : registroDeHoras) {
			Integer cantidad = 0;
			if (hhee.getTipo().equals(HoraTipo.COMPENSATORIA)) {
				cantidad = hhee.getCantidadHhee();
				sumarHorasCompensatorias(cantidad);

			}
		}

	}

	private void sumarHorasCompensatorias(Integer cantidad) {
		for (Licencia licencia : registroLicencias) {
			if (licencia.getMotivo().equals(Motivo.DESCANSO_COMPENSATORIO))
				licencia.sumarCantidad(cantidad);
		}

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
	public Double calcularValorHoraPorCategoria() {
		switch (categoria) {
		case A: {

			valorHora = VALOR_HORA_BASICO + VALOR_HORA_BASICO * .2;
			break;
		}
		case B: {

			valorHora = VALOR_HORA_BASICO + VALOR_HORA_BASICO * .5;
			break;
		}
		case B1: {

			valorHora = VALOR_HORA_BASICO + VALOR_HORA_BASICO * .6;
			break;
		}
		case C: {

			valorHora = VALOR_HORA_BASICO + VALOR_HORA_BASICO * .9;
			break;
		}
		case C1: {

			valorHora = VALOR_HORA_BASICO + VALOR_HORA_BASICO * .95;
			break;
		}
		case D: {

			valorHora = VALOR_HORA_BASICO + VALOR_HORA_BASICO * 2;
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + categoria);
		}
		return valorHora;
	}

	public Integer calcularAntiguedad() {
		Integer antiguedad = 0;
		antiguedad = LocalDate.now().getYear() - this.fechaIngreso.getYear();
		return antiguedad;
	}

	public Integer actualizarSaldoVacaciones() {

		LocalDate fechaActualizacion = getFechaActualizacion();
		Integer saldoActualizado = 0;
		saldoActualizado = calcucarCantidadVacacionesSegunAnmatiguedad();

		for (Licencia licencia : registroLicencias) {
			if (licencia.getMotivo().equals(Motivo.VACACIONES))
				saldoActualizado += licencia.getCantidad();
		}

		return saldoActualizado;
	}

	private LocalDate getFechaActualizacion() {
		Integer anioActual = LocalDate.now().getYear();
		LocalDate fechaActualizacion = LocalDate.of(anioActual, 10, 01);
		return fechaActualizacion;
	}

	private Integer calcucarCantidadVacacionesSegunAnmatiguedad() {
		Integer cantidad_Dias = 0, antiguedad = 0;
		antiguedad = calcularAntiguedad();
		if (antiguedad <= 1)
			cantidad_Dias = 7;
		else if (antiguedad > 1 && antiguedad <= 5)
			cantidad_Dias = 14;
		else if (antiguedad > 5 && antiguedad <= 10)
			cantidad_Dias = 21;
		else if (antiguedad > 10 && antiguedad <= 15)
			cantidad_Dias = 28;
		else

			cantidad_Dias = 35;

		return cantidad_Dias;

	}

	

	

}
