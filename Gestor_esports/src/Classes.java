import java.util.ArrayList;
import java.util.Date;
import java.util.ArrayList;

class Torneo {
    int idTorneo;
    String nombre;
    Date fechaInicio;
    Date fechaFin;
    ArrayList<Partida> partidas;
    ArrayList<Equipo> equipos;
}

class Partida {
    int idPartida;
    Date fecha;
    String resultado;
    Juego juego;
    Equipo equipo1;
    Equipo equipo2;
    Arbitro arbitro;
}

class Equipo {
    int idEquipo;
    String nombre;
    ArrayList<Jugador> jugadores;
}

class Jugador {
    int idJugador;
    String nombre;
    String alias;
    int ranking;
}

class Juego {
    int idJuego;
    String nombre;
    Categoria categoria;
}

class Categoria {
    int idCategoria;
    String nombre;
}

class Arbitro {
    int idArbitro;
    String nombre;
    int experiencia;
}
