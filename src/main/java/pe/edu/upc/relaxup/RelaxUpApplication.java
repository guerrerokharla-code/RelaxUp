package pe.edu.upc.relaxup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.upc.relaxup.Entities.Role;
import pe.edu.upc.relaxup.Entities.Users;
import pe.edu.upc.relaxup.Repositories.IUserRepository;

import java.util.List;

@SpringBootApplication
public class RelaxUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelaxUpApplication.class, args);
    }

    @Bean
    CommandLineRunner init(IUserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.findOneByUsername("admin") == null) {
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setEnabled(true);

                Role role = new Role();
                role.setRol("ADMIN");
                role.setUser(admin);
                admin.setRoles(List.of(role));

                userRepo.save(admin);
                System.out.println(">>> USUARIO ADMIN CREADO <<<");
            } else {
                System.out.println(">>> ADMIN YA EXISTE <<<");
            }
        };
    }
}
