package demo.springsec;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class RepoMascotas {

    private final Collection<Mascota> mascotas = new ArrayList<>();

    public Collection<Mascota> getMascotas(int idAmo) {
        return mascotas.stream().filter(m -> m.idAmo() == idAmo).collect(Collectors.toList());
    }

    public void alta(Mascota mascota) {
        mascotas.add(mascota);
    }

}
