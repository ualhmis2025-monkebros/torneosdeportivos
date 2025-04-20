package org.ualhmis.torneos;

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

    @Test
    void testCrearHorarioConInicioNulo() {
        LocalDateTime fin = LocalDateTime.of(2024, 4, 19, 12, 0);
        assertThrows(IllegalArgumentException.class, () -> new Horario(null, fin));
    }

    @Test
    void testCrearHorarioConFinNulo() {
        LocalDateTime inicio = LocalDateTime.of(2024, 4, 19, 10, 0);
        assertThrows(IllegalArgumentException.class, () -> new Horario(inicio, null));
    }

    @Test
    void testCrearHorarioConInicioDespuesDeFin() {
        LocalDateTime inicio = LocalDateTime.of(2024, 4, 19, 13, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 4, 19, 12, 0);
        assertThrows(IllegalArgumentException.class, () -> new Horario(inicio, fin));
    }

    @Test
    void testSolapaConTrue() {
        Horario h1 = new Horario(LocalDateTime.of(2024, 4, 19, 10, 0),
                                 LocalDateTime.of(2024, 4, 19, 12, 0));
        Horario h2 = new Horario(LocalDateTime.of(2024, 4, 19, 11, 0),
                                 LocalDateTime.of(2024, 4, 19, 13, 0));

        assertTrue(h1.solapaCon(h2));
    }

    @Test
    void testSolapaConFalse() {
        Horario h1 = new Horario(LocalDateTime.of(2024, 4, 19, 8, 0),
                                 LocalDateTime.of(2024, 4, 19, 10, 0));
        Horario h2 = new Horario(LocalDateTime.of(2024, 4, 19, 10, 0),
                                 LocalDateTime.of(2024, 4, 19, 12, 0));

        assertFalse(h1.solapaCon(h2));
    }

    @Test
    void testEqualsConIguales() {
        Horario h1 = new Horario(LocalDateTime.of(2024, 4, 19, 9, 0),
                                 LocalDateTime.of(2024, 4, 19, 11, 0));
        Horario h2 = new Horario(LocalDateTime.of(2024, 4, 19, 9, 0),
                                 LocalDateTime.of(2024, 4, 19, 11, 0));

        assertEquals(h1, h2);
        assertEquals(h1, h1);
    }

    @Test
    void testEqualsConDiferentes() {
        Horario h1 = new Horario(LocalDateTime.of(2024, 4, 19, 9, 0),
                                 LocalDateTime.of(2024, 4, 19, 11, 0));
        Horario h3 = new Horario(LocalDateTime.of(2024, 4, 19, 10, 0),
                                 LocalDateTime.of(2024, 4, 19, 12, 0));

        assertNotEquals(h1, h3);
    }

    @Test
    void testEqualsConNullYOtroTipo() {
        Horario h1 = new Horario(LocalDateTime.of(2024, 4, 19, 9, 0),
                                 LocalDateTime.of(2024, 4, 19, 11, 0));

        assertNotEquals(h1, null);
        assertNotEquals(h1, "otro objeto"); 
    }
}
