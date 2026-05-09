package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.Progreso;

import java.util.List;

@Repository
public interface IProgresoRepository extends JpaRepository<Progreso,Integer> {
    @Query(value = "SELECT nivel_control_ira, COUNT(*) AS cantidad FROM progreso GROUP BY nivel_control_ira ORDER BY nivel_control_ira", nativeQuery = true)
    List<Object[]> cantidadPorNivelControlIra();
    @Query(value = "SELECT m.descripcion, AVG(p.nivel_control_ira) AS promedio FROM progreso p INNER JOIN meta_emocional m ON p.id_meta_emocional = m.id_meta GROUP BY m.descripcion", nativeQuery = true)
    List<Object[]> promedioControlIraPorMeta();
}
