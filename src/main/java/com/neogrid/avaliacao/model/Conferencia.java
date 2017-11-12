package com.neogrid.avaliacao.model;

import java.io.Serializable;
import java.util.List;

public class Conferencia implements Serializable {

	private static final long serialVersionUID = -7583566189282692050L;

	private int dia;
	private List<Palestra> palestrasList;
	
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public List<Palestra> getPalestrasList() {
		return palestrasList;
	}
	public void setPalestrasList(List<Palestra> palestrasList) {
		this.palestrasList = palestrasList;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dia;
		result = prime * result + ((palestrasList == null) ? 0 : palestrasList.hashCode());
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
		Conferencia other = (Conferencia) obj;
		if (dia != other.dia)
			return false;
		if (palestrasList == null) {
			if (other.palestrasList != null)
				return false;
		} else if (!palestrasList.equals(other.palestrasList))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Track "+this.getDia()+":";
	}
	
}
