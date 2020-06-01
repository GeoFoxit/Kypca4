package geo.GoodService.repos;

import geo.GoodService.models.Good;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends CrudRepository<Good, Integer> {
    Good getById(Integer id);
}
