package ar.com.confaweb.hhee.cuentasDeTiempo;

import java.util.ArrayList;
import java.util.List;

import ar.com.confaweb.hhee.enums.Motivo;

public class Licencia {
	private String descripcion;
	private String codigo;
	private Integer cantidad;
	private Motivo motivo;
	
	public Licencia(String descripcion, String codigo, Integer cantidad, Motivo motivo) {
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.motivo = motivo;
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	
}
