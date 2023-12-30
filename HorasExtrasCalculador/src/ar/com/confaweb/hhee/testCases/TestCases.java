package ar.com.confaweb.hhee.testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ar.com.confaweb.hhee.cuentasDeTiempo.Licencia;
import ar.com.confaweb.hhee.dominio.HoraExtra;
import ar.com.confaweb.hhee.empleado.Empleado;
import ar.com.confaweb.hhee.empleado.Persona;
import ar.com.confaweb.hhee.enums.Categoria;
import ar.com.confaweb.hhee.enums.HoraTipo;
import ar.com.confaweb.hhee.enums.Motivo;
import ar.com.confaweb.hhee.enums.Unidad;
import ar.com.confaweb.hhee.exceptions.CantidadLicenciaSolicitadaMayorASaldoException;
import ar.com.confaweb.hhee.exceptions.FaltaINgresarDatosDElEmpleadoException;
import ar.com.confaweb.hhee.exceptions.LicenciaSinSaldoException;
import ar.com.confaweb.hhee.exceptions.NoSeRegistranHorasExtrasEnElMesException;
import ar.com.confaweb.hhee.exceptions.NoSeRegistranHorasExtrasEnLaFechaException;

public class TestCases {

	@Test // #1
	public void registrarHoraExtra() {
//		PREPARACION
		String nombreEmp = "Julian", apellidoEmp = "Rooswell";
		Integer dni = 111111, registroEmpl = 000001, cantidadHhee = 6;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA;
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2000, 01, 01),
				fechaIngreso = LocalDate.of(2000, 1, 1), fechaHhee = LocalDate.of(2022, 01, 10);
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
//		EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidadHhee);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);
		((Empleado) empleado1).registrarHhee(hhee);

//		VALIDACION
		assertTrue(((Empleado) empleado1).registrarHhee(hhee));
	}

	@Test // #2
	public void sumarHoraExtraRegistrada() {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell";
		Integer dni = 111111, registroEmpl = 000001, cantidadHhee = 6;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.VACACIONES, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2000, 01, 01),
				fechaIngreso = LocalDate.of(2000, 1, 1), fechaHhee = LocalDate.of(2022, 01, 10),
				fechaHhee1 = LocalDate.of(2022, 01, 11);

//		EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidadHhee);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidadHhee);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);
		// ArrayList<HoraExtra> registroHhee=new ArrayList<HoraExtra>();
		((Empleado) empleado1).registrarHhee(hhee);
		((Empleado) empleado1).registrarHhee(hhee1);
		((Empleado) empleado1).sumarHhee();

//		VALIDACION
		Integer ve = 12;
		Integer vo = ((Empleado) empleado1).sumarHhee();

		assertEquals(ve, vo);
	}

	@Test // #3
	public void queSePuedaRegistrarLicencia() {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "vacaciones sin adelanto", codigo = "vv01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.VACACIONES, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2000, 01, 01),
				fechaIngreso = LocalDate.of(2000, 1, 1), fechaHhee = LocalDate.of(2022, 01, 10),
				fechaHhee1 = LocalDate.of(2022, 01, 11);

//		EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);
		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);
		// ArrayList<HoraExtra> registroHhee=new ArrayList<HoraExtra>();
		((Empleado) empleado1).registrarLicencia(licencia);

//		VALIDACION 

		assertTrue(((Empleado) empleado1).registrarLicencia(licencia));
	}

	@Test // #4
	public void queSePuedaConusultarHHEEPorFecha_SiElResultadoEsNuloLanzaExcepcion()
			throws NoSeRegistranHorasExtrasEnLaFechaException {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "vacaciones sin adelanto", codigo = "vv01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.VACACIONES, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2000, 01, 01),
				fechaIngreso = LocalDate.of(2000, 1, 1), fechaHhee = LocalDate.of(2022, 01, 10),
				fechaHhee1 = LocalDate.of(2022, 01, 11), fechaConsulta = LocalDate.of(2022, 01, 11);

//		EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);

		((Empleado) empleado1).registrarHhee(hhee1);
		((Empleado) empleado1).consultarHHEEPorFecha(fechaConsulta);

//		VALIDACION 
		Integer ve = 6;
		Integer vo = ((Empleado) empleado1).consultarHHEEPorFecha(fechaConsulta);

		assertEquals(ve, vo);
	}

	@Test // #6
	public void queSePuedaDarDeAltaEmpleadoEnSistema_LanzaExcepcionPorDatoNoCorroborado()
			throws FaltaINgresarDatosDElEmpleadoException {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "vacaciones sin adelanto", codigo = "vv01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.VACACIONES, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2014, 10, 11),
				fechaIngreso = LocalDate.of(2021, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11);

//		EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);

//		VALIDACION 
		assertTrue(((Empleado) empleado1).datosValidados());

	}

	@Test // #7
	public void siLaHheeEsCompensatoriaSumaMismaCantidadDeLicienciaCompensatoria() {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "descanso compensatorio", codigo = "dc01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.DESCANSO_COMPENSATORIO, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.COMPENSATORIA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2021, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11);

//		EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);

		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);

		((Empleado) empleado1).registrarHhee(hhee);
		((Empleado) empleado1).registrarLicencia(licencia);
		((Empleado) empleado1).cargarLicCompensatoriaPorHoraSobrefranco();

