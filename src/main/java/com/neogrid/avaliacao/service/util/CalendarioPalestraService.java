package com.neogrid.avaliacao.service.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.neogrid.avaliacao.config.ConferenciaConfig;
import com.neogrid.avaliacao.model.Conferencia;
import com.neogrid.avaliacao.model.Palestra;

public class CalendarioPalestraService {

	private static final String FORMAT_HH_MMA = "hh:mma ";

	public static List<Conferencia> processarCalendarioPalestras(List<List<Palestra>> palestrasAMList,
			List<List<Palestra>> palestrasPMList) {

		List<Conferencia> conferenciasList = new ArrayList<>();

		int totalDias = palestrasAMList.size();

		for (int dia = 0; dia < totalDias; dia++) {
			Conferencia conferencia = new Conferencia();
			conferencia.setDia(dia + 1);
			List<Palestra> palestrasList = new ArrayList<Palestra>();

			Date date = getInitDate();

			SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_HH_MMA);
			String horarioStr = dateFormat.format(date);

			horarioStr = agendarPalestra(palestrasAMList.get(dia), dia, palestrasList, date, horarioStr);
			horarioStr = agendarLunch(palestrasList, date, horarioStr);
			horarioStr = agendarPalestra(palestrasPMList.get(dia), dia, palestrasList, date, horarioStr);
			agendarNetworking(palestrasList, horarioStr);

			conferencia.setPalestrasList(palestrasList);
			conferenciasList.add(conferencia);
		}

		return conferenciasList;
	}

	private static void agendarNetworking(List<Palestra> palestraList, String horarioStr) {
		Palestra networkingPalestra = new Palestra(ConferenciaConfig.NETWORKING_STR, ConferenciaConfig.NETWORKING_MIN);
		networkingPalestra.setHorario(horarioStr);
		palestraList.add(networkingPalestra);
	}

	private static String agendarLunch(List<Palestra> palestraList, Date date, String horarioStr) {
		int tempo = 60;
		Palestra lunchPalestra = new Palestra(ConferenciaConfig.LUNCH_STR, ConferenciaConfig.LUNCH_MIN);
		lunchPalestra.setHorario(horarioStr);
		palestraList.add(lunchPalestra);
		horarioStr = addFaixaHorario(date, tempo);
		return horarioStr;
	}

	private static String agendarPalestra(List<Palestra> palestrasPeriodoList, int dias, List<Palestra> palestraList,
			Date date, String horarioStr) {
		for (Palestra palestra : palestrasPeriodoList) {
			palestra.setHorario(horarioStr);
			horarioStr = addFaixaHorario(date, palestra.getDuracao());
			palestraList.add(palestra);
		}
		return horarioStr;
	}

	public static String addFaixaHorario(Date date, int timeDuration) {
		long timeInLong = date.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_HH_MMA);
		long timeDurationInLong = timeDuration * 60 * 1000;
		long newTimeInLong = timeInLong + timeDurationInLong;
		date.setTime(newTimeInLong);
		return dateFormat.format(date);
	}

	public static Date getInitDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.HOUR_OF_DAY, 9);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	public static Date getLunchtDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.HOUR_OF_DAY, 12);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
}
