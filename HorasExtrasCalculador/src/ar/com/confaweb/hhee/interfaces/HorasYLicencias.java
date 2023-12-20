package ar.com.confaweb.hhee.interfaces;

import java.time.LocalDate;

public interface HorasYLicencias {
	Integer calcularTotalHorasPorMes(LocalDate mes);
	void cargarLicCompensatoriaPorHoraSobrefranco();

}
