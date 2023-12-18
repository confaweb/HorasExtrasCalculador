package ar.com.confaweb.hhee.testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.com.confaweb.hhee.dominio.HoraExtra;
import ar.com.confaweb.hhee.empleado.Empleado;
import ar.com.confaweb.hhee.empleado.Persona;
import ar.com.confaweb.hhee.enums.Categoria;
import ar.com.confaweb.hhee.enums.HoraTipo;
import ar.com.confaweb.hhee.enums.Motivo;

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

}
