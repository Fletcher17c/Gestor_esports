import models.*;

import javax.naming.directory.InvalidAttributeValueException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            // Create categories
            Categoria fps = Categoria.crearCategoria("FPS");
            Categoria moba = Categoria.crearCategoria("MOBA");

            // Create games
            Juego csgo = new Juego("CS:GO", fps.getNombre());
            Juego lol = new Juego("LoL", moba.getNombre());

            // Create teams
            Equipo equipo1 = new Equipo("Dragones");
            Equipo equipo2 = new Equipo("Titanes");
            Equipo equipo3 = new Equipo("Guerreros");
            Equipo equipo4 = new Equipo("Leones");
            Equipo equipo5 = new Equipo("Fenix");

            // Create players and assign to teams
            Jugador jugador1 = new Jugador("Silvio", "silviogamer");
            Jugador jugador2 = new Jugador("Gabriel", "Gibribang");
            Jugador jugador3 = new Jugador("Luis", "luix");
            Jugador jugador4 = new Jugador("Maria", "mary");
            Jugador jugador5 = new Jugador("Ana", "anita");
            Jugador jugador6 = new Jugador("Carlos", "carlitos");
            Jugador jugador7 = new Jugador("Sofia", "sofi");
            Jugador jugador8 = new Jugador("Diego", "dieguito");
            Jugador jugador9 = new Jugador("Elena", "elenita");
            Jugador jugador10 = new Jugador("Javier", "javi");

            equipo1.aniadir_jugador(jugador1);
            equipo1.aniadir_jugador(jugador2);
            equipo2.aniadir_jugador(jugador3);
            equipo2.aniadir_jugador(jugador4);
            equipo3.aniadir_jugador(jugador5);
            equipo3.aniadir_jugador(jugador6);
            equipo4.aniadir_jugador(jugador7);
            equipo4.aniadir_jugador(jugador8);
            equipo5.aniadir_jugador(jugador9);
            equipo5.aniadir_jugador(jugador10);

            // Create referee
            Arbitro arbitro1 = new Arbitro("Pedro");

            // Create a tournament
            Torneo torneo = new Torneo("TorneoVerano", "01-07-2024", "10-07-2024");

            // Add teams to tournament
            torneo.aniadirEquipo(equipo1.getNombre());
            torneo.aniadirEquipo(equipo2.getNombre());

            // Simulate several matches
            for (int i = 1; i <= 3; i++) {
                Partida partida = new Partida("0" + (i+1) + "-07-2024");
                partida.asociarJuego(csgo.getNombre(), csgo.getCategoria().getNombre());
                partida.setArbitro(arbitro1);
                torneo.aniadirPartida(Partida.buscarPartida(partida.getIdPartida()));

                System.out.println("Match " + i + ":");
                System.out.println("  Referee: " + partida.getArbitro().getNombre());
                System.out.println("  Winner: " + partida.getResultado());

                // Print player rankings after match
                for (Equipo eq : partida.getEquipos()) {
                    if (eq != null) {
                        for (Jugador j : eq.getJugadores()) {
                            System.out.println("    Player: " + j.getNombre() + ", Ranking: " + j.getRanking());
                        }
                    }
                }


                arbitro1.arbitrar_partida(partida.getIdPartida());
                System.out.println("  Referee " + arbitro1.getNombre() + " experience: " + arbitro1.getExperiencia());
                System.out.println();
            }

            // Delete tournament (removes matches, not teams or players)
            torneo.eliminarTorneo();
            System.out.println("Tournament deleted. Remaining teams: " + Equipo.getSizeEquipos());

        } catch (InvalidAttributeValueException e) {
            System.out.println("Invalid attribute: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Date parsing error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}