package ar.com.confaweb.hhee.cuentasDeTiempo;

import java.util.List;

import ar.com.confaweb.hhee.enums.Motivo;
import ar.com.confaweb.hhee.enums.Unidad;

public class Licencia {
	private String descripcion;
	private String codigo;
	private Integer cantidad;
	private Motivo motivo;
	private Unidad unidad;
	
	public Licencia(String descripcion, String codigo, Integer cantidad, Motivo motivo,Unidad unidad) {
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.motivo = motivo;
		this.unidad=unidad;
				
	}
	public Licencia(Integer cantidad,Motivo motivo) {
		this.cantidad=cantidad;
		this.motivo=motivo;
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
	public Unidad getUnidad() {
		return unidad;
	}
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	public void  sumarCantidad(Integer cantidad) {
		Integer cantidadActual=0;
		cantidadActual=this.getCantidad()+cantidad;
		this.setCantidad(cantidadActual);
		
	}

	
}
