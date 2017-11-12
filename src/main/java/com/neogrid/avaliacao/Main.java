package com.neogrid.avaliacao;

import java.util.List;

import com.neogrid.avaliacao.model.Conferencia;
import com.neogrid.avaliacao.model.Palestra;
import com.neogrid.avaliacao.service.ConferenciaService;

public class Main {

	public static void main(String[] args) {
		ConferenciaService service = new ConferenciaService();
		try {
			List<Conferencia> conferenciasList = service.gerarConferencia();
			for (Conferencia conferencia : conferenciasList) {
				System.out.println(conferencia);
				for (Palestra palestra : conferencia.getPalestrasList()) {
					System.out.println(palestra);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
