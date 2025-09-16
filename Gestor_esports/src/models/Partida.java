package models;
import javax.naming.directory.InvalidAttributeValueException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Partida {
    private int idPartida;
    protected static Set<Integer> idsPartidas = new HashSet<>();
    protected static ArrayList<Partida> partidas = new ArrayList<>();

    Date fecha;
    String resultado;
    Juego juego;
    final int MAX_EQUIPOS = 2;
    Equipo[] equipos = new Equipo[MAX_EQUIPOS];
    Arbitro arbitro;

    public Partida(String fechastr) throws InvalidAttributeValueException, ParseException {
        setIdPartida();
        setFecha(fechastr);
        setEquipos();
        escojerResultado();
        partidas.add(this);
    }

    //getters
    public int getIdPartida() {return idPartida;}
    public Juego getJuego() {return juego;}
    public Arbitro getArbitro() {return arbitro;}
    public Equipo[] getEquipos() {return equipos;}
    public String getResultado() {return resultado;}

    public void setIdPartida() {
        Random rand = new Random();
        int nuevo_id;
        do {
            nuevo_id = rand.nextInt(0, 300);
        }while(!idsPartidas.add(nuevo_id));
        this.idPartida = nuevo_id;
    }

    public void setFecha(String fechastr) throws InvalidAttributeValueException, ParseException {
        if(fechastr == null || fechastr.isEmpty()){
            throw new InvalidAttributeValueException();
        }
        fechastr = fechastr.strip();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        this.fecha = formato.parse(fechastr);

    }

    // se llama desde arbitrarPartida para linkear la partida con el arbitro
    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }


    public void asociarJuego(String nombreJuego, String categoria) throws InvalidAttributeValueException {
        this.juego = new Juego(nombreJuego, categoria);
    }

    public void setEquipos() {
        List<Equipo> listaEquipos = new ArrayList<>(Equipo.equipos);

        if (listaEquipos.size() < 2 || listaEquipos.stream().filter(equipo -> !equipo.estaOcupado()).count() < 2) {
            throw new IllegalStateException();
        }
        Random rand = new Random();
        int index1;
        do {
            index1 = rand.nextInt(listaEquipos.size());
        } while (listaEquipos.get(index1).estaOcupado());

        int index2;
        do {
            index2 = rand.nextInt(listaEquipos.size());
        } while (index2 == index1 || listaEquipos.get(index2).estaOcupado());

        equipos[0] = listaEquipos.get(index1);
        equipos[1] = listaEquipos.get(index2);
        equipos[0].setEstaOcupado(true);
        equipos[1].setEstaOcupado(true);
    }

    public void escojerResultado(){
        Random random = new Random();
        int resultadoRandom = random.nextInt(2);

        if (resultadoRandom == 0){
            for(Jugador jugador: equipos[0].getJugadores()){
                jugador.incrementRanking();
            }
            this.resultado = "El equipo " + equipos[0].getNombre() + " ha ganado";
            equipos[0].setEstaOcupado(false);
        }
        else
        {
            for(Jugador jugador: equipos[1].getJugadores()){
                jugador.incrementRanking();
            }
            this.resultado = " El equipo " + equipos[1].getNombre() + " ha ganado";
            equipos[1].setEstaOcupado(false);

        }
    }

    public static Partida buscarPartida(int idPartida){
        for (Partida partida: partidas){
            if(partida.idPartida == idPartida){
                return partida;
            }
        }
        return null;
    }


}