//		VALIDACION 
		Integer ve = 12;
		Integer vo = licencia.getCantidad();
		assertEquals(ve, vo);

	}

	@Test // #8
	public void QueSePuedaConsumirLicenciaYAjusteSaldo_LanzaExcepcionSiSeQuiereConsumirlicenciaSinSaldo() throws LicenciaSinSaldoException, CantidadLicenciaSolicitadaMayorASaldoException {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "descanso compensatorio", codigo = "dc01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.DESCANSO_COMPENSATORIO, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.COMPENSATORIA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2021, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11),
				fechaInicioLicencia=LocalDate.of(2022, 02, 15),fechaFinLicencia=LocalDate.of(2022, 02, 18);
		

//		EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);

		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);

		((Empleado) empleado1).registrarHhee(hhee);
		((Empleado) empleado1).registrarLicencia(licencia);
		((Empleado) empleado1).consumirLicencia(motivo,fechaInicioLicencia,fechaFinLicencia);

//		VALIDACION 
		Integer ve = 3;
		Integer vo = licencia.getCantidad();
		assertEquals(ve, vo);

	}

	@Test // #9
	public void queSePuedaDConsultarCantidadDeHorasExtrasPorNumeroMes()
			throws NoSeRegistranHorasExtrasEnElMesException {

//			PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "descanso compensatorio", codigo = "dc01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6, mesNumero = 01;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.DESCANSO_COMPENSATORIO, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.COMPENSATORIA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2021, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11);

//			EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);

		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);

		((Empleado) empleado1).registrarHhee(hhee);
		((Empleado) empleado1).registrarHhee(hhee);
		((Empleado) empleado1).registrarLicencia(licencia);
		((Empleado) empleado1).cargarLicCompensatoriaPorHoraSobrefranco();

//			VALIDACION 
		Integer ve = 12;
		Integer vo = ((Empleado) empleado1).consultarHHEEPorMesNumero(mesNumero);
		assertEquals(ve, vo);

	}

	@Test // #9.1
	public void queSePuedaDConsultarCantidadDeHorasExtrasPorNombreMesEnglish()
			throws NoSeRegistranHorasExtrasEnElMesException {

//			PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "descanso compensatorio", codigo = "dc01",
				mesNombre = "january";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6, mesNumero = 02;
		Categoria categoria = Categoria.A;
		Motivo motivo = Motivo.DESCANSO_COMPENSATORIO, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.COMPENSATORIA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 1000.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2021, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11);

//			EJECUCION
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);

		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);

		((Empleado) empleado1).registrarHhee(hhee);
		((Empleado) empleado1).registrarHhee(hhee);
		((Empleado) empleado1).registrarLicencia(licencia);
		((Empleado) empleado1).cargarLicCompensatoriaPorHoraSobrefranco();

//			VALIDACION 
		Integer ve = 12;
		Integer vo = ((Empleado) empleado1).consultarHHEEPorMesNombreEnglish(mesNombre);
		assertEquals(ve, vo);

	}

	@Test // #10
	public void queSePuedaCalcualrValorHoraPOrCategoria() {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "descanso compensatorio", codigo = "dc01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6;
		Categoria categoria = Categoria.D;
		Motivo motivo = Motivo.DESCANSO_COMPENSATORIO, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 0.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2021, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11);

//		EJECUCION

		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);
		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);

//		VALIDACION 

		Double ve = 3000.00;
		Double vo = ((Empleado) empleado1).calcularValorHoraPorCategoria();
		assertEquals(ve, vo, .01);

	}
	@Test // #11
	public void queSePuedaCalcularAntiguedad() {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "descanso compensatorio", codigo = "dc01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6;
		Categoria categoria = Categoria.D;
		Motivo motivo = Motivo.DESCANSO_COMPENSATORIO, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 0.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2020, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11);

//		EJECUCION

		
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);
		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);

//		VALIDACION 

		Integer ve = 3;
		Integer vo = ((Empleado) empleado1).calcularAntiguedad();
		assertEquals(ve, vo);

	}

	@Test // #12
	public void queSePuedaRneovarLicenciaMotivoVacacionesPorAnitiguedad() {
//		PREPARACION

		String nombreEmp = "Julian", apellidoEmp = "Rooswell", descripcion = "descanso compensatorio", codigo = "dc01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6,anioActual=LocalDate.now().getYear();
		Categoria categoria = Categoria.D;
		Motivo motivo = Motivo.VACACIONES, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 0.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2000, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11),
				fechaActualizacion=LocalDate.of( anioActual, 10, 01);

//		EJECUCION

		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad);
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);
		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);
		

