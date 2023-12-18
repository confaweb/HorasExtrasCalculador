package ar.com.confaweb.hhee.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.com.confaweb.hhee.enums.HoraTipo;
import ar.com.confaweb.hhee.enums.Motivo;

public class HoraExtra {

	private Integer cantidadHhee;
	private LocalDate fechaHhee;
	private Motivo motivo;
	private HoraTipo tipo;
	private LocalTime horaInicio;
	private LocalTime horaFin;

	public HoraExtra(LocalTime horaInicio,LocalTime horaFin, LocalDate fechaHhee, HoraTipo tipo,Motivo motivo) {
		this.horaInicio=horaInicio;
		this.horaFin=horaFin;
		this.fechaHhee=fechaHhee;
		this.motivo=motivo;
		this.setTipo(tipo);
	}

	public Integer getCantidadHhee() {
		return cantidadHhee;
	}

	public void setCantidadHhee(Integer cantidadHhee) {
		this.cantidadHhee = cantidadHhee;
	}

	public LocalDate getFechaHhee() {
		return fechaHhee;
	}

	public void setFechaHhee(LocalDate fechaHhee) {
		this.fechaHhee = fechaHhee;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public HoraTipo getTipo() {
		return tipo;
	}

	public void setTipo(HoraTipo tipo) {
		this.tipo = tipo;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	

	

}
