package forumHub.domain.repositorys;

import forumHub.domain.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<Curso,Long> {
}
