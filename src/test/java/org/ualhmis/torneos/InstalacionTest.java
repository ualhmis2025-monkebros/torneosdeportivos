package org.ualhmis.torneos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstalacionTest {

    private Instalacion instalacion;

    @BeforeEach
    void setUp() {
        instalacion = new Instalacion("Campo 1", "campo");
    }

    @ParameterizedTest
    @CsvSource({
        "null,campo,El nombre de la instalación no puede estar vacío.",
        "'  ',campo,El nombre de la instalación no puede estar vacío.",
        "Campo 1,null,El tipo de instalación no puede ser nulo o vacío.",
        "Campo 1,' ',El tipo de instalación no puede ser nulo o vacío."
    })
    void testCrearInstalacionParametrosInvalidos(String nombre, String tipo, String mensajeEsperado) {
        String nombreReal = "null".equals(nombre) ? null : nombre;
        String tipoReal = "null".equals(tipo) ? null : tipo;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Instalacion(nombreReal, tipoReal));
        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    void testEstaDisponibleHorarios() {
        Horario horario = new Horario(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        Horario ocupado = new Horario(LocalDateTime.now().plusHours(1).plusMinutes(30), LocalDateTime.now().plusHours(2).plusMinutes(30));
        Horario libre = new Horario(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));

        assertTrue(instalacion.estaDisponible(horario));
        instalacion.ocuparHorario(horario);
        assertFalse(instalacion.estaDisponible(ocupado));
        assertTrue(instalacion.estaDisponible(libre));
        Exception exceptionSolape = assertThrows(IllegalStateException.class, () -> instalacion.ocuparHorario(ocupado));
        Exception exceptionNull = assertThrows(IllegalArgumentException.class, () -> instalacion.ocuparHorario(null));
        assertEquals("La instalación no está disponible en ese horario.", exceptionSolape.getMessage());
        assertEquals("El horario no puede ser nulo.", exceptionNull.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "campo,Fútbol,true",
        "campo,Baloncesto,false",
        "pabellón,Baloncesto,true",
        "pabellón,Fútbol,false",
        "pista,Voleibol,true",
        "pista,Fútbol,false",
        "gimnasio,Fútbol,false",
        "gimnasio,Baloncesto,false",
        "gimnasio,Voleibol,false",
        "gimnasio,Tenis,false"
    })
    void testEsAdecuadaPara(String tipoInstalacion, String deporte, boolean esperado) {
        Instalacion instalacion = new Instalacion("Instalación de prueba", tipoInstalacion);
        
        assertEquals(esperado, instalacion.esAdecuadaPara(deporte));
    }

    @ParameterizedTest
    @CsvSource({
        "null,El deporte no puede ser nulo o vacío.",
        "'  ',El deporte no puede ser nulo o vacío."
    })
    void testEsAdecuadaParaExcepciones(String deporte, String mensajeEsperado) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> instalacion.esAdecuadaPara("null".equals(deporte) ? null : deporte));
        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    void testEquals() {
        Instalacion instalacion2 = new Instalacion("Campo 1", "pabellón");
        Instalacion instalacion3 = new Instalacion("Campo 2", "campo");

        assertTrue(instalacion.equals(instalacion));
        assertFalse(instalacion.equals("Campo 1"));
        assertFalse(instalacion.equals(null));
        assertTrue(instalacion.equals(instalacion2));
        assertFalse(instalacion.equals(instalacion3));
    }

    @Test
    void testGetters() {
        List<Horario> horarios = instalacion.getHorariosOcupados();
        assertEquals("Campo 1", instalacion.getNombre());
        assertEquals("campo", instalacion.getTipo());
        assertNotNull(horarios);
        assertTrue(horarios.isEmpty());

        Horario horario = new Horario(LocalDateTime.of(2025, 4, 20, 10, 0), LocalDateTime.of(2025, 4, 20, 12, 0));
        instalacion.ocuparHorario(horario);
        
        List<Horario> horariosActualizados = instalacion.getHorariosOcupados();
        assertEquals(1, horariosActualizados.size());
        assertTrue(horariosActualizados.contains(horario));

        horariosActualizados.clear();
        assertEquals(1, instalacion.getHorariosOcupados().size());
    }
}
