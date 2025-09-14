package models;

import javax.naming.directory.InvalidAttributeValueException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Jugador {
    private int idJugador;
    protected static Set<Integer> ids = new HashSet<>();
    private String nombre;
    private String user_name;
    private int ranking;

    public Jugador( String nombre, String user_name) throws InvalidAttributeValueException {
        setIdJugador();
        setNombre(nombre);
        setUser_name(user_name);
        this.ranking = 1;
    }

    public void setIdJugador() {
        Random rand = new Random();
        int nuevo_id;
        do {
            nuevo_id = rand.nextInt(0, 300);
        } while (!ids.add(nuevo_id));
        this.idJugador = nuevo_id;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setNombre(String nombre)
    throws InvalidAttributeValueException {
        nombre  = nombre.strip();
        for(char caracter: nombre.toCharArray()){
            if (Character.isDigit(caracter)){
                throw new InvalidAttributeValueException();
            }
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setUser_name(String user_name)
        throws InvalidAttributeValueException{
        user_name = user_name.strip();
        if (user_name.toCharArray().length >15 || user_name.contains(" ")){
            throw new InvalidAttributeValueException();
        }
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    //setter de ranking, se le cambia el nombre para tener mas claridad
    public void incrementRanking() {
        this.ranking++;
    }

    public int getRanking() {
        return ranking;
    }
}
