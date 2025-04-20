package org.ualhmis.torneos;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sede {

    private String nombre;
    private List<Instalacion> instalaciones;

    public Sede(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la sede no puede estar vacío.");
        }
        this.nombre = nombre;
        this.instalaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Instalacion> getInstalaciones() {
        return new ArrayList<>(instalaciones);
    }

    public void agregarInstalacion(Instalacion instalacion) {
        if (instalacion == null) {
            throw new IllegalArgumentException("La instalación no puede ser nula.");
        }
        if (instalaciones.contains(instalacion)) {
            throw new IllegalArgumentException("La instalación ya está registrada en la sede.");
        }
        instalaciones.add(instalacion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Sede)) return false;
        Sede other = (Sede) obj;
        return Objects.equals(nombre, other.nombre);
    }
}
