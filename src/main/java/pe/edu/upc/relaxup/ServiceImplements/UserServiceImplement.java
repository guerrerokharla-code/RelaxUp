package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.relaxup.Entities.Role;
import pe.edu.upc.relaxup.Entities.Users;
import pe.edu.upc.relaxup.Entities.Usuario;
import pe.edu.upc.relaxup.Repositories.IUserRepository;
import pe.edu.upc.relaxup.Repositories.IUsuarioRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IUserService;

import java.util.List;

@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private IUserRepository uR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Transactional
    @Override
    public Users insert(Users user) {
        // 1. Encriptar contraseña y activar usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        // 2. Asignar rol USER por defecto
        Role defaultRole = new Role();
        defaultRole.setRol("USER");
        defaultRole.setUser(user);
        user.setRoles(List.of(defaultRole));

        // 3. Guardar el usuario de seguridad
        Users savedUser = uR.save(user);

// Crear perfil OBLIGATORIO y enlazarlo
        Usuario perfil = new Usuario();
// perfil.setId(savedUser.getId());  ← ¡QUITA ESTA LÍNEA!
        perfil.setUser(savedUser);         // ← la relación es suficiente para @MapsId
        perfil.setNombres(savedUser.getUsername());
        perfil.setEmail(savedUser.getUsername() + "@email.com");
        perfil.setDireccion("Pendiente");
        perfil.setCelular(0);
        usuarioRepo.save(perfil);          // ahora ejecutará un persist porque el ID es null

        return savedUser;
    }
}