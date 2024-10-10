package curso_springboot.springboot.repository;

import curso_springboot.springboot.model.Telefone;
import jakarta.data.repository.Repository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
@Transactional
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {

    @Query("select t from Telefone t where t.pessoa.id = ?1")
     List<Telefone> getTelefones(Long pessoaid);
}
