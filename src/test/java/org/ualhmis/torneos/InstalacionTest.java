package org.ualhmis.torneos;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstalacionTest {

    @Test
    void testCrearInstalacionNombreNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Instalacion(null, "campo"));
        assertEquals("El nombre de la instalación no puede estar vacío.", exception.getMessage());
    }

    @Test
    void testCrearInstalacionNombreVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Instalacion("  ", "campo"));
        assertEquals("El nombre de la instalación no puede estar vacío.", exception.getMessage());
    }

    @Test
    void testCrearInstalacionTipoNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Instalacion("Campo 1", null));
        assertEquals("El tipo de instalación no puede ser nulo o vacío.", exception.getMessage());
    }

    @Test
    void testCrearInstalacionTipoVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Instalacion("Campo 1", " "));
        assertEquals("El tipo de instalación no puede ser nulo o vacío.", exception.getMessage());
    }

    @Test
    void testEstaDisponibleSinHorarios() {
        Instalacion instalacion = new Instalacion("Campo 1", "campo");
        Horario horario = new Horario(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        assertTrue(instalacion.estaDisponible(horario));
    }

    @Test
    void testOcuparHorarioDisponible() {
        Instalacion instalacion = new Instalacion("Campo 1", "campo");
        Horario horario = new Horario(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        instalacion.ocuparHorario(horario);
        assertFalse(instalacion.estaDisponible(horario));
    }
 
    @Test
    void testGetters() {
        Instalacion instalacion = new Instalacion("Campo 1", "campo");

        // Comprobar nombre y tipo
        assertEquals("Campo 1", instalacion.getNombre());
        assertEquals("campo", instalacion.getTipo());

        // Comprobar lista inicial de horarios vacía
        List<Horario> horarios = instalacion.getHorariosOcupados();
        assertNotNull(horarios);
        assertTrue(horarios.isEmpty());

        // Añadir horario y comprobar
        Horario horario = new Horario(LocalDateTime.of(2025, 4, 20, 10, 0),
                LocalDateTime.of(2025, 4, 20, 12, 0));
        instalacion.ocuparHorario(horario);

        List<Horario> horariosActualizados = instalacion.getHorariosOcupados();
        assertEquals(1, horariosActualizados.size());
        assertTrue(horariosActualizados.contains(horario));

        horariosActualizados.clear();
        assertEquals(1, instalacion.getHorariosOcupados().size());
    }

    @Test
    void testOcuparHorarioSolapado() {
        Instalacion instalacion = new Instalacion("Campo 1", "campo");
        Horario horario1 = new Horario(LocalDateTime.of(2025, 4, 20, 10, 0),
                LocalDateTime.of(2025, 4, 20, 12, 0));
        instalacion.ocuparHorario(horario1);

        Horario horarioSolapado = new Horario(LocalDateTime.of(2025, 4, 20, 11, 0),
                LocalDateTime.of(2025, 4, 20, 13, 0));

        Exception exception = assertThrows(IllegalStateException.class, () -> instalacion.ocuparHorario(horarioSolapado));
        assertEquals("La instalación no está disponible en ese horario.", exception.getMessage());
    }

    @Test
    void testOcuparHorarioNulo() {
        Instalacion instalacion = new Instalacion("Campo 1", "campo");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> instalacion.ocuparHorario(null));
        assertEquals("El horario no puede ser nulo.", exception.getMessage());
    }

    @Test
    void testEsAdecuadaPara() {
        Instalacion campo = new Instalacion("Campo 1", "campo");
        assertTrue(campo.esAdecuadaPara("Fútbol"));
        assertFalse(campo.esAdecuadaPara("Baloncesto"));

        Instalacion pabellon = new Instalacion("Pabellón A", "pabellón");
        assertTrue(pabellon.esAdecuadaPara("Baloncesto"));
        assertFalse(pabellon.esAdecuadaPara("Fútbol"));

        Instalacion pista = new Instalacion("Pista 1", "pista");
        assertTrue(pista.esAdecuadaPara("Voleibol"));
        assertFalse(pista.esAdecuadaPara("Fútbol"));
    }

    @Test
    void testEsAdecuadaParaDeporteNulo() {
        Instalacion campo = new Instalacion("Campo 1", "campo");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> campo.esAdecuadaPara(null));
        assertEquals("El deporte no puede ser nulo o vacío.", exception.getMessage());
    }

    @Test
    void testEsAdecuadaParaDeporteVacio() {
        Instalacion campo = new Instalacion("Campo 1", "campo");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> campo.esAdecuadaPara("  "));
        assertEquals("El deporte no puede ser nulo o vacío.", exception.getMessage());
    }

    @Test
    void testEsAdecuadaParaTipoNoContemplado() {
        Instalacion gimnasio = new Instalacion("Gimnasio 1", "gimnasio");
        assertFalse(gimnasio.esAdecuadaPara("Fútbol"));
        assertFalse(gimnasio.esAdecuadaPara("Baloncesto"));
        assertFalse(gimnasio.esAdecuadaPara("Voleibol"));
        assertFalse(gimnasio.esAdecuadaPara("Tenis"));
    }
    
    @Test
    void testEquals() {
        Instalacion instalacion1 = new Instalacion("Campo 1", "campo");
        assertTrue(instalacion1.equals(instalacion1));
        String otroObjeto = "Campo 1";
        assertFalse(instalacion1.equals(otroObjeto));

        assertFalse(instalacion1.equals(null));

        Instalacion instalacion2 = new Instalacion("Campo 1", "pabellón");
        assertTrue(instalacion1.equals(instalacion2)); 

        Instalacion instalacion3 = new Instalacion("Campo 2", "campo");
        assertFalse(instalacion1.equals(instalacion3));
    }
}
