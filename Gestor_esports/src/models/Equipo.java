package models;

import javax.naming.directory.InvalidAttributeValueException;
import java.util.HashSet;
import java.util.Set;

public class Equipo {
    protected final static Set<Equipo> equipos = new HashSet<>();

    private String nombre;
    protected final Set<Jugador> jugadores = new HashSet<>();
    private boolean estaOcupado;

    public Equipo(String nombre) throws InvalidAttributeValueException {
        setNombre(nombre);
        this.estaOcupado = false;
        equipos.add(this);
    }

    //getters
    public String getNombre() {return nombre;}
    public boolean estaOcupado() {return estaOcupado;}
    public static  int getSizeEquipos() {return equipos.size();}

    public void setNombre(String nombre)
        throws InvalidAttributeValueException{
        nombre = nombre.strip();
        if (nombre.length() >15 || nombre.contains(" "))
        {
            throw new InvalidAttributeValueException();
        }
        this.nombre = nombre;
    }

    public void setEstaOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
    }

    public void aniadir_jugador(Jugador jugador)
    throws InvalidAttributeValueException{
        if(jugador == null || !jugadores.add(jugador)){
            throw new InvalidAttributeValueException();
        }else{
            jugador.setEquipo(this);
        }
    }

    public static Equipo buscarEquipo(String nombre){
        if(nombre == null || nombre.isEmpty()){
            return null;
        }
        nombre = nombre.strip();
        for (Equipo equipo: equipos){
            if(equipo.getNombre().equalsIgnoreCase(nombre)){
                return equipo;
            }
        }
        return null;
    }

    public Set<Jugador> getJugadores() {
        return this.jugadores;
    }

    public void eliminar_jugador(String nombreJugador){
        if(nombreJugador == null || nombreJugador.isEmpty()){
            throw new IllegalArgumentException();
        }
        nombreJugador = nombreJugador.strip();
        for (Jugador jugador: jugadores){
            if(jugador.getNombre().equalsIgnoreCase(nombreJugador)){
                jugadores.remove(jugador);
                jugador.setEquipo(null);
                return;
            }
        }
    }

    public void transferir_jugador(String nombreJugador, String nombreEquipo){
        if(nombreJugador == null || nombreJugador.isEmpty() || nombreEquipo == null || nombreEquipo.isEmpty()){
            throw new IllegalArgumentException();
        }
        nombreJugador = nombreJugador.strip();
        nombreEquipo = nombreEquipo.strip();
        Equipo equipoDestino = buscarEquipo(nombreEquipo);
        if(equipoDestino == null){
            throw new IllegalArgumentException();
        }
        for (Jugador jugador: jugadores){
            if(jugador.getNombre().equalsIgnoreCase(nombreJugador)){
                eliminar_jugador(nombreJugador);
                try {
                    equipoDestino.aniadir_jugador(jugador);
                    jugador.setEquipo(equipoDestino);
                } catch (InvalidAttributeValueException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
    }

}
