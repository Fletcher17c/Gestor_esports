package models;
import javax.naming.directory.InvalidAttributeValueException;
import java.util.HashMap;
import java.util.Map;

public class Categoria {
    private static final Map<String, Categoria> categorias = new HashMap<>();
    private final String nombre;

    private Categoria(String nombre) {
        this.nombre = nombre;
    }

    public static Categoria crearCategoria(String nombre) throws InvalidAttributeValueException {
        if (nombre == null || nombre.isEmpty() || nombre.chars().anyMatch(Character::isDigit)) {
            throw new InvalidAttributeValueException();
        }
        return categorias.computeIfAbsent(nombre, Categoria::new);
    }

    public String getNombre() {
        return nombre;
    }
}