//		VALIDACION 

		Integer ve = 35;
		Integer vo = ((Empleado) empleado1).actualizarSaldoVacaciones();
		assertEquals(ve, vo);

	}
	@Test // #12
	public void queSePuedaOrdenarListaLicenciaPorDescripcion() {
//		PREPARACION

		String  nombreEmp = "Julian", apellidoEmp = "Rooswell",descripcion = "descanso compensatorio",descripcion_1="vacaciones",descripcion_2="baja medica", codigo = "dc01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6,anioActual=LocalDate.now().getYear();
		Categoria categoria = Categoria.D;
		Motivo motivo = Motivo.VACACIONES, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 0.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2000, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11),
				fechaActualizacion=LocalDate.of( anioActual, 10, 01);

//		EJECUCION

		
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);
		Licencia licencia = new Licencia(descripcion, codigo, cantidad, motivo, unidad);
		Licencia licencia_1 = new Licencia(descripcion_1, codigo, cantidad, motivo, unidad);
		Licencia licencia_2 = new Licencia(descripcion_2, codigo, cantidad, motivo, unidad);
		
		((Empleado)empleado1).registrarLicencia(licencia);
		((Empleado)empleado1).registrarLicencia(licencia_1);
		((Empleado)empleado1).registrarLicencia(licencia_2);
		
		Collections.sort(((Empleado)empleado1).getRegistroDeHoras());
		

//		VALIDACION 

		String ve=descripcion_2;
		String vo=((Empleado)empleado1).getRegistroLicencias().get(0).getDescripcion();
		assertEquals(ve, vo);
		String ve1=descripcion;
		String vo1=((Empleado)empleado1).getRegistroLicencias().get(1).getDescripcion();
		assertEquals(ve1, vo1);
		String ve2=descripcion_1;
		String vo2=((Empleado)empleado1).getRegistroLicencias().get(2).getDescripcion();
		assertEquals(ve2, vo2);



	}
	@Test // #13
	public void queSePuedaOrdenarListaHheePorCantidadDeMayorAMenor() {
//		PREPARACION

		String  nombreEmp = "Julian", apellidoEmp = "Rooswell",descripcion = "descanso compensatorio",descripcion_1="vacaciones",descripcion_2="baja medica", codigo = "dc01";
		Integer dni = 111111, registroEmpl = 000001, cantidad = 6, cantidad_1 =12, cantidad_2 =18, cantidad_3 =24,anioActual=LocalDate.now().getYear();
		Categoria categoria = Categoria.D;
		Motivo motivo = Motivo.VACACIONES, motivo1 = Motivo.VACACIONES;
		HoraTipo tipo = HoraTipo.DIURNA, tipo1 = HoraTipo.DIURNA;
		Unidad unidad = Unidad.DIA_LABORAL;
		LocalTime horaInicio = LocalTime.of(06, 00), horaFin = LocalTime.of(12, 00);
		Double valorHora = 0.00;
		LocalDate fecha = LocalDate.now(), fechaNac = LocalDate.of(2004, 10, 11),
				fechaIngreso = LocalDate.of(2000, 01, 22), fechaHhee = LocalDate.of(22, 01, 10),
				fechaHhee1 = LocalDate.of(22, 01, 11), fechaConsulta = LocalDate.of(22, 01, 11),
				fechaActualizacion=LocalDate.of( anioActual, 10, 01);

//		EJECUCION

		
		Persona empleado1 = new Empleado(nombreEmp, apellidoEmp, dni, registroEmpl, fechaNac, categoria,
				valorHora, fechaIngreso);
		HoraExtra hhee = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad);
		HoraExtra hhee_1 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad_1);
		HoraExtra hhee_2 = new HoraExtra(horaInicio, horaFin, fechaHhee, tipo, motivo, cantidad_2);
		HoraExtra hhee_3 = new HoraExtra(horaInicio, horaFin, fechaHhee1, tipo1, motivo1, cantidad_3);
		
		((Empleado)empleado1).registrarHhee(hhee);
		((Empleado)empleado1).registrarHhee(hhee_1);;
		((Empleado)empleado1).registrarHhee(hhee_2);
		((Empleado)empleado1).registrarHhee(hhee_3);
		
		Collections.reverse(((Empleado)empleado1).getRegistroDeHoras());
		

//		VALIDACION 

		Integer ve=cantidad_3;
		Integer vo=((Empleado)empleado1).getRegistroDeHoras().get(0).getCantidadHhee();
		assertEquals(ve, vo);
		Integer ve1=cantidad_2;
		Integer vo1=((Empleado)empleado1).getRegistroDeHoras().get(1).getCantidadHhee();
		assertEquals(ve1, vo1);
		Integer ve2=cantidad_1;
		Integer vo2=((Empleado)empleado1).getRegistroDeHoras().get(2).getCantidadHhee();
		assertEquals(ve2, vo2);
		Integer ve3=cantidad;
		Integer vo3=((Empleado)empleado1).getRegistroDeHoras().get(3).getCantidadHhee();
		assertEquals(ve3, vo3);



	}



}
