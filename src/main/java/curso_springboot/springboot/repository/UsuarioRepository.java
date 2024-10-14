package curso_springboot.springboot.repository;

import curso_springboot.springboot.model.Usuario;
import jakarta.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.login =?1")
    Usuario findUserByLogin(String login);
}
