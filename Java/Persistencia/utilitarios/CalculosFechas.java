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

	public Date sumarHorasAFecha(Date fecha, int horas) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.HOUR, horas);
		return calendar.getTime();
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

}
