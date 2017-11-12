package com.neogrid.avaliacao.comparator;

import java.util.Comparator;

import com.neogrid.avaliacao.model.Palestra;

public class PalestraComparator implements Comparator<Palestra> {

	@Override
	public int compare(Palestra o1, Palestra o2) {
		if(o1.getDuracao() < o2.getDuracao()){
            return 1;
        } else {
            return -1;
        }
	}

}
