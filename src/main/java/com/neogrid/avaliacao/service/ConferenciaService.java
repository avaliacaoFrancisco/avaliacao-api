package com.neogrid.avaliacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.neogrid.avaliacao.comparator.PalestraComparator;
import com.neogrid.avaliacao.config.ConferenciaConfig;
import com.neogrid.avaliacao.model.Conferencia;
import com.neogrid.avaliacao.model.Palestra;
import com.neogrid.avaliacao.service.util.CalendarioPalestraService;
import com.neogrid.avaliacao.service.util.PalestraPeriodoService;
import com.neogrid.avaliacao.util.ReadFileUtil;
import com.neogrid.avaliacao.validator.PalestraValidator;

@Service
public class ConferenciaService {


	public List<Conferencia> gerarConferencia() throws Exception {
		List<String> palestrasList = ReadFileUtil.read(this.getDirNameFile());
		List<Conferencia> conferenciasList = new ArrayList<>();
		if(palestrasList != null) 
			conferenciasList = this.processarConferencia(this.gerarPalestras(palestrasList));
		return conferenciasList;
	}

	

	private List<Conferencia> processarConferencia(List<Palestra> palestrasList) {
		int duracaoTotalPalestras = getDuracaoTotalPalestras(palestrasList);
		int totalDias = duracaoTotalPalestras / ConferenciaConfig.TOTAL_MIN_DIA;
		palestrasList.sort(new PalestraComparator());
		List<List<Palestra>> listAM = new ArrayList<List<Palestra>>();
		List<List<Palestra>> listPM = new ArrayList<List<Palestra>>();
		PalestraPeriodoService.alocarPalestras(listAM,listPM,palestrasList, totalDias);
		return CalendarioPalestraService.processarCalendarioPalestras(listAM, listPM);
	}

	private int getDuracaoTotalPalestras(List<Palestra> palestras) {
		int total = 0;
		for (Palestra palestra : palestras) {
			total += palestra.getDuracao();
		}
		return total;
	}

	private List<Palestra> gerarPalestras(List<String> palestrasList) throws Exception {
		List<Palestra> validPalestrasList = new ArrayList<Palestra>();
		for (String palestraStr : palestrasList)
			validPalestrasList.add(PalestraValidator.ProcessarValidar(palestraStr));
		return validPalestrasList;
	}

	private String getDirNameFile() {
		return System.getProperty("user.dir") + 
				System.getProperty("file.separator") + 
				ConferenciaConfig.FILE_NAME;
	}
}
