package org.ualhmis.torneos;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HorarioTest {

    @Test
    void testCrearHorarioValido() {
        LocalDateTime inicio = LocalDateTime.of(2024, 4, 19, 10, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 4, 19, 12, 0);
        Horario horario = new Horario(inicio, fin);

        assertEquals(inicio, horario.getInicio());
        assertEquals(fin, horario.getFin());
    }

    @ParameterizedTest
    @CsvSource({
        "NULL,2024-04-19T12:00:00",
        "2024-04-19T10:00:00,NULL",
        "2024-04-19T13:00:00,2024-04-19T12:00:00"
    })
    void testCrearHorarioInvalido(String inicioStr, String finStr) {
        LocalDateTime inicio = "NULL".equals(inicioStr) ? null : LocalDateTime.parse(inicioStr);
        LocalDateTime fin = "NULL".equals(finStr) ? null : LocalDateTime.parse(finStr);

        assertThrows(IllegalArgumentException.class, () -> new Horario(inicio, fin));
    }

    @ParameterizedTest
    @CsvSource({
        "2024-04-19T10:00,2024-04-19T12:00,2024-04-19T11:00,2024-04-19T13:00,true",
        "2024-04-19T08:00,2024-04-19T10:00,2024-04-19T10:00,2024-04-19T12:00,false"
    })
    void testSolapaCon(String inicio1Str, String fin1Str, String inicio2Str, String fin2Str, boolean esperado) {
        Horario h1 = new Horario(LocalDateTime.parse(inicio1Str), LocalDateTime.parse(fin1Str));
        Horario h2 = new Horario(LocalDateTime.parse(inicio2Str), LocalDateTime.parse(fin2Str));

        assertEquals(esperado, h1.solapaCon(h2));
    }

    @ParameterizedTest
    @CsvSource({
        "2024-04-19T12:00,2024-04-19T14:00,2024-04-19T08:00,2024-04-19T10:00,false",
        "2024-04-19T08:00,2024-04-19T10:00,2024-04-19T09:00,2024-04-19T09:30,true"
    })
    void testSolapaConNoSolapan(String inicio1Str, String fin1Str, String inicio2Str, String fin2Str, boolean esperado) {
        Horario h1 = new Horario(LocalDateTime.parse(inicio1Str), LocalDateTime.parse(fin1Str));
        Horario h2 = new Horario(LocalDateTime.parse(inicio2Str), LocalDateTime.parse(fin2Str));
        
        assertEquals(esperado, h1.solapaCon(h2));
    }

    @Test
    void testEquals() {
        Horario h1 = new Horario(LocalDateTime.of(2024, 4, 19, 9, 0), LocalDateTime.of(2024, 4, 19, 11, 0));
        Horario h2 = new Horario(LocalDateTime.of(2024, 4, 19, 9, 0), LocalDateTime.of(2024, 4, 19, 11, 0));
        Horario distintoInicio = new Horario(LocalDateTime.of(2024, 4, 19, 10, 0), LocalDateTime.of(2024, 4, 19, 11, 0));
        Horario distintoFin = new Horario(LocalDateTime.of(2024, 4, 19, 9, 0), LocalDateTime.of(2024, 4, 19, 12, 0));
        Horario otro = new Horario(LocalDateTime.of(2024, 4, 19, 10, 0), LocalDateTime.of(2024, 4, 19, 12, 0));

        assertEquals(h1, h2);
        assertEquals(h1, h1);
        assertNotEquals(h1, distintoInicio);
        assertNotEquals(h1, distintoFin);
        assertNotEquals(h1, otro);
        assertNotEquals(h1, null);
        assertNotEquals(h1, "otro objeto");
    }
}