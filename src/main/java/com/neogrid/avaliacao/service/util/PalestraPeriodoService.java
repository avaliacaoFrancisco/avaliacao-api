package com.neogrid.avaliacao.service.util;

import java.util.ArrayList;
import java.util.List;

import com.neogrid.avaliacao.config.ConferenciaConfig;
import com.neogrid.avaliacao.model.Palestra;

public class PalestraPeriodoService {

	
	public static void alocarPalestras(List<List<Palestra>> listAM, List<List<Palestra>> listPM,
			List<Palestra> palestraList, int totalDias) {
		for (int i = 0; i < totalDias; i++) {
			List<Palestra> palestrasAMList = alocarPalestrasPeriodo2(palestraList, true);
			List<Palestra> palestrasPMList = alocarPalestrasPeriodo2(palestraList, false);
			listAM.add(palestrasAMList);
			listPM.add(palestrasPMList);
		}
	}

	public static List<Palestra> alocarPalestrasPeriodo2(List<Palestra> palestraList, boolean isAMList) {

		int minAM = ConferenciaConfig.TOTAL_MIN_AM;
		int minPM = ConferenciaConfig.TOTAL_MIN_PM;

		int periodoMaximo = isAMList ? minAM : minPM;
		int quntPalestras = palestraList.size();
		List<Palestra> innerPalestraList = new ArrayList<Palestra>();

		int countWhile = 0;
		int duracaoPeriodo = 0;
		while (countWhile != quntPalestras) {
			int currentCount = countWhile;
			countWhile++;
			Palestra palestra = palestraList.get(currentCount);
			if (palestra.isAgendada())
				continue;

			int duracao = palestra.getDuracao();
			if (duracao > periodoMaximo || duracao + duracaoPeriodo > periodoMaximo)
				continue;

			innerPalestraList.add(palestra);
			palestra.setAgendada(true);
			duracaoPeriodo += duracao;

			if (duracaoPeriodo == periodoMaximo)
				break;
		}

		return innerPalestraList;
	}

}
