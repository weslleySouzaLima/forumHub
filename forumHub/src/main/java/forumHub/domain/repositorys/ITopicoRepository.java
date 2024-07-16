package forumHub.domain.repositorys;

import forumHub.domain.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicoRepository extends JpaRepository<Topico,Long> {
}
