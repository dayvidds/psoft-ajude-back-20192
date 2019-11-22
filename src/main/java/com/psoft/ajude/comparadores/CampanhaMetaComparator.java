package com.psoft.ajude.comparadores;

import com.psoft.ajude.entidades.Campanha;

import java.util.Comparator;

public class CampanhaMetaComparator implements Comparator<Campanha> {

    @Override
    public int compare(Campanha o1, Campanha o2) {
        return Double.compare(o1.getQuantiaFaltante(), o2.getQuantiaFaltante());
    }

}
