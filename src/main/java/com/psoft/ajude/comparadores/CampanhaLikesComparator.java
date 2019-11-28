package com.psoft.ajude.comparadores;

import com.psoft.ajude.entidades.Campanha;

import java.util.Comparator;

public class CampanhaLikesComparator implements Comparator<Campanha> {
    @Override
    public int compare(Campanha o1, Campanha o2) {
        return o2.getLikesUsuarios().size() - o1.getLikesUsuarios().size();
    }
}
