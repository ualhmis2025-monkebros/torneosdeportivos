package org.ualhmis.torneos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SedeTest {

    @Test
    void TestGetters() {
        Sede sede = new Sede("Sede Central");
        assertEquals("Sede Central", sede.getNombre());
    }
    
    @Test
    void testCrearSedeNombreNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Sede(null));
        assertEquals("El nombre de la sede no puede estar vacío.", exception.getMessage());
    }

    @Test
    void testCrearSedeNombreVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Sede("  "));
        assertEquals("El nombre de la sede no puede estar vacío.", exception.getMessage());
    }

    @Test
    void testAgregarInstalacionNula() {
        Sede sede = new Sede("Sede Central");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sede.agregarInstalacion(null));
        assertEquals("La instalación no puede ser nula.", exception.getMessage());
    }

    @Test
    void testAgregarInstalacionDuplicada() {
        Sede sede = new Sede("Sede Central");
        Instalacion instalacion = new Instalacion("Campo 1", "campo");

        sede.agregarInstalacion(instalacion);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> sede.agregarInstalacion(instalacion));
        assertEquals("La instalación ya está registrada en la sede.", exception.getMessage());
    }

    @Test
    void testAgregarInstalacionesYObtenerLista() {
        Sede sede = new Sede("Sede Central");

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
        assertTrue(sede1.equals(sede1)); 

        String otroObjeto = "Sede Central";
        assertFalse(sede1.equals(otroObjeto));

        assertFalse(sede1.equals(null));

        Sede sede2 = new Sede("Sede Central");
        assertTrue(sede1.equals(sede2));

        Sede sede3 = new Sede("Otra Sede");
        assertFalse(sede1.equals(sede3));
    }
}
