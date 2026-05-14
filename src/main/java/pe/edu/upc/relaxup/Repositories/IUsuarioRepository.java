package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.relaxup.Entities.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}