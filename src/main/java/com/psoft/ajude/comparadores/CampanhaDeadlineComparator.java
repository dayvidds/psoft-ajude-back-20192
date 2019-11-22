package com.psoft.ajude.comparadores;

import com.psoft.ajude.entidades.Campanha;

import java.util.Comparator;

public class CampanhaDeadlineComparator implements Comparator<Campanha> {
    @Override
    public int compare(Campanha o1, Campanha o2) {
        return o1.getDeadline().compareTo(o2.getDeadline());
    }
}
