package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.ArrayList;

class TorneoTest {

	private Torneo torneo;
	private Entrenador entrenador;
	private Equipo equipo;
	
	
	// Instancia variables que se usan en todos los test
	@BeforeEach
	void setUp() {
		torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");
        entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10));
        equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
	}
	
	// Comprueba que el equipo se añade correctamente al torneo
    @Test
    void testRegistrarEquipoCorrectamente() {
        torneo.registrarEquipo(equipo);
        
        assertEquals(1, torneo.getEquipos().size());
    }

    // Comprueba que no se añada dos veces un mismo equipo
    @Test
    void testNoRegistrarEquipoDosVeces() {
        torneo.registrarEquipo(equipo);
        torneo.registrarEquipo(equipo);

        assertEquals(1, torneo.getEquipos().size(), "El equipo se ha registrado más de una vez");
    }

	// Instancia los valores en el test parametrizado "testNoRegistrarEquipoConCategoriaOModalidadIncorrectas"
    @ParameterizedTest
    @CsvSource({
        "Cadete,Masculino",   // Categoría distinta
        "Juvenil,Femenino",   // Modalidad distinta
        "Cadete,Femenino"     // Ambas distintas
    })
    
    // Test parametrizado que comprueba que el equipo añadido coincida con las características del torneo
    void testNoRegistrarEquipoConCategoriaOModalidadIncorrectas(String categoriaEquipo, String modalidadEquipo) {
        Equipo equipo = new Equipo("Hola", categoriaEquipo, modalidadEquipo, entrenador);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }
    
    // Test Getters
    @Test
    void testGetters() {
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

        assertEquals("Liga Juvenil", torneo.getNombre());
        assertEquals("Fútbol", torneo.getDeporte());
        assertEquals("Juvenil", torneo.getCategoria());
        assertEquals("Masculino", torneo.getModalidad());
        assertEquals("Liga", torneo.getTipo());
        assertNotNull(torneo.getEquipos());
        assertTrue(torneo.getEquipos().isEmpty(), "Al inicio no debe haber equipos registrados");
    }

    // Test Setters
    @Test
    void testSetters() {
        Torneo torneo = new Torneo("Nombre inicial", "Deporte inicial", "Inicial", "Inicial", "Inicial");
        Entrenador entrenador = new Entrenador("Ana", "Femenino", LocalDate.of(1990, 4, 12));
        Equipo equipo = new Equipo("Águilas", "Cadete", "Femenino", entrenador);
        ArrayList<Equipo> nuevaLista = new ArrayList<>();
        nuevaLista.add(equipo);
        
        torneo.setNombre("Liga Regional");
        torneo.setDeporte("Baloncesto");
        torneo.setCategoria("Cadete");
        torneo.setModalidad("Femenino");
        torneo.setTipo("Eliminatoria");
        torneo.setEquipos(nuevaLista);

        assertEquals("Liga Regional", torneo.getNombre());
        assertEquals("Baloncesto", torneo.getDeporte());
        assertEquals("Cadete", torneo.getCategoria());
        assertEquals("Femenino", torneo.getModalidad());
        assertEquals("Eliminatoria", torneo.getTipo());
        assertEquals(equipo, torneo.getEquipos().get(0));
    }
}


