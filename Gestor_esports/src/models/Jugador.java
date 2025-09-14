package models;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Jugador {
    int idJugador;
    Set<Integer> ids = new HashSet<>();

    String nombre;
    String alias;
    int ranking;

    public void setIdJugador() {
        Random rand = new Random();
        int idJugador = rand.nextInt(0, 300);
        if(ids.add(idJugador) == true){
            this.idJugador = idJugador;
        }
    }
}
