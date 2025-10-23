package com.tienda.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FuncionApp {

    public static String getFechaString(LocalDate fecha) {
        if (fecha == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
}
