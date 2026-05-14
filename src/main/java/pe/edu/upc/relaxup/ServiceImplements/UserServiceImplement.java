package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.relaxup.Entities.Role;
import pe.edu.upc.relaxup.Entities.Users;
import pe.edu.upc.relaxup.Repositories.IUserRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IUserService;

import java.util.List;

@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private IUserRepository uR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Users insert(Users user) {
        // Encriptar contraseña y activar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        // Datos de perfil por defecto (si no vienen en el request)
        if (user.getNombres() == null) user.setNombres(user.getUsername());
        if (user.getEmail() == null) user.setEmail(user.getUsername() + "@email.com");
        if (user.getDireccion() == null) user.setDireccion("Pendiente");
        user.setCelular(0);

        // Asignar rol USER por defecto
        Role defaultRole = new Role();
        defaultRole.setRol("USER");
        defaultRole.setUser(user);
        user.setRoles(List.of(defaultRole));

        return uR.save(user);
    }
}