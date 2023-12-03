package com.example.sintegracebusca.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class DateUtil {

    public static List<String> listarMeses() {
        Month month = YearMonth.now().getMonth();
        ArrayList<String> months = new ArrayList<>();
//        months.add(month.getDisplayName(TextStyle.FULL, new Locale("pt", "BR")));
        months.add(month.name());
        int i = 1;
        while (i < 12) {
            months.add(
//                month.minus(i).getDisplayName(TextStyle.FULL, new Locale("pt", "BR"))
//            );
                month.minus(i).name());

            i++;
        }
        return months;
    }

    public static LocalDate getPrimeiroDiaMes(String mes) {
        LocalDate primeiroDiaPesquisa = null;
        Month month = null;
        if (nonNull(mes)) {
            month = Month.valueOf(Month.class, mes.toUpperCase());
            primeiroDiaPesquisa = LocalDate.of(Year.now().getValue(), month, 1);
        } else {
            primeiroDiaPesquisa = LocalDate.now().minusDays(3);
        }
        return primeiroDiaPesquisa;
    }

    public static LocalDate getPrimeiroDiaMes(String mes, LocalDate defaultStart) {
        LocalDate primeiroDiaPesquisa = null;
        Month month = null;
        if (nonNull(mes)) {
            month = Month.valueOf(Month.class, mes.toUpperCase());
            primeiroDiaPesquisa = LocalDate.of(Year.now().getValue(), month, 1);
        } else {
            primeiroDiaPesquisa = defaultStart;
        }
        return primeiroDiaPesquisa;
    }

    public static LocalDate getUltimoDiaMes(String mes) {
        LocalDate ultimoDiaPesquisa = null;
        Month month = null;
        if (nonNull(mes)) {
            month = Month.valueOf(Month.class, mes.toUpperCase());
            ultimoDiaPesquisa = LocalDate.of(Year.now().getValue(), month, month.length(Year.isLeap(YearMonth.now().getYear())));
        } else {
            ultimoDiaPesquisa = LocalDate.now().plusDays(45);
        }
        return ultimoDiaPesquisa;
    }
}
