package com.psoft.ajude.util;

import java.text.Normalizer;

public class Util {
    public static String removerCaracteresDesnecessarios(String str) {
        return Normalizer
                .normalize(str, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()]", "");
    }

}
