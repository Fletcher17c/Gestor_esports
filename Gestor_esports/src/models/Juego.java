package models;
import javax.naming.directory.InvalidAttributeValueException;

public class Juego {
    String nombre;
    Categoria categoria;


    public Juego(String nombre, String nombreCategoria) throws InvalidAttributeValueException {
        this.nombre = nombre;
        this.categoria = Categoria.crearCategoria(nombreCategoria);
    }

    //getters
    public String getNombre() {return nombre;}
    public Categoria getCategoria() {return categoria;}

    //setters
    public void setNombre(String nombre) throws InvalidAttributeValueException {
        if(nombre == null || nombre.isEmpty() || nombre.chars().anyMatch(Character::isDigit)){
            throw new InvalidAttributeValueException();
        }
        nombre = nombre.strip();
        this.nombre = nombre;
    }
}
