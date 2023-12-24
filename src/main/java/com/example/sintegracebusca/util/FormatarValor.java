package com.example.sintegracebusca.util;

import com.example.sintegracebusca.domain.Compra;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatarValor {
    public static void formatar(Compra compra) {

        var bd = BigDecimal.valueOf(compra.getValor())
            .setScale(2, RoundingMode.HALF_UP);
        double newInput = bd.doubleValue();
        compra.setValor(newInput);
    }

    public static double formatar(String valor) {

        var valorAjustado = valor.replace(",", "");
        var bigDecimal = BigDecimal.valueOf(Double.valueOf(valorAjustado))
            .setScale(2, RoundingMode.HALF_UP);

        return bigDecimal.doubleValue();
    }
}
