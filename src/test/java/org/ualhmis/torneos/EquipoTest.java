package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// Creación de jugadores y cálculo automático de categoría

class EquipoTest {

	private Entrenador entrenador;
	private Equipo equipo;

	@BeforeEach
	void setUp() {
		entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
		equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	}

	@ParameterizedTest
	@CsvSource({ "'',Infantil,Masculino,Entrenador Principal", // nombre vacío
			"' ',Infantil,Masculino,Entrenador Principal", // nombre en blanco
			"null,Infantil,Masculino,Entrenador Principal", // nombre nulo

			"Equipo A,null,Masculino,Entrenador Principal", // categoría nula

			"Equipo A,Infantil,null,Entrenador Principal", // modalidad nula

			"Equipo A,Infantil,Masculino,null" // entrenador nulo
	})
	// Test parametrizado que comprueba que el equipo no tenga ningún parámetro nulo
	void testCreacionEquipo(String nombreTexto, String categoriaTexto, String modalidadTexto, String entrenadorTexto) {
		String nombre = "null".equals(nombreTexto) ? null : nombreTexto;
		String categoria = "null".equals(categoriaTexto) ? null : categoriaTexto;
		String modalidad = "null".equals(modalidadTexto) ? null : modalidadTexto;
		Entrenador entrenadorAux = "null".equals(entrenadorTexto) ? null : entrenador;

		assertThrows(IllegalArgumentException.class, () -> new Equipo(nombre, categoria, modalidad, entrenadorAux));
	}

	@Test
	void testGetter() {
		assertEquals("Tigres", equipo.getNombre());
		assertEquals("Juvenil", equipo.getCategoria());
		assertEquals("Masculino", equipo.getModalidad());
		assertEquals(entrenador, equipo.getEntrenador());
	}

	@Test
	void testSetter() {
		// Cambiar los valores con los setters
		equipo.setNombre("Leones");
		equipo.setCategoria("Infantil");
		equipo.setModalidad("Femenino");

		Entrenador nuevoEntrenador = new Entrenador("Lucía", "Femenino", LocalDate.of(1990, 8, 15));
		equipo.setEntrenador(nuevoEntrenador);

		Entrenador segundoEntrenador = new Entrenador("Miguel", "Masculino", LocalDate.of(1985, 11, 25));
		equipo.setSegundoEntrenador(segundoEntrenador);

		List<Jugador> jugadores = List.of(new Jugador("Carla", "Femenino", LocalDate.of(2012, 9, 5)),
				new Jugador("Elena", "Femenino", LocalDate.of(2013, 4, 20)));
		equipo.setJugadores(jugadores);

		// Validar los valores actualizados
		assertEquals("Leones", equipo.getNombre());
		assertEquals("Infantil", equipo.getCategoria());
		assertEquals("Femenino", equipo.getModalidad());
		assertEquals(nuevoEntrenador, equipo.getEntrenador());
		assertEquals(segundoEntrenador, equipo.getSegundoEntrenador());
		assertEquals(jugadores, equipo.getJugadores());
	}

	@Test
	void testToString() {
		Entrenador entrenadorPrincipal = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
		Entrenador entrenadorSecundario = new Entrenador("Ana", "Femenino", LocalDate.of(1985, 6, 20));
		Jugador jugador1 = new Jugador("Luis", "Masculino", LocalDate.of(2008, 7, 15));
		Jugador jugador2 = new Jugador("Pedro", "Masculino", LocalDate.of(2009, 5, 20));

		Equipo equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenadorPrincipal);
		equipo.setSegundoEntrenador(entrenadorSecundario);
		equipo.setJugadores(List.of(jugador1, jugador2));

		String expectedToString = "Equipo [nombre=Tigres, categoria=Juvenil, modalidad=Masculino, entrenador="
				+ entrenadorPrincipal + ", segundoEntrenador=" + entrenadorSecundario + ", jugadores=[" + jugador1
				+ ", " + jugador2 + "]]";

		assertEquals(expectedToString, equipo.toString(), "El método toString no genera la representación esperada.");
	}

	@Test
	void testEquals() {
	    Equipo equipoIgual = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	    Equipo equipoDiferente = new Equipo("Leones", "Infantil", "Femenino", entrenador);
	    Equipo nNulo1 = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	    Equipo nNulo2 = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	    Equipo cNulo1 = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	    Equipo cNulo2 = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	    Equipo mNulo1 = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	    Equipo mNulo2 = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	    Equipo nombreD = new Equipo("Panteras", "Juvenil", "Masculino", entrenador);
	    Equipo modalidadD = new Equipo("Tigres", "Juvenil", "Femenino", entrenador);
	    nNulo1.setNombre(null);
	    nNulo2.setNombre(null);
	    cNulo1.setCategoria(null);
	    cNulo2.setCategoria(null);
	    mNulo1.setModalidad(null);
	    mNulo2.setModalidad(null);
	  
	    // Comparaciones básicas
	    assertTrue(equipo.equals(equipoIgual), "Debería ser igual si los atributos coinciden");
	    assertTrue(equipo.equals(equipo), "Un objeto debe ser igual a sí mismo");
	    assertFalse(equipo.equals(equipoDiferente), "Debería ser falso con atributos diferentes");
	    assertFalse(equipo.equals(null), "Comparar con null debe devolver false");
	    assertFalse(equipo.equals("No soy un equipo"), "Comparar con objeto de otra clase debe devolver false");

	    // === Comparaciones con nombre ===
	    assertTrue(nNulo1.equals(nNulo2), "Ambos con nombre null deberían ser iguales"); 
	    assertFalse(nNulo1.equals(equipo), "Uno con nombre null y otro no → false");
	    assertFalse(equipo.equals(nombreD), "Nombres distintos → false");

	    // === Comparaciones con categoría ===
	    assertTrue(cNulo1.equals(cNulo2), "Ambos con categoría null deberían ser iguales");
	    assertFalse(cNulo1.equals(equipo), "Uno con categoría null y otro no → false");

	    // === Comparaciones con modalidad ===
	    assertTrue(mNulo1.equals(mNulo2), "Ambos con modalidad null deberían ser iguales");
	    assertFalse(mNulo1.equals(equipo), "Uno con modalidad null y otro no → false");
	    assertFalse(equipo.equals(modalidadD), "Modalidades distintas → false");
	}

	@Test
	void testAgregarJugadorCorrectamente() {
		Jugador jugador = new Jugador("Luis", "Masculino", LocalDate.of(2008, 7, 15)); // 16 años en 2024-2025

		equipo.agregarJugador(jugador);
		assertEquals(1, equipo.getJugadores().size(), "El jugador debería haber sido agregado correctamente.");

		equipo.agregarJugador(jugador);
		assertEquals(1, equipo.getJugadores().size(), "El jugador no debería agregarse dos veces.");

		Jugador jugadorDiferenteCategoria = new Jugador("Pedro", "Masculino", LocalDate.of(2015, 5, 20)); // Infantil
		equipo.agregarJugador(jugadorDiferenteCategoria);

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
