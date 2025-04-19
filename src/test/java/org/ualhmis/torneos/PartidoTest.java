package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

// Registro de equipos en torneos con validación de categoría y modalidad

class PartidoTest {

	Entrenador entrenador1;
	Entrenador entrenador2;
	Equipo equipo1;
	Equipo equipo2;
	Partido partido;

	@BeforeEach
	void setUp() {
		entrenador1 = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
		entrenador2 = new Entrenador("Ana", "Femenino", LocalDate.of(1985, 6, 20));
		equipo1 = new Equipo("Tigres", "Juvenil", "Masculino", entrenador1);
		equipo2 = new Equipo("Leones", "Juvenil", "Masculino", entrenador2);
		partido = new Partido(equipo1, equipo2);
	}

	@Test
	void testRegistrarResultado() {
		partido.registrarResultado(2, 1);

		assertEquals(2, partido.getGolesEquipo1());
		assertEquals(1, partido.getGolesEquipo2());
	}

	@Test
	void testGetter() {
		assertEquals(equipo1, partido.getEquipo1());
		assertEquals(equipo2, partido.getEquipo2());
	}

	@Test
	void testSetter() {
		// Nuevos valores
		Entrenador nuevoEntrenador1 = new Entrenador("Luis", "Masculino", LocalDate.of(1990, 1, 1));
		Entrenador nuevoEntrenador2 = new Entrenador("Marta", "Femenino", LocalDate.of(1992, 4, 15));
		Equipo nuevoEquipo1 = new Equipo("Águilas", "Juvenil", "Masculino", nuevoEntrenador1);
		Equipo nuevoEquipo2 = new Equipo("Pumas", "Juvenil", "Masculino", nuevoEntrenador2);

		partido.setEquipo1(nuevoEquipo1);
		partido.setEquipo2(nuevoEquipo2);
		partido.setGolesEquipo1(3);
		partido.setGolesEquipo2(3);

		assertEquals(nuevoEquipo1, partido.getEquipo1());
		assertEquals(nuevoEquipo2, partido.getEquipo2());
		assertEquals(3, partido.getGolesEquipo1());
		assertEquals(3, partido.getGolesEquipo2());
	}
}