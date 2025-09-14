package models;
import java.util.ArrayList;
import java.util.Date;

public class Torneo {
        int idTorneo;
        String nombre;
        Date fechaInicio;
        Date fechaFin;
        ArrayList<Partida> partidas;
        ArrayList<Equipo> equipos;

}
