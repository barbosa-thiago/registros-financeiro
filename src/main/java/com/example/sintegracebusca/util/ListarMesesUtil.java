package com.example.sintegracebusca.util;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListarMesesUtil {

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
}
