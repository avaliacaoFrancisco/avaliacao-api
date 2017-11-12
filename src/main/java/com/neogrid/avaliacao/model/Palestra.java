package com.neogrid.avaliacao.model;

import java.io.Serializable;

public class Palestra implements Serializable {

	private static final long serialVersionUID = 7307623881454561805L;
	private String nome;
	private Integer duracao;
	private boolean agendada;
	private String horario;
	
	
	public Palestra(String nome, Integer duracao) {
		super();
		this.nome = nome;
		this.duracao = duracao;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public boolean isAgendada() {
		return agendada;
	}

	public void setAgendada(boolean agendada) {
		this.agendada = agendada;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (agendada ? 1231 : 1237);
		result = prime * result + ((duracao == null) ? 0 : duracao.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palestra other = (Palestra) obj;
		if (agendada != other.agendada)
			return false;
		if (duracao == null) {
			if (other.duracao != null)
				return false;
		} else if (!duracao.equals(other.duracao))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return horario  + nome +" "+ duracao+"min";
	}

	
	
	
	
	
	
	
}
