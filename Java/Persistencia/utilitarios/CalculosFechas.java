package utilitarios;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class CalculosFechas {

	private static CalculosFechas instancia;

	public CalculosFechas() {
	}

	public static CalculosFechas getInstancia() {
		if (instancia == null)
			instancia = new CalculosFechas();
		return instancia;
	}

	public java.sql.Date deStringADateSql(String fecha) {
		String anio = fecha.substring(0, 4);
		String mes = fecha.substring(5, 7);
		String dia = fecha.substring(8, 10);
		// conversion a enteros
		int anioNro = Integer.parseInt(anio) - 1900;
		int mesNro = Integer.parseInt(mes) - 1;
		int diaNro = Integer.parseInt(dia);
		// seteo de un Date
		java.sql.Date resultado = new java.sql.Date(anioNro, mesNro, diaNro);
		return resultado;
	}

	public Timestamp deStringATimestamp(String fecha, String hora) {
		String cadena = fecha.concat(" " + hora + ":00");
		Timestamp ts = Timestamp.valueOf(cadena);
		return ts;
	}
	
	public Date deStringADateUtil(String fecha, String hora) {
		String anio = fecha.substring(0, 4);
		String mes = fecha.substring(5, 7);
		String dia = fecha.substring(8, 10);
		String horas = hora.substring(0, 2);
		String minutos = hora.substring(3, 5);
		// conversion a enteros
		int anioNro = Integer.parseInt(anio) - 1900;
		int mesNro = Integer.parseInt(mes) - 1;
		int diaNro = Integer.parseInt(dia);
		int hsNro = Integer.parseInt(horas);
		int minNro = Integer.parseInt(minutos);
		int segNro = 0;
		// seteo de un Date
		Date resultado = new Date();
		resultado.setYear(anioNro);
		resultado.setMonth(mesNro);
		resultado.setDate(diaNro);
		resultado.setHours(hsNro);
		resultado.setMinutes(minNro);
		resultado.setSeconds(segNro);
		return resultado;
	}

	public Date sumarHorasAFecha(Date fecha, int horas) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.HOUR, horas);
		return calendar.getTime();
	}

	public Date sumarDiasAFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DATE, dias);
		return calendar.getTime();
	}
	
	public Date sumarMesesAFecha(Date fecha, int meses) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, meses);
		return calendar.getTime();
	}

	public boolean puedeAgendar(String fecha) {
		Date fechaPedida = deStringADateUtil(fecha, "00:00");
		Date fechaActual = new Date();
		fechaActual.setHours(0);
		fechaActual.setMinutes(0);
		fechaActual.setSeconds(0);
		Date fechaMaxPermitida = sumarMesesAFecha(fechaActual, 2);
		if (fechaPedida.compareTo(fechaMaxPermitida) > 0)
			return false;
		else
			return true;
	}
	
	public boolean puedeModificarAgenda(String fecha) {
		Date fechaPedida = deStringADateUtil(fecha, "00:00");
		Date fechaActual = new Date();
		fechaActual.setHours(0);
		fechaActual.setMinutes(0);
		fechaActual.setSeconds(0);
		Date fecInicioModif = sumarDiasAFecha(fechaActual, 7);
		if (fechaPedida.compareTo(fecInicioModif) > 0)
			return true;
		else
			return false;
	}
	

}
























