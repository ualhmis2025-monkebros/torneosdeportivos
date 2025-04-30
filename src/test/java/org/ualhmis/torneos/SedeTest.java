package org.ualhmis.torneos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SedeTest {

    private Sede sede;

    @BeforeEach
    void setUp() {
        sede = new Sede("Sede Central");
    }

    @ParameterizedTest
    @ValueSource(strings = {"NULL", "  "})
    void testCrearSede(String nombre) {
        String realNombre = "NULL".equals(nombre) ? null : nombre;
      
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Sede(realNombre));
        assertEquals("El nombre de la sede no puede estar vacío.", exception.getMessage());
    }

    @Test
    void testAgregarInstalacionesInvalidas() {
        Instalacion instalacion = new Instalacion("Campo 1", "campo");

        sede.agregarInstalacion(instalacion);
      
        Exception exceptionNula = assertThrows(IllegalArgumentException.class, () -> sede.agregarInstalacion(null));
        Exception exceptionDuplicada = assertThrows(IllegalArgumentException.class, () -> sede.agregarInstalacion(instalacion));
        assertEquals("La instalación no puede ser nula.", exceptionNula.getMessage());
        assertEquals("La instalación ya está registrada en la sede.", exceptionDuplicada.getMessage());
    }

    @Test
    void testAgregarInstalacionesYObtenerLista() {
        Instalacion instalacion1 = new Instalacion("Campo 1", "campo");
        Instalacion instalacion2 = new Instalacion("Pabellón A", "pabellón");

        sede.agregarInstalacion(instalacion1);
        sede.agregarInstalacion(instalacion2);
        List<Instalacion> instalaciones = sede.getInstalaciones();

        assertNotNull(instalaciones);
        assertEquals(2, instalaciones.size());
        assertTrue(instalaciones.contains(instalacion1));
        assertTrue(instalaciones.contains(instalacion2));
        instalaciones.clear();
        assertEquals(2, sede.getInstalaciones().size());
    }

    @Test
    void testEquals() {
        Sede sede1 = new Sede("Sede Central");
        Sede sede2 = new Sede("Sede Central");
        Sede sede3 = new Sede("Otra Sede");
      
        assertTrue(sede1.equals(sede1)); 
        assertFalse(sede1.equals("Sede Central"));
        assertFalse(sede1.equals(null));
        assertTrue(sede1.equals(sede2));
        assertFalse(sede1.equals(sede3));
    }

    @Test
    void testGetters() {
        assertEquals("Sede Central", sede.getNombre());
    }
}

