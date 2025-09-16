package models;
import javax.naming.directory.InvalidAttributeValueException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Arbitro {
    private int idArbitro;
    protected static Set<Integer> ids = new HashSet<>();
    protected Set<Partida> partidasArbitradas = new HashSet<>();
    private String nombre;
    private int experiencia;

    public Arbitro(String nombre) throws InvalidAttributeValueException {
        setIdArbitro();
        setNombre(nombre);
        this.experiencia = 1;
    }

    public int getIdArbitro() {
        return idArbitro;
    }
    public String getNombre() {
        return nombre;
    }
    public int getExperiencia() {
        return experiencia;
    }


    public void setIdArbitro() {
        Random rand = new Random();
        int nuevo_id;
        do {
            nuevo_id = rand.nextInt(0, 300);
        }while(!ids.add(nuevo_id));
        this.idArbitro = nuevo_id;
    }

    public void setNombre(String nombre)
    throws InvalidAttributeValueException{
        if(nombre == null || nombre.isEmpty()){
            throw new InvalidAttributeValueException();
        }
        nombre = nombre.strip();

        for (char caracter:nombre.toCharArray()){
            if(Character.isDigit(caracter)){
                throw new InvalidAttributeValueException();
            }
        }
        this.nombre = nombre;
    }


    //la experiencia se va a ir aumentando por cada partido arbitrado
    //similar al setter de ranking de los jugadores

    public void incrementExperiencia() {
        this.experiencia++;
    }

    public void arbitrar_partida(int idPartida) throws InvalidAttributeValueException {
            Partida partida = Partida.buscarPartida(idPartida);

        if(partida == null || !this.partidasArbitradas.add(partida)){
            throw new InvalidAttributeValueException();
        }
        partida.setArbitro(this);
        this.incrementExperiencia();
    }

}
