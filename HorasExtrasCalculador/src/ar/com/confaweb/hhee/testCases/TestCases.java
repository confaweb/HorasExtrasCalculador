package ar.com.confaweb.hhee.testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.com.confaweb.hhee.cuentasDeTiempo.Licencia;
import ar.com.confaweb.hhee.dominio.HoraExtra;
import ar.com.confaweb.hhee.empleado.Empleado;
import ar.com.confaweb.hhee.empleado.Persona;
import ar.com.confaweb.hhee.enums.Categoria;
import ar.com.confaweb.hhee.enums.HoraTipo;
import ar.com.confaweb.hhee.enums.Motivo;
import ar.com.confaweb.hhee.exceptions.FaltaINgresarDatosDElEmpleadoException;
import ar.com.confaweb.hhee.exceptions.NoSeRegistranHorasExtrasEnLaFechaException;

public class TestCases {

	@Test //#1
	public void registrarHoraExtra() {
//		PREPARACION
		String nombreEmp="Julian",apellidoEmp="Rooswell";
		Integer dni=111111,registroEmpl=000001,cantidadHhee=6,edad=33;
		Categoria categoria= Categoria.A;
		Motivo motivo=Motivo.VACACIONES;
		HoraTipo tipo=HoraTipo.DIURNA;
		Double valorHora=1000.00;
		LocalDate fecha=LocalDate.now(),fechaNac=LocalDate.of(00, 01, 01),fechaIngreso=LocalDate.of(00, 1, 1),fechaHhee=LocalDate.of(22, 01, 10);
		LocalTime horaInicio=LocalTime.of(06, 00),horaFin=LocalTime.of(12, 00);
//		EJECUCION
		HoraExtra hhee=new HoraExtra(horaInicio,horaFin,fechaHhee,tipo, motivo);
		Persona empleado1= new Empleado(nombreEmp,apellidoEmp,dni,registroEmpl,edad,fechaNac,categoria,valorHora,fechaIngreso);
		 empleado1.registrarHhee(hhee);
		
//		VALIDACION
		assertTrue( empleado1.registrarHhee(hhee));
	}
	@Test  //#2
	public void sumarHoraExtraRegistrada() {
//		PREPARACION
		
		String nombreEmp="Julian",apellidoEmp="Rooswell";
		Integer dni=111111,registroEmpl=000001,cantidadHhee=6,edad=33;
		Categoria categoria= Categoria.A;
		Motivo motivo=Motivo.VACACIONES,motivo1=Motivo.VACACIONES;
		HoraTipo tipo=HoraTipo.DIURNA,tipo1=HoraTipo.DIURNA;
		LocalTime horaInicio=LocalTime.of(06, 00),horaFin=LocalTime.of(12, 00);
		Double valorHora=1000.00;
		LocalDate fecha=LocalDate.now(),fechaNac=LocalDate.of(00, 01, 01),fechaIngreso=LocalDate.of(00, 1, 1),fechaHhee=LocalDate.of(22, 01, 10),
				fechaHhee1=LocalDate.of(22, 01, 11);
		
		
//		EJECUCION
		HoraExtra hhee=new HoraExtra(horaInicio,horaFin,fechaHhee,tipo, motivo);
		HoraExtra hhee1=new HoraExtra(horaInicio,horaFin,fechaHhee1,tipo1, motivo1);
		Persona empleado1= new Empleado(nombreEmp,apellidoEmp,dni,registroEmpl,edad,fechaNac,categoria,valorHora,fechaIngreso);
		 //ArrayList<HoraExtra> registroHhee=new ArrayList<HoraExtra>();
		 empleado1.registrarHhee(hhee);
		 empleado1.registrarHhee(hhee1);
		((Empleado) empleado1).sumarHhee();
		
//		VALIDACION
		Integer ve =12;
		Integer vo=((Empleado) empleado1).sumarHhee();
		
		assertEquals(ve,vo);
	}
	@Test  //#3
	public void queSePuedaRegistrarLicencia() {
//		PREPARACION
		
		String nombreEmp="Julian",apellidoEmp="Rooswell",descripcion="vacaciones sin adelanto",codigo="vv01";
		Integer dni=111111,registroEmpl=000001,cantidad=6,edad=33;
		Categoria categoria= Categoria.A;
		Motivo motivo=Motivo.VACACIONES,motivo1=Motivo.VACACIONES;
		HoraTipo tipo=HoraTipo.DIURNA,tipo1=HoraTipo.DIURNA;
		LocalTime horaInicio=LocalTime.of(06, 00),horaFin=LocalTime.of(12, 00);
		Double valorHora=1000.00;
		LocalDate fecha=LocalDate.now(),fechaNac=LocalDate.of(00, 01, 01),fechaIngreso=LocalDate.of(00, 1, 1),fechaHhee=LocalDate.of(22, 01, 10),
				fechaHhee1=LocalDate.of(22, 01, 11);
		
		
//		EJECUCION
		HoraExtra hhee=new HoraExtra(horaInicio,horaFin,fechaHhee,tipo, motivo);
		HoraExtra hhee1=new HoraExtra(horaInicio,horaFin,fechaHhee1,tipo1, motivo1);
		Persona empleado1= new Empleado(nombreEmp,apellidoEmp,dni,registroEmpl,edad,fechaNac,categoria,valorHora,fechaIngreso);
		Licencia licencia=new Licencia(descripcion, codigo, cantidad, motivo);
		 //ArrayList<HoraExtra> registroHhee=new ArrayList<HoraExtra>();
		 ((Empleado) empleado1).registrarLicencia(licencia);
		 
		
//		VALIDACION 
		
		
		assertTrue(((Empleado) empleado1).registrarLicencia(licencia));
	}
	@Test  //#4
	public void queSePuedaConusultarHHEEPorFecha_SiElResultadoEsNuloLanzaExcepcion() throws NoSeRegistranHorasExtrasEnLaFechaException {
//		PREPARACION
		
		String nombreEmp="Julian",apellidoEmp="Rooswell",descripcion="vacaciones sin adelanto",codigo="vv01";
		Integer dni=111111,registroEmpl=000001,cantidad=6,edad=33;
		Categoria categoria= Categoria.A;
		Motivo motivo=Motivo.VACACIONES,motivo1=Motivo.VACACIONES;
		HoraTipo tipo=HoraTipo.DIURNA,tipo1=HoraTipo.DIURNA;
		LocalTime horaInicio=LocalTime.of(06, 00),horaFin=LocalTime.of(12, 00);
		Double valorHora=1000.00;
		LocalDate fecha=LocalDate.now(),fechaNac=LocalDate.of(00, 01, 01),fechaIngreso=LocalDate.of(00, 1, 1),fechaHhee=LocalDate.of(22, 01, 10),
				fechaHhee1=LocalDate.of(22, 01, 11),fechaConsulta=LocalDate.of(22,01,11);
				
		
		
//		EJECUCION
		HoraExtra hhee=new HoraExtra(horaInicio,horaFin,fechaHhee,tipo, motivo);
		HoraExtra hhee1=new HoraExtra(horaInicio,horaFin,fechaHhee1,tipo1, motivo1);
		Persona empleado1= new Empleado(nombreEmp,apellidoEmp,dni,registroEmpl,edad,fechaNac,categoria,valorHora,fechaIngreso);
		
		empleado1.registrarHhee(hhee);
		((Empleado) empleado1).consultarHheePorFecha( fechaConsulta);
		 
		 
		
//		VALIDACION 
		Integer ve=6;
		Integer vo=((Empleado) empleado1).consultarHHEEPorFecha( fechaConsulta);
		
		
		assertEquals(ve,vo);
	}

