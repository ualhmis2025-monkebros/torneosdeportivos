package org.ualhmis.torneos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Instalacion {

    private String nombre;
    private String tipo;
    private List<Horario> horariosOcupados;

    public Instalacion(String nombre, String tipo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la instalación no puede estar vacío.");
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de instalación no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
        this.tipo = tipo;
        this.horariosOcupados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Horario> getHorariosOcupados() {
        return new ArrayList<>(horariosOcupados);
    }

    public void ocuparHorario(Horario horario) {
        if (horario == null) {
            throw new IllegalArgumentException("El horario no puede ser nulo.");
        }
        if (!estaDisponible(horario)) {
            throw new IllegalStateException("La instalación no está disponible en ese horario.");
        }
        horariosOcupados.add(horario);
    }

    public boolean estaDisponible(Horario horario) {
        for (Horario ocupado : horariosOcupados) {
            if (ocupado.solapaCon(horario)) {
                return false;
            }
        }
        return true;
    }

    public boolean esAdecuadaPara(String deporte) {
        if (deporte == null || deporte.isBlank()) {
            throw new IllegalArgumentException("El deporte no puede ser nulo o vacío.");
        }

        switch (tipo.toLowerCase()) {
            case "campo":
                return deporte.equalsIgnoreCase("Fútbol");
            case "pabellón":
                return deporte.equalsIgnoreCase("Baloncesto");
            case "pista":
                return deporte.equalsIgnoreCase("Voleibol");
            default:
                return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Instalacion)) return false;
        Instalacion other = (Instalacion) obj;
        return Objects.equals(nombre, other.nombre);
    }

}