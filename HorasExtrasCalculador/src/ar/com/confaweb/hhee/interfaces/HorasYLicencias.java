package ar.com.confaweb.hhee.interfaces;

import java.time.LocalDate;

import ar.com.confaweb.hhee.enums.Categoria;

public interface HorasYLicencias {
	Integer calcularTotalHorasPorMes(LocalDate mes);
	void cargarLicCompensatoriaPorHoraSobrefranco();
	Double calcularValorHoraPorCategoria();

}
