package com.neogrid.avaliacao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neogrid.avaliacao.model.Conferencia;
import com.neogrid.avaliacao.service.ConferenciaService;


@RestController
@RequestMapping("/conferencia")
public class ConferenciaResource {

	@Autowired
	private ConferenciaService conferenciaService;
	
	@GetMapping
	public List<Conferencia> processarConferencia() throws Exception {
		return conferenciaService.gerarConferencia();
	}
}