	@Test  //#6
	public void queSePuedaDarDeAltaEmpleadoEnSistema_LanzaExcepcionPorDatoNoCorroborado() throws FaltaINgresarDatosDElEmpleadoException {
//		PREPARACION
		
		String nombreEmp="Julian",apellidoEmp="Rooswell",descripcion="vacaciones sin adelanto",codigo="vv01";
		Integer dni = 111111,registroEmpl=000001,cantidad=6,edad=20;
		Categoria categoria= Categoria.A;
		Motivo motivo=Motivo.VACACIONES,motivo1=Motivo.VACACIONES;
		HoraTipo tipo=HoraTipo.DIURNA,tipo1=HoraTipo.DIURNA;
		LocalTime horaInicio=LocalTime.of(06, 00),horaFin=LocalTime.of(12, 00);
		Double valorHora=1000.00;
		LocalDate fecha=LocalDate.now(),fechaNac=LocalDate.of(24, 10, 15),fechaIngreso=LocalDate.of(00,01, 01),fechaHhee=LocalDate.of(22, 01, 10),
				fechaHhee1=LocalDate.of(22, 01, 11),fechaConsulta=LocalDate.of(22,01,11);
				
		
		
//		EJECUCION
		HoraExtra hhee=new HoraExtra(horaInicio,horaFin,fechaHhee,tipo, motivo);
		HoraExtra hhee1=new HoraExtra(horaInicio,horaFin,fechaHhee1,tipo1, motivo1);
		Persona empleado1= new Empleado(nombreEmp,apellidoEmp,dni,registroEmpl,edad,fechaNac,categoria,valorHora,fechaIngreso);
		
		
		 
		 
		
//		VALIDACION 
		assertTrue(((Empleado) empleado1).datosValidados( ));
	
	}
	@Test  //#7
	public void siLaHheeEsCompensatoriaSumaMismaCantidadDeLicienciaCompensatoria() {
		
	}
	@Test  //#8
	public void QueSePuedaConsumirLicenciaYAjusteSaldo_LanzaExcepcionSiSeQuiereConsumirlicenciaSinSaldo() {
		
	}
	@Test  //#9
	public void queSePuedaDConsultarCantidadDeHorasExtrasPorMes() {
		
	}

}
