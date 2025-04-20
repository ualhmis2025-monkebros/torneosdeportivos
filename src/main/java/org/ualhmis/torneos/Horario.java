package org.ualhmis.torneos;

import java.time.LocalDateTime;

public class Horario {

    private LocalDateTime inicio;
    private LocalDateTime fin;

    public Horario(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null || inicio.isAfter(fin)) {
            throw new IllegalArgumentException("El horario es inválido.");
        }
        this.inicio = inicio;
        this.fin = fin;
    }

    public boolean solapaCon(Horario otro) {
        return inicio.isBefore(otro.fin) && fin.isAfter(otro.inicio);
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Horario)) return false;
        Horario horario = (Horario) o;
        return inicio.equals(horario.inicio) && fin.equals(horario.fin);
    }

}