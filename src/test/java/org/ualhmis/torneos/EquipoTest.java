package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

// Creación de jugadores y cálculo automático de categoría

class EquipoTest {

    @Test
    void testAgregarJugadorCorrectamente() {
        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Equipo equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);

        // Crear un jugador con categoría "Juvenil"
        Jugador jugador = new Jugador("Luis", "Masculino", LocalDate.of(2008, 7, 15)); // 16 años en 2024-2025

        // Categorías para depuración
        System.out.println("Categoría del equipo: " + equipo.getCategoria());
        System.out.println("Categoría del jugador: " + jugador.getCategoria());

        // Caso positivo: agregar jugador válido
        equipo.agregarJugador(jugador);
        assertEquals(1, equipo.getJugadores().size(), "El jugador debería haber sido agregado correctamente.");

        // Caso negativo 1: intentar agregar el mismo jugador nuevamente
        equipo.agregarJugador(jugador);
        assertEquals(1, equipo.getJugadores().size(), "El jugador no debería agregarse dos veces.");

        // Caso negativo 2: agregar un jugador de diferente categoría
        Jugador jugadorDiferenteCategoria = new Jugador("Pedro", "Masculino", LocalDate.of(2010, 5, 20)); // Infantil
        equipo.agregarJugador(jugadorDiferenteCategoria);

        // Verificar lista de jugadores
        System.out.println("Jugadores en el equipo después del intento: " + equipo.getJugadores());

        assertEquals(1, equipo.getJugadores().size(), "No se debe agregar un jugador de diferente categoría.");
    }

    @Test
    void testNoAgregarJugadorDeDiferenteCategoria() {
        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Equipo equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);

        Jugador jugador = new Jugador("Luis", "Masculino", LocalDate.of(2015, 5, 10)); // Infantil

        equipo.agregarJugador(jugador);

        assertEquals(0, equipo.getJugadores().size()); // No debe agregarse
    }

    @Test
    void testAsignarSegundoEntrenador() {
        Entrenador entrenador1 = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        Entrenador entrenador2 = new Entrenador("Ana", "Femenino", LocalDate.of(1985, 6, 20));

        Equipo equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador1);
        equipo.asignarSegundoEntrenador(entrenador2);

        assertNotNull(equipo.getSegundoEntrenador());
        assertEquals("Ana", equipo.getSegundoEntrenador().getNombre());
    }
}
