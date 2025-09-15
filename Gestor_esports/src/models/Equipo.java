package models;

import javax.naming.directory.InvalidAttributeValueException;
import java.util.HashSet;
import java.util.Set;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private final Set<Jugador> jugadores = new HashSet<>();

    public void aniadir_jugador(Jugador jugador)
    throws InvalidAttributeValueException{
        if(jugador == null || !jugadores.add(jugador)){
            throw new InvalidAttributeValueException();
        }else{
            jugador.setEquipo(this);
        }

    }
}
