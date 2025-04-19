package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GestorTorneosTest {

	// Test para ver que se crean los torneos correctamente en el gestor de torneos
    @Test
    void anadirTorneoAGestorTorneos() {
    	GestorTorneos gestorTorneos = new GestorTorneos();
    	
    	gestorTorneos.crearTorneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");
    	
    	assertEquals(1, gestorTorneos.getTorneos().size(), "El torneo debería haberse añadido");

        Torneo torneoCreado = gestorTorneos.getTorneos().get(0);
        assertEquals("Liga Juvenil", torneoCreado.getNombre());
        assertEquals("Fútbol", torneoCreado.getDeporte());
        assertEquals("Juvenil", torneoCreado.getCategoria());
        assertEquals("Masculino", torneoCreado.getModalidad());
        assertEquals("Liga", torneoCreado.getTipo());
    } 
}