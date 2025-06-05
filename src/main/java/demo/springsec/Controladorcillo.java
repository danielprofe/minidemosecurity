package demo.springsec;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/mascotas")
public class Controladorcillo {

    private final RepoMascotas repoMascotas;

    public Controladorcillo(RepoMascotas repoMascotas) {
        this.repoMascotas = repoMascotas;
    }

    @GetMapping
    public ResponseEntity<Collection<Mascota>> listarMascotas(Authentication auth) {
        if (auth.getPrincipal() instanceof Usuario usuario) {
            return ResponseEntity.ok(repoMascotas.getMascotas(usuario.id()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<Mascota> cargarMascota(@RequestBody Mascota nuevaMascota, Authentication auth) {
        if (auth.getPrincipal() instanceof Usuario usuario) {
            repoMascotas.alta(new Mascota(nuevaMascota.nombre(), usuario.id()));
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
