package demo.springsec;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class UserManager implements UserDetailsService {

    private final Collection<Usuario> usuarios = new ArrayList<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarios.stream()
                .filter(u -> u.nombre().equals(username))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApplicationRunner crearUsuarios() {
        return args -> {
            usuarios.add(new Usuario(1, "pepe", passwordEncoder().encode("deamu"), Collections.singleton("ROLE_USER")));
            usuarios.add(new Usuario(2, "manolo", passwordEncoder().encode("deamu"), Collections.singleton("ROLE_USER")));
        };
    }
}
