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
        int idArbitro = rand.nextInt(0, 300);
        if(ids.add(idArbitro) == true){
            this.idArbitro = idArbitro;
        }
    }
}
