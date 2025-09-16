package models;

import javax.naming.directory.InvalidAttributeValueException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Torneo {
    private static int nextId = 1;
    protected static Map<Integer, Torneo> torneos = new HashMap<>();
    private int idTorneo;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private final ArrayList<Partida> partidas;
    private final ArrayList<Equipo> equipos;

    public Torneo(String nombre, String fechaInicio, String fechaFinStr) throws InvalidAttributeValueException, ParseException {
        this.idTorneo = nextId++;
        setNombre(nombre);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFinStr);
        this.partidas = new ArrayList<>();
        this.equipos = new ArrayList<>();
        torneos.put(this.idTorneo, this);
    }

    // Getters
    public int getIdTorneo() {
        return idTorneo;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    // Setters
    public void setNombre(String nombre) throws InvalidAttributeValueException {
        if (nombre == null || nombre.isEmpty() || nombre.chars().anyMatch(Character::isDigit)) {
            throw new InvalidAttributeValueException();
        }
        this.nombre = nombre.strip();
    }

    public void setFechaInicio(String fechaInicioStr) throws InvalidAttributeValueException, ParseException {
        if (fechaInicioStr == null || fechaInicioStr.isEmpty()) {
            throw new InvalidAttributeValueException();
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        this.fechaInicio = formato.parse(fechaInicioStr.strip());
    }

    public void setFechaFin(String fechaFinStr) throws InvalidAttributeValueException, ParseException {
        if (fechaFinStr == null || fechaFinStr.isEmpty()) {
            throw new InvalidAttributeValueException();
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaFinParsed = formato.parse(fechaFinStr.strip());
        if (this.fechaInicio != null && fechaFinParsed.before(this.fechaInicio)) {
            throw new InvalidAttributeValueException();
        }
        this.fechaFin = fechaFinParsed;
    }


    public void aniadirPartida(Partida partida) throws InvalidAttributeValueException {
        if (partida == null || partidas.contains(partida)) {
            throw new InvalidAttributeValueException();
        }
        partidas.add(partida);
    }

    public void aniadirEquipo(String nombreEquipo) throws InvalidAttributeValueException {
        Equipo equipo = Equipo.buscarEquipo(nombreEquipo);
        if (equipo == null || equipos.contains(equipo)) {
            throw new InvalidAttributeValueException();
        }
        equipos.add(equipo);
    }

    public void eliminarTorneo() {
        for (Partida partida : partidas) {
            Partida.partidas.remove(partida);
        }
        partidas.clear();
        torneos.remove(this.idTorneo);
    }

}