package com.neogrid.avaliacao.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.neogrid.avaliacao.config.ConferenciaConfig;
import com.neogrid.avaliacao.exceptions.PalestraInvalidaExceptions;
import com.neogrid.avaliacao.model.Palestra;

public class PalestraValidator {

	private static final String NOME_PAL_N_CONT_NUM = ", nome da palestra não pode conter números";
	private static final String PALESTRA = ", Palestra: ";
	private static final String DUR_TER__MIN_LIGHTNING = ". Duração deve terminar com 'min' ou 'lightning' ";
	private static final String ARQ_PAL_INVALIDA = "Arquivo Possui uma Palestra Inválida ";
	private static final String DUR_INF_INV = "Duração Informada Inválida, ";
	private static final String LIGHTNING = "lightning";
	private static final String MIN = "min";

	public static Palestra ProcessarValidar(String palestraStr) throws Exception {

		String minStr = MIN;
		String lightningStr = LIGHTNING;
		int ultimoEspaco = palestraStr.lastIndexOf(" ");
		String nome = palestraStr.substring(0, ultimoEspaco);
		String duracaoStr = palestraStr.substring(ultimoEspaco + 1);

		validaNome(ultimoEspaco, nome);
		int duracao = validaDuracao(palestraStr, minStr, lightningStr, duracaoStr);

		return new Palestra(nome, duracao);
	}

	private static int validaDuracao(String palestraStr, String minStr, String lightningStr, String duracaoStr)
			throws PalestraInvalidaExceptions {

		if (!duracaoStr.endsWith(minStr) && !duracaoStr.endsWith(lightningStr))
			throw new PalestraInvalidaExceptions(DUR_INF_INV + palestraStr + DUR_TER__MIN_LIGHTNING);

		int duracao = 0;
		try {
			if (duracaoStr.endsWith(minStr))
				duracao = Integer.parseInt(duracaoStr.substring(0, duracaoStr.indexOf(minStr)));

			else if (duracaoStr.endsWith(lightningStr))
				duracao = ConferenciaConfig.LIGHTNING_MIN;

		} catch (NumberFormatException nfe) {
			throw new PalestraInvalidaExceptions(DUR_INF_INV + duracaoStr + PALESTRA + palestraStr);
		}
		return duracao;
	}

	private static void validaNome(int ultimoEspaco, String nome) throws PalestraInvalidaExceptions {
		if (ultimoEspaco == -1)
			throw new PalestraInvalidaExceptions(ARQ_PAL_INVALIDA);

		if (nome == null || "".equals(nome.trim()))
			throw new PalestraInvalidaExceptions(ARQ_PAL_INVALIDA);

		String REGEX = "\\d+";
		Matcher matcher = Pattern.compile(REGEX).matcher(nome);
		if (matcher.find()) {
			throw new PalestraInvalidaExceptions(ARQ_PAL_INVALIDA + nome + NOME_PAL_N_CONT_NUM);
		}
	}
}
