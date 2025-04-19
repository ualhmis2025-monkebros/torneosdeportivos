package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

// Restricciones en los equipos (jugadores de la misma categoría y modalidad)

class JugadorTest {

	// Test que comprueba que el jugador se asigne correctamente a su categoría
    @Test
    void testCategoriaPorEdad() {
        Jugador jugador1 = new Jugador("Carlos", "Masculino", LocalDate.of(2015, 5, 10));
        assertEquals("Infantil", jugador1.getCategoria());

        Jugador jugador2 = new Jugador("Luis", "Masculino", LocalDate.of(2011, 3, 15));
        assertEquals("Cadete", jugador2.getCategoria());

        Jugador jugador3 = new Jugador("Ana", "Femenino", LocalDate.of(2008, 8, 22));
        assertEquals("Juvenil", jugador3.getCategoria());

        Jugador jugador4 = new Jugador("Pedro", "Masculino", LocalDate.of(2005, 1, 30));
        assertEquals("Junior", jugador4.getCategoria());

        Jugador jugador5 = new Jugador("Marta", "Femenino", LocalDate.of(1998, 6, 5));
        assertEquals("Absoluta", jugador5.getCategoria());
    }

	// Instancia los valores en el test parametrizado "testCreacionJugadorInvalido"
    @ParameterizedTest
    @CsvSource({
        "'',Masculino,2010-01-01",      // nombre vacío
        "' ',Masculino,2010-01-01",     // nombre en blanco
        "null,Masculino,2010-01-01",    // nombre nulo -> fecha válida (se evalúa LocalDate.parse)

        "Juan,'',2010-01-01",           // género vacío
        "Juan,' ',2010-01-01",          // género en blanco
        "Juan,null,2010-01-01",         // género nulo -> fecha válida (se evalúa LocalDate.parse)

        "Juan,Masculino,''",           // fecha vacía
        "Juan,Masculino,' '",          // fecha en blanco
        "Juan,Masculino,null"          // fecha nula
    })
    // Test parametrizado que comprueba que el jugador no tenga ningún parámetro nulo
    void testCreacionJugadorInvalido(String nombreTexto, String generoTexto, String fechaTexto) {
        String nombre = "null".equals(nombreTexto) ? null : nombreTexto;
        String genero = "null".equals(generoTexto) ? null : generoTexto;
        LocalDate fecha = ("null".equals(fechaTexto) || fechaTexto.isBlank()) ? null : LocalDate.parse(fechaTexto);
        
        assertThrows(IllegalArgumentException.class, () -> new Jugador(nombre, genero, fecha));
    }
    
    @Test
    void testGetters() {
    	Jugador jugador = new Jugador("Carlos", "Masculino", LocalDate.of(2015, 5, 10));
    	
    	assertEquals("Carlos", jugador.getNombre());
    	assertEquals("Masculino", jugador.getGenero());
    	assertEquals("2015-05-10", jugador.getFechaNacimiento().toString());
    }
}
