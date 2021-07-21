package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.domain.Code;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, UUID> {

//    @Query(value = "SELECT * FROM code_snippet c ORDER BY c.snippet_id DESC LIMIT 10", nativeQuery = true)
    List<Code> findTop10ByIsSecretAndTimeLessThanOrderByDateDesc(Boolean isSecret, long upperBound);

}
