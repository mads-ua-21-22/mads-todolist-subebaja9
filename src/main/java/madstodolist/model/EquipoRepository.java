package madstodolist.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EquipoRepository extends CrudRepository<Equipo,Long> {
    Optional<Equipo> findByNombre(String s);
}
