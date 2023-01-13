package dad.calendario.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static int Primerdia(int anio, int mes) {
		Calendar Calendario = Calendar.getInstance();
		Calendario.set(anio, mes - 1, 1);
		int dia = Calendario.get(Calendar.DAY_OF_WEEK);
		return (dia == 1) ? 7 : dia - 1;
	}
	
	public static int Ultimodia(int anio, int mes) {
		LocalDate primero = LocalDate.of(anio, mes, 1);
		return primero.plusMonths(1).minusDays(1).getDayOfMonth();
	}
	
	public static Date Nuevafecha(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();
	}
	
}
