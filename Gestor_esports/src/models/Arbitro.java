package models;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Arbitro {
    private int idArbitro;
    protected Set<Integer> ids = new HashSet<>();

    String nombre;
    int experiencia;

    public void setIdArbitro() {
        Random rand = new Random();
        int nuevo_id;
        do {
            nuevo_id = rand.nextInt(0, 300);
        }while(!ids.add(nuevo_id));
        this.idArbitro = nuevo_id;
    }
}